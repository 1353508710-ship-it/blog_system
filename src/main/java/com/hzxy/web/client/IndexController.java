package com.hzxy.web.client;

import com.github.pagehelper.PageInfo;
import com.hzxy.model.domain.Article;
import com.hzxy.model.domain.Comment;
import com.hzxy.service.IArticleService;
import com.hzxy.service.ICommentService;
import com.hzxy.utils.Commons;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 控制层--SpringMVC框架的控制器类
 * 处理首页关于文章的相关功能
 */
@Controller
public class IndexController {

    //创建一个日志记录器（logger）
    private static final Logger logger= LoggerFactory.getLogger(IndexController.class);

    //引入文章的业务层IArticleService
    @Autowired
    private IArticleService articleService;
    //引入评论的业务层Service
    @Autowired
    private ICommentService commentService;
//    @Autowired
//    private Commons commons;

    //访问博客首页的方法(文章显示第1页的5条数据)
    @RequestMapping("/")
    public String index(HttpServletRequest request) {

       String path=this.index(request,1,5);
        return path;

    }

    /**
     *访问博客首页的方法，指定第xx页，每页显示xx数据
     * @param page  指定第xx页
     * @param count  指定每页xx数据
     * 点击下一页  http://localhost/page/2
     */
    @RequestMapping("/page/{p}")
    public String index(HttpServletRequest request, @PathVariable("p") int page,
                        @RequestParam(value ="count",defaultValue = "5") int count){

//        request.setAttribute("msg","100");
//        model.addAttribute("a","200");
//        logger.info("page的值为"+page);
//        logger.info("count的值为"+count);
        //调用业务层IArticleService
        PageInfo<Article> articles=articleService.selectArticleWithPage(page,count);
        //System.out.println(articles);
        //PageInfo{pageNum=1, pageSize=5, size=5, startRow=1, endRow=5, total=12, pages=3, list=Page{count=true, pageNum=1, pageSize=5, startRow=0, endRow=5, total=12, pages=3, reasonable=true, pageSizeZero=false}[Article{id=12, title='Spring Boot 2 权威发布'}, Article{id=11, title='Docker数据管理介绍'}, Article{id=10, title='Docker常用客户端指令介绍'}, Article{id=9, title='虚拟化容器技术——Docker运行机制介绍'}, Article{id=8, title='JDK 8——聚合操作'}], prePage=0, nextPage=2, isFirstPage=true, isLastPage=false, hasPreviousPage=false, hasNextPage=true, navigatePages=8, navigateFirstPage=1, navigateLastPage=3, navigatepageNums=[1, 2, 3]}
        //调用业务层的方法获取的是排序好的热度前十的文章数据
        List<Article> articleList =articleService.getHitArticles();
        request.setAttribute("articles",articles);
        //request.setAttribute("commons",commons);
        request.setAttribute("articleList",articleList);
        return "client/index";//跳转到client目录下的index.html页面
    }
    /**
     * 根据文章id查看对应的文章详情信息，并完成对应页面的跳转,同时要将改文章的所有评论显示到页面中
     */
    @RequestMapping("/article/{id}")
    public String getArticleInfo(@PathVariable("id") Integer id,HttpServletRequest request){

        //1、调用业务层的方法，根据id查询文章详情
        //2、将文章详情信息放入request中存储

        Article article=articleService.selectArticleWithId(id);
        //调用getArticleComments()方法，该方法可以将文章对应的评论查询出来
        getArticleComments(request,article);
        request.setAttribute("article",article);

        return "/client/articleDetails";  //跳转到client目录下的articleDetail
    }
    /**
     * 查询文章对应评论信息,并将评论信息填充到文章详情里
     * 举例点击评论中的下一页 http://localhost/article/1?cp=2#comments
     */
     private void getArticleComments(HttpServletRequest request,Article article){
        if(article.getAllowComment()){  //条件成立，改文章允许被评论
            //接收请求参数中cp的值
            String cp=request.getParameter("cp");
            //第一次点击某个文章查看文章详情时，cp为空，cp赋值为1
            cp=StringUtils.isBlank(cp)?"1":cp;
            //调用业务层的相关方法，查询文章对应的评论
            PageInfo<Comment> comments=commentService.selectCommentsWithPage(article.getId(),Integer.parseInt(cp),3);
            request.setAttribute("comments",comments);
            request.setAttribute("cp",cp);
        }
    }
}

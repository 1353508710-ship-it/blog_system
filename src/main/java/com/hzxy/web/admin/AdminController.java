package com.hzxy.web.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzxy.model.ResponseData.ArticleResponseData;
import com.hzxy.model.ResponseData.StatisticBo;
import com.hzxy.model.domain.Article;
import com.hzxy.model.domain.Comment;
import com.hzxy.service.IArticleService;
import com.hzxy.service.ISiteService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 后台管理模块
 * 控制层：
 * SpringMVC的控制器类
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    //引入业务层
    @Autowired
    private ISiteService siteService;
    @Autowired
    private IArticleService articleService;

    //后台管理模块的起始页(仪表盘)  localhost/admin   localhost/admin/index
    @RequestMapping(value = {"/index", ""})
    public String index(HttpServletRequest request) {

        //调用业务层的方法获取文章数量和评论数量
        StatisticBo statisticBo = siteService.getStatisticBo();

        //调用业务层的方法获取最新的5篇文章
        List<Article> articleList = siteService.recentArticles(5);

        //调用业务层的方法获取最新的5篇评论
        List<Comment> commentList = siteService.recentComments(5);
        //将StatisticBo存入request中
        request.setAttribute("statistics", statisticBo);

        //经articleList存入request中
        request.setAttribute("articles", articleList);
        //将commentList存入request中
        request.setAttribute("comments", commentList);
        return "back/index";//跳转到back目录下的index.html页面
    }
    //处理后台中文章管理的请求
    //http://localhost/admin/article

    /**
     * @param request
     * @param page    第几页
     * @param count   每页显示多少条数据
     * @return
     */
    @RequestMapping("/article")
    public String ArticleManage(HttpServletRequest request, @RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "10") int count) {

        //访问业务层的方法，分页查询文章数据
        PageInfo<Article> pageInfo = articleService.selectArticleWithPage(page, count);


        //把PageInfo放入request中存储
        request.setAttribute("articles", pageInfo);

        return "back/article_list";

    }

    //http://localhost/admin/article/toEditPage
    // 跳转到编辑/发布页面（移除错误的发布逻辑）
    @RequestMapping("/article/toEditPage")
    public String toEditPage(HttpServletRequest request,
                             @RequestParam(required = false) Integer id) {
        // 如果是编辑已有文章，查询文章数据回显；如果是新增，传空对象
        Article article = null;
        if (id != null) {
            article = articleService.selectArticleWithId(id);
        }
        request.setAttribute("contents", article);
        return "back/article_edit";
    }

    // 新增：处理文章发布/编辑的表单提交
//    @RequestMapping("/article/publish")
//    public String doPublishArticle(Article article) {
//        // 调用业务层发布文章（此时article包含前端提交的title、content等数据）
//        articleService.publishArticle(article);
//        // 发布成功后跳转到文章列表页
//        return "back/article_list";
//    }

    // 发布文章接口（返回JSON响应）
    @RequestMapping("/article/publish")
    @ResponseBody // 标记返回JSON，而非页面
    public ArticleResponseData publishArticle(Article article) {
        try {
            articleService.publishArticle(article);
            return ArticleResponseData.ok("文章发布成功"); // 成功响应
        } catch (IllegalArgumentException e) {
            // 参数错误（比如标题为空），返回400状态码
            return ArticleResponseData.fail(e.getMessage(), 400);
        } catch (Exception e) {
            // 其他异常，返回500状态码
            e.printStackTrace();
            return ArticleResponseData.fail("发布失败：" + e.getMessage());
        }
    }
    // 编辑文章接口（返回JSON响应）
    @RequestMapping("/article/modify")
    @ResponseBody
    public ArticleResponseData modifyArticle(Article article) {
        try {
            articleService.updateArticleWithId(article);
            return ArticleResponseData.ok("文章编辑成功");
        } catch (IllegalArgumentException e) {
            return ArticleResponseData.fail(e.getMessage(), 400);
        } catch (Exception e) {
            e.printStackTrace();
            return ArticleResponseData.fail("编辑失败：" + e.getMessage());
        }
    }
    // 文章删除接口（对应前端的 /admin/article/delete 请求）
    @RequestMapping("/article/delete")
    @ResponseBody
    public ArticleResponseData deleteArticle(@RequestParam Integer id) {
        try {
            // 调用业务层删除文章
            articleService.deleteArticleById(id);
            return ArticleResponseData.ok("文章删除成功");
        } catch (IllegalArgumentException e) {
            // 处理参数错误（比如ID为空）
            return ArticleResponseData.fail(e.getMessage(), 400);
        } catch (Exception e) {
            e.printStackTrace();
            return ArticleResponseData.fail("文章删除失败：" + e.getMessage());
        }

    }
    //文章详情页
    @RequestMapping("/{id}")
    public String articleDetail(@PathVariable Integer id, HttpServletRequest request) {
        // 查询文章详情
        Article article = articleService.selectArticleWithId(id);
        request.setAttribute("article", article);
        return "client/articleDetail";
    }
}

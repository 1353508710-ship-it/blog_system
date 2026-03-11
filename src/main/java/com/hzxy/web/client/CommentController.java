package com.hzxy.web.client;

import com.hzxy.model.ResponseData.ArticleResponseData;
import com.hzxy.model.domain.Comment;
import com.hzxy.service.ICommentService;
import com.hzxy.utils.Commons;
import com.hzxy.utils.MyUtils;
import com.vdurmont.emoji.EmojiParser;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 控制层--SpringMVC框架中的处理器类
 * 关于评论的相关功能
 */
@Controller
@RequestMapping("/comments")
public class CommentController {

    //引入业务层
    @Autowired
    private ICommentService commentService;
    @Autowired
    private Commons commons;

    /**
     *
     * 处理发布评论的方法
     * @Param  aid 表单提交过来的文章id
     * @Param  text  表单提交过来的评论内容
     */
    @RequestMapping("/publish")
    @ResponseBody //该注解主要用于返回的对象是直接以特定的格式，进行响应，儿并非解析为视图进行跳转
    public ArticleResponseData publishComment(HttpServletRequest request,Integer aid,String text){
        //取出js脚本
        text=MyUtils.cleanXSS(text);

        //表情处理:将表情符合转换为别名格式
        text=EmojiParser.parseToAliases(text);

        //获取当前的登录信息
        User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //System.out.println(user);

        //创建Comment实体类
        Comment comment=new Comment();
        comment.setArticleId(aid); //文章id
        comment.setIp(request.getRemoteAddr()); //ip地址
        comment.setCreated(new Date()); //发布评论的时间，当前系统时间
        comment.setAuthor(user.getUsername());
        comment.setContent(text); //评论的内容
        try {
            //调用业务层的方法,完成评论的发布
            commentService.publishComment(comment);

            return ArticleResponseData.ok();


        }catch (Exception e){

            return ArticleResponseData.fail();
        }


    }
}

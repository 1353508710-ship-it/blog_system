package com.hzxy.service;

import com.github.pagehelper.PageInfo;
import com.hzxy.model.domain.Comment;

/**
 * 业务层：关于评论的相关业务
 */
public interface ICommentService {
    /**
     * 根据文章id获取改文章的所有评论
     * 分页处理
     * @Param articleId  文章id
     * @Param page   表示第几页
     * @Param count  表示每页显示的数量
     */
    public abstract PageInfo<Comment> selectCommentsWithPage(Integer articleId,Integer page,Integer count);

    /**
     * 发布评论
     * @param comment 评论的相关数据
     *
     */
    public void publishComment(Comment comment);
}

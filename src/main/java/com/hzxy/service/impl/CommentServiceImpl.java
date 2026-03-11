package com.hzxy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzxy.dao.CommentMapper;
import com.hzxy.dao.StatisticMapper;
import com.hzxy.model.domain.Comment;
import com.hzxy.model.domain.Statistic;
import com.hzxy.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 业务层：关于评论的相关业务,是ICommentService接口的实现子类
 */
@Service
@Transactional
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private StatisticMapper statisticMapper;

    @Override
    public PageInfo<Comment> selectCommentsWithPage(Integer articleId, Integer page, Integer count) {
        //设置分页查询
        PageHelper.startPage(page,count);
        //调用dao数据访问层CommentMapper(查出的所有评论是不分页的)
        List<Comment> comments =commentMapper.selectCommentByArticleId(articleId);
        //将未分页的集合中的数据放入PageInfo中，然后就分页
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        return pageInfo;
    }

    /**
     * 发布评论
     * @param comment 评论的相关数据
     *
     */
    @Override
    public void publishComment(Comment comment) {
        //将Comment相关数据插入到数据库中
        commentMapper.InsertComment(comment);
        //根据文章的id查询文章统计表中的相关数据
        Statistic statistic=statisticMapper.selectStatisticWithId(comment.getArticleId());
        //将原评论数量获取，然后更新评论数量
        statistic.setCommentsNum(statistic.getCommentsNum()+1);
        statisticMapper.updateArticleCommentWithId(statistic);

    }


}

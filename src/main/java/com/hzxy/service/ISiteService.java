package com.hzxy.service;

import com.hzxy.model.ResponseData.StatisticBo;
import com.hzxy.model.domain.Article;
import com.hzxy.model.domain.Comment;
import com.hzxy.model.domain.Statistic;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 业务层:
 * 整个博客网站关于统计相关的业务
 */
public interface ISiteService {

    /**
     * 最新的文章查询（前五条数据）
     * @param limit 表示要查询的文章数量
     * @return  集合，存储查询出来的5条最新文章数据
     */
    public abstract List<Article> recentArticles(int limit);

    //最新的评论查询
    public abstract List<Comment> recentComments(int limit);

    //最新的统计数量的查询(文章数量和评论数量)
    public abstract StatisticBo getStatisticBo();
}

package com.hzxy.service.impl;

import com.github.pagehelper.PageHelper;
import com.hzxy.dao.ArticleMapper;
import com.hzxy.dao.CommentMapper;
import com.hzxy.dao.StatisticMapper;
import com.hzxy.model.ResponseData.StatisticBo;
import com.hzxy.model.domain.Article;
import com.hzxy.model.domain.Comment;
import com.hzxy.model.domain.Statistic;
import com.hzxy.service.ISiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 业务层:
 * 整个博客网站关于统计相关的业务
 * 该类时ISiteService接口的实现子类
 */
@Service
@Transactional
public class SiteServiceImpl implements ISiteService {

    //引入Dao数据访问层的接口
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private StatisticMapper statisticMapper;

    /**
     * 最新的文章查询（前五条数据）
     * @param limit 表示要查询的文章数量
     * @return  集合，存储查询出来的5条最新文章数据
     */
    @Override
    public List<Article> recentArticles(int limit) { //20-->10    5,6,7

        //设置分页查询
        PageHelper.startPage(1, limit>10 || limit<1?10:limit);
        //调用Dao数据访问层获取数据
        List<Article> articleList=articleMapper.selectArticles();
        //使用循环遍历articleList集合
        for(int i=0;i<articleList.size();i++){
            Article article=articleList.get(i);
            Integer articleId=article.getId();
            //调用Dao数据访问层的方法。获取文章对应的评论数量
            Statistic statistic=statisticMapper.selectStatisticWithId(articleId);
            article.setHits(statistic.getHis());
            article.setCommentsNum(statistic.getCommentsNum());

        }
        return articleList;
    }
    //最新的评论查询

    @Override
    public List<Comment> recentComments(int limit) {
        PageHelper.startPage(1, limit>10 || limit<1?10:limit);
        //调用Dao数据访问层查询所有的评论
        List<Comment> commentList=commentMapper.selectComments();

        return commentList;
    }

    //最新的统计数量的查询(文章数量和评论数量)
    @Override
    public StatisticBo getStatisticBo() {
        //思路：
        //1、调用Dao数据访问层 ArticleMapper、CommentMapper
        //2、将文章数量和评论数量封装到StatisticBo中，最后返回
        Integer articleCount=articleMapper.countArticles();//文章数量
        Integer commentCount=commentMapper.countComment();//评论数量
        //创建StatisticBo--空
        StatisticBo statisticBo=new StatisticBo();
        //赋值
        statisticBo.setArticles(articleCount);
        statisticBo.setComments(commentCount);

        return statisticBo;
    }
}

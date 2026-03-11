package com.hzxy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzxy.dao.ArticleMapper;
import com.hzxy.dao.StatisticMapper;
import com.hzxy.model.domain.Article;
import com.hzxy.model.domain.Statistic;
import com.hzxy.service.IArticleService;
import jdk.jfr.Threshold;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 业务层：是IArticleService接口的实现子类
 */
@Service
@Transactional  //事务管理
public class ArticleServiceImpl implements IArticleService {

    //引入数据访问层的ArticleMapper
    @Autowired
    private ArticleMapper articleMapper;
    //引入数据访问层StatisticMapper
    @Autowired
    private StatisticMapper statisticMapper;
    //使用Redisthemplate存储数据
    @Autowired
    private RedisTemplate redisTemplate;

    //删除文章数据
    @Override
    public void deleteArticleById(Integer id) {
// 1. 参数校验：避免空ID
        if (id == null) {
            throw new IllegalArgumentException("文章ID不能为空");
        }

        // 2. 先删除文章对应的统计数据（t_statistic表）
        statisticMapper.deleteStatisticByArticleId(id);

        // 3. 再删除文章本身（t_article表）
        articleMapper.deleteArticleById(id);
    }

    @Override
    public void updateArticleWithId(Article article) {
        // 参数校验（避免空值）
        if (article.getId() == null) {
            throw new IllegalArgumentException("文章ID不能为空");
        }
        if (!StringUtils.hasText(article.getTitle())) {
            throw new IllegalArgumentException("文章标题不能为空");
        }
        if (!StringUtils.hasText(article.getContent())) {
            throw new IllegalArgumentException("文章内容不能为空");
        }

        // 设置修改时间
        article.setModified(new Date());

        // 调用Mapper更新数据库
        articleMapper.updateArticleWithId(article);
    }

    @Override
    public void publishArticle(Article article) {
        // 关键：参数校验，提前拦截null值，避免数据库报错
        if (!StringUtils.hasText(article.getTitle())) {
            throw new IllegalArgumentException("文章标题不能为空");
        }
        if (!StringUtils.hasText(article.getContent())) {
            throw new IllegalArgumentException("文章内容不能为空");
        }

        // 补充必要的默认值
        article.setCreated(new Date());
        article.setModified(new Date()); // 新增修改时间字段（建议数据库添加）
        if (article.getAllowComment() == null) {
            article.setAllowComment(true); // 默认允许评论
        }

        // 移除多余的赋值代码，直接调用DAO
        articleMapper.publishArticle(article);
        statisticMapper.addStatistic(article);

    }

    /**
     * //分页查询所有文章(分页处理)
     * @param page 表示要查询的页码  1  2  3
     * @param count 表示每页显示的数量
     * @return PageInfo是pagehelp分页插件中提供的类，包含除了当前页的文章数据，还包含了其它的分页信息（总页数、当前页、下一页，每页显示数据等）
     */
    @Override
    public PageInfo<Article> selectArticleWithPage(Integer page, Integer count) {
        //设置分页查询
        PageHelper.startPage(page, count);
        //调用数据访问层Dao层，获取所有的文章数据(未分页的)
        List<Article> articleList =articleMapper.selectArticles();
        //ArticleList中点击量，评论量为空
        //将文章的点击量和评论量封装到Article，并存储到集合中
        for (int i=0;i<articleList.size();i++){
            Article article=articleList.get(i);
            Statistic statistic=statisticMapper.selectStatisticWithId(article.getId());
            article.setHits(statistic.getHis());
            article.setCommentsNum(statistic.getCommentsNum());

        }

        //创建PageInfo将数据封装到PageInfo中Pageinfo中存储的数据就是分页好的数据
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);
        return pageInfo;



    }

    /**
     * 统计首页中前十的热度文章信息
     */
    @Override
    public List<Article> getHitArticles() {
        //创建空集合
        List<Article> articleList =new ArrayList<Article>();
        //目标：往Articlelist集合中存储排序好的热度前十的文章数据
        //调用数据访问层获取相关数据
        List<Statistic> list =statisticMapper.getStatistic();
        //遍历list集合
        for (int i = 0; i < list.size(); i++){  //i=0 1 2 3 4 5 6 7 8 9 10 11
            Statistic s=list.get(i);
            Integer articleId=s.getArticleId();
            Article article=articleMapper.selectArticleById(articleId);
            article.setHits(s.getHis());
            article.setCommentsNum(s.getCommentsNum());
            //将Article放入ArticleList集合中
            articleList.add(article);
            if (i>=9){
                break; //结束循环
            }

        }

        return articleList;
    }

    /**
     * 根据文章id查询该文章的详情信息(Redis的使用)
     * 第一次查看文章详情信息，从数据库MySQl中进行查询，并存入Redis中
     * 如果第二次开始查看文章详情信息，从Redis中获取，不再操作MySQl数据库
     * @Param id 文章id
     * @Return Article 根据id查出的文章详情信息
     */
    @Override
    public Article selectArticleWithId(Integer id) {
        Article article=null;  //文章类Article初始值为空
        //先从Redis中查询  id=10, article_10
        Object o=redisTemplate.opsForValue().get("article_"+id);
        if (o!=null){ //o不为空--第二次查看文章信息：o从Redis中取出
            System.out.println("Redis...");
            article=(Article)o;

        }else {  //o为空的情况,练剑数据访问层Dao，从MySQl中查询
            System.out.println("mysql...");
            article=articleMapper.selectArticleById(id);
            Statistic statistic=statisticMapper.selectStatisticWithId(article.getId());
            article.setHits(article.getHits());
            article.setCommentsNum(article.getCommentsNum());

        }


        if (article!=null){
            //将Article存入到Redis中
            redisTemplate.opsForValue().set("article_"+id,article);  //key:value
        }


        return article;
    }
}

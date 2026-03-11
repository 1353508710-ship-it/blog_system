package com.hzxy.service;

import com.github.pagehelper.PageInfo;
import com.hzxy.model.domain.Article;

import java.util.List;

/**
 * 业务层--
 * 1、连接（访问）数据访问层（Dao中的articleMapper）
 * 2、被web层（控制层 SpringMVC框架的控制器类）访问
 *
 *
 */
public interface IArticleService {



    //删除文章数据
    public abstract void deleteArticleById(Integer id);

    //文章修改的功能,参数是article类型
    public abstract void updateArticleWithId(Article article);

    //发布文章的功能
    public abstract void publishArticle(Article article);

    /**
     * //分页查询所有文章(分页处理)
     * @param page 表示要查询的页码  1  2  3
     * @param count 表示每页显示的数量
     * @return PageInfo是pagehelp分页插件中提供的类，包含除了当前页的文章数据，还包含了其它的分页信息（总页数、当前页、下一页，每页显示数据等）
     */
    public abstract PageInfo<Article> selectArticleWithPage(Integer page, Integer count);

    /**
     * 统计首页中前十的热度文章信息
     */
    public abstract List<Article> getHitArticles();

    /**
     *
     *
     * 根据文章id查询该文章的详情信息(Redis的使用)
     * 第一次查看文章详情信息，从数据库MySQl中进行查询，并存入Redis中
     * 如果第二次开始查看文章详情信息，从Redis中获取，不再操作MySQl数据库
     * @Param id 文章id
     * @Return Article 根据id查出的文章详情信息
     */
    public abstract Article selectArticleWithId(Integer id);






}

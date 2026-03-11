package com.hzxy.model.ResponseData;

/**
 * 封装数据:
 * 1、文章总量
 * 2、评论总量
 */
public class StatisticBo {
    private Integer articles;//文章总量
    private Integer comments;//评论总量

    public StatisticBo() {
    }

    public StatisticBo(Integer articles, Integer comments) {
        this.articles = articles;
        this.comments = comments;
    }

    public Integer getArticles() {
        return articles;
    }

    public void setArticles(Integer articles) {
        this.articles = articles;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "StatisticBo{" +
                "articles=" + articles +
                ", comments=" + comments +
                '}';
    }
}

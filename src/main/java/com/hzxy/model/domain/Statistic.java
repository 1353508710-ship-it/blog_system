package com.hzxy.model.domain;

/**
 * 文章统计表 -- t-statistic
 */
public class Statistic {
    private Integer id; //主键id
    private Integer articleId; //关联的文章id
    private Integer his;//文章点击总量
    private Integer commentsNum;//文章评论总量

    public Statistic() {
    }

    public Statistic(Integer id, Integer articleId, Integer his, Integer commentsNum) {
        this.id = id;
        this.articleId = articleId;
        this.his = his;
        this.commentsNum = commentsNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getHis() {
        return his;
    }

    public void setHis(Integer his) {
        this.his = his;
    }

    public Integer getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(Integer commentsNum) {
        this.commentsNum = commentsNum;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", his=" + his +
                ", commentsNum=" + commentsNum +
                '}';
    }
}

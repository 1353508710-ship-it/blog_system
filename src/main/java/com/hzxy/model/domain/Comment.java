package com.hzxy.model.domain;

import java.util.Date;

/**
 *评论实体类 -- 对应数据库表t_Comment
 */
public class Comment {
    private Integer id; //评论id 主键
    private Integer articleId; //关联文章id
    private Date created; //评论时间
    private String ip; //IP地址
    private String content; //评论的内容
    private String status; //评论的状态，默认approved、审核通过
    private String author; //作者

    public Comment() {
    }

    public Comment(Integer id, Integer articleId, Date created, String ip, String content, String status, String author) {
        this.id = id;
        this.articleId = articleId;
        this.created = created;
        this.ip = ip;
        this.content = content;
        this.status = status;
        this.author = author;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", created=" + created +
                ", ip='" + ip + '\'' +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

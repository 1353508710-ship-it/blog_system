package com.hzxy.dao;

import com.hzxy.model.domain.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 数据访问层：对应数据库表中t_Comment评论表的相关操作
 */
@Mapper
public interface CommentMapper {
    //根据文章id查询改文章对应的所有评论(不分页),按照id降序排列
    @Select("select *from t_comment where article_id=#{articleId} order by id desc")
    public abstract List<Comment> selectCommentByArticleId(Integer articleId);

    //发表评论：
    @Insert("insert into t_comment(article_id,created,author,ip,content)" +
            "values (#{articleId},#{created},#{author},#{ip},#{content})")
    public void InsertComment(Comment comment);

    //统计评论的总数量
    @Select("select count(*) from t_comment")
    public Integer countComment();

    //查询所有的评论，按照id降序即可（最小留言）
    @Select("select *from t_comment order by id desc")
    public List<Comment> selectComments();

}

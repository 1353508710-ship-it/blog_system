package com.hzxy.dao;

import com.hzxy.model.domain.Article;
import com.hzxy.model.domain.Statistic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

/**
 * 数据访问层：访问数据库表中——Statistic表的相关操作
 */
@Mapper
public interface StatisticMapper {

    //根据文章ID删除统计数据
    @Select("DELETE FROM t_statistic WHERE article_id = #{articleId}")
    public abstract void deleteStatisticByArticleId(Integer articleId);

    //新增文章后，改文章对应的文章统计数据也要新增一条
    @Insert("insert into t_statistic(article_id, his, comments_num) VALUES (#{id},0,0);")
    public void addStatistic(Article article);

    /**根据文章id查询点击量和评论量
     * @param articleId  关联的文章id
     * @return  文章统计表实体类
     */
    @Select("select *from t_statistic where article_id=#{articleId}")
    public Statistic selectStatisticWithId(Integer articleId);
    /**
     * 根据点击量降序，再根据评论量降序查询的文章统计表中的数据
     */
    @Select("select *from t_statistic where his!=0 order by his desc ,comments_num desc")
    public List<Statistic> getStatistic();

    /**
     * 更改文章统计表中评论的数量(发布评论成功以后，评论的数量要同步+1)
     */
    @Update("update t_statistic set comments_num=#{commentsNum} where article_id=#{articleId}")
    public void updateArticleCommentWithId(Statistic statistic);


}

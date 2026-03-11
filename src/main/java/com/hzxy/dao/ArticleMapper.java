package com.hzxy.dao;

import com.hzxy.model.domain.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 数据访问层--访问t_article表的相关数据
 */
@Mapper
public interface ArticleMapper {

    //根据ID更新文章
    @Select("UPDATE t_article \" +\n" +
            "            \"SET title = #{title}, \" +          // 文章标题\n" +
            "            \"content = #{content}, \" +        // 文章内容\n" +
            "            \"tags = #{tags}, \" +              // 文章标签\n" +
            "            \"modified = #{modified}, \" +      // 修改时间\n" +
            "            \"allow_comment = #{allowComment}, \" + // 是否允许评论（数据库字段是下划线，实体类是驼峰，MyBatis自动转换）\n" +
            "            \"categories = #{categories} \" +   // 所属分类\n" +
            "            \"WHERE id = #{id}")
    public abstract void updateArticleById(Article article);


    //删除文章数据
    @Select("delete from t_article where id=#{id}")
    public abstract void deleteArticleById(Integer id);

    //文章修改的功能,参数是article类型
    public abstract void updateArticleWithId(Article article);


    //发布文章的功能
    @Insert("insert into t_article(title,created,modified,tags,categories," +
            "allow_comment,thumbnail,content) values (#{title},#{created},#{modified},#{tags},#{categories},#{allowComment},#{thumbnail},#{content});")
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    public abstract Integer publishArticle(Article article);


    //查询文章表中所有的数据--查询所有的文章(不分页,按照id降序排列)
    @Select("select *from t_article order by id desc")
    public abstract List<Article> selectArticles();

    /**根据文章的id查询对应的文章
     *@param id 表示文章id
     *@return Article实体类，封装文章全部数据
     */

    @Select("select *from t_article where id=#{id}")
    public abstract Article selectArticleById(Integer id);

    //统计文章数量
    @Select("select count(*) from t_article")
    public Integer countArticles();

}

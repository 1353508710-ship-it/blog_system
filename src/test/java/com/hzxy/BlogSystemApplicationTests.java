package com.hzxy;

import com.github.pagehelper.PageInfo;
import com.hzxy.dao.ArticleMapper;
import com.hzxy.dao.CommentMapper;
import com.hzxy.dao.StatisticMapper;
import com.hzxy.model.ResponseData.StatisticBo;
import com.hzxy.model.domain.Article;
import com.hzxy.model.domain.Comment;
import com.hzxy.model.domain.Statistic;
import com.hzxy.service.IArticleService;
import com.hzxy.service.ICommentService;
import com.hzxy.service.ISiteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class BlogSystemApplicationTests {

    /**
     * 数据访问层:
     * ArticleMapper
     *StatisticMappper
     * CommentMapper
     */
    //数据访问层的引入
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private StatisticMapper statisticMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Test
    public void test01(){

        articleMapper.deleteArticleById(20);

//        Article article = new Article();
//        article.setId(14);
//        article.setTitle("Spring框架");
//        article.setCreated(new Date());
//        article.setCategories("默认分类");
//        articleMapper.updateArticleWithId(article);


//        Article article = new Article();
//        article.setTitle("Spring框架编程");
//        article.setCreated(new Date());
//        article.setContent("spring框架非常不错");
//        article.setTags("Spring");
//        article.setAllowComment(true);
//        articleMapper.publishArticle(article);
//        System.out.println(article);
//        statisticMapper.addStatistic(article);

        //Integer count=articleMapper.countArticles();
        //System.out.println(count);//12

        //Integer countComment=  commentMapper.countComment();
        //System.out.println(countComment);//14

        //List<Comment> commentList=commentMapper.selectComments();
        //System.out.println(commentList);
        //[Comment{id=19, articleId=12, created=Thu Feb 05 08:00:00 CST 2026, ip='0:0:0:0:0:0:0:1', content='好好好！！！', status='approved', author='user'},
        // Comment{id=18, articleId=12, created=Thu Feb 05 08:00:00 CST 2026, ip='0:0:0:0:0:0:0:1', content='beautiful，very good', status='approved', author='user'},
        // Comment{id=17, articleId=12, created=Thu Feb 05 08:00:00 CST 2026, ip='0:0:0:0:0:0:0:1', content='很好，很好，非常好', status='approved', author='user'},
        // Comment{id=16, articleId=12, created=Thu Feb 05 08:00:00 CST 2026, ip='127.0.0.1', content='小红评论的你 very very good', status='approved', author='奥特曼'},
        // Comment{id=15, articleId=12, created=Thu Feb 05 08:00:00 CST 2026, ip='127.0.0.1', content='小红评论的你 very very good', status='approved', author='奥特曼'},
        // Comment{id=14, articleId=8, created=Thu Dec 13 08:00:00 CST 2018, ip='0:0:0:0:0:0:0:1', content='very good blog', status='approved', author='tom'},
        // Comment{id=13, articleId=1, created=Thu Dec 13 08:00:00 CST 2018, ip='0:0:0:0:0:0:0:1', content='好东西，先收藏起来，哈哈', status='approved', author='tom'},
        // Comment{id=12, articleId=1, created=Thu Dec 13 08:00:00 CST 2018, ip='0:0:0:0:0:0:0:1', content='很全，努力学习中...', status='approved', author='东方不败'},
        // Comment{id=11, articleId=1, created=Thu Dec 13 08:00:00 CST 2018, ip='0:0:0:0:0:0:0:1', content='很详细，感谢...', status='approved', author='东方不败'},
        // Comment{id=10, articleId=1, created=Thu Dec 13 08:00:00 CST 2018, ip='0:0:0:0:0:0:0:1', content='博主，这资料怎么弄的？有相关资源和教材推荐吗？', status='approved', author='李四'},
        // Comment{id=9, articleId=1, created=Thu Dec 13 08:00:00 CST 2018, ip='0:0:0:0:0:0:0:1', content='非常不错，赞一个！', status='approved', author='李四'},
        // Comment{id=3, articleId=10, created=Thu Dec 13 08:00:00 CST 2018, ip='0:0:0:0:0:0:0:1', content='关于Docker虚拟容器的讲解挺好的额，学习中', status='approved', author='李四'},
        // Comment{id=2, articleId=11, created=Thu Dec 13 08:00:00 CST 2018, ip='0:0:0:0:0:0:0:1', content='很不错的原理分析，受用了！', status='approved', author='李四'},
        // Comment{id=1, articleId=12, created=Thu Dec 13 08:00:00 CST 2018, ip='0:0:0:0:0:0:0:1', content='很不错，不过这文章排版不太好看啊', status='approved', author='李四'}]






//        List<Article> articles =articleMapper.selectArticles();
//        System.out.println(articles);
        //[Article{id=12, title='Spring Boot 2 权威发布', created=Wed Dec 12 08:00:00 CST 2018, modified=null, categories='默认分类', tags='Spring Boot 2', allowComment=true, thumbnail='null', hits=null, commentsNum=null}, Article{id=11, title='Docker数据管理介绍', created=Fri Dec 07 08:00:00 CST 2018, modified=null, categories='默认分类', tags='Docker,数据管理', allowComment=true, thumbnail='null', hits=null, commentsNum=null}, Article{id=10, title='Docker常用客户端指令介绍', created=Wed Dec 05 08:00:00 CST 2018, modified=null, categories='默认分类', tags='Docker,客户端指令', allowComment=true, thumbnail='null', hits=null, commentsNum=null}, Article{id=9, title='虚拟化容器技术——Docker运行机制介绍', created=Mon Dec 03 08:00:00 CST 2018, modified=null, categories='默认分类', tags='虚拟化容器,Docker,运行机制', allowComment=true, thumbnail='null', hits=null, commentsNum=null}, Article{id=8, title='JDK 8——聚合操作', created=Sun Dec 02 08:00:00 CST 2018, modified=null, categories='默认分类', tags='JDK 8,聚合操作', allowComment=true, thumbnail='null', hits=null, commentsNum=null}, Article{id=7, title='函数式接口', created=Sat Dec 01 08:00:00 CST 2018, modified=null, categories='默认分类', tags='接口,函数式接口', allowComment=true, thumbnail='null', hits=null, commentsNum=null}, Article{id=6, title='JDK 8——Lambda表达式介绍', created=Tue Nov 27 08:00:00 CST 2018, modified=null, categories='默认分类', tags='2018,Lambda表达式', allowComment=true, thumbnail='null', hits=null, commentsNum=null}, Article{id=5, title='2018版Go语言+区块链学习线路图', created=Tue Nov 27 08:00:00 CST 2018, modified=null, categories='默认分类', tags='2018,Go语言,区块链,学习线路图', allowComment=true, thumbnail='null', hits=null, commentsNum=null}, Article{id=4, title='2018新版PHP学习线路图', created=Fri Nov 16 08:00:00 CST 2018, modified=null, categories='默认分类', tags='2018,PHP,学习线路图', allowComment=true, thumbnail='null', hits=null, commentsNum=null}, Article{id=3, title='2018新版前端与移动开发学习线路图', created=Tue Nov 13 08:00:00 CST 2018, modified=null, categories='默认分类', tags='2018,前端与移动,学习线路图', allowComment=true, thumbnail='null', hits=null, commentsNum=null}, Article{id=2, title='2018新版Python学习线路图', created=Wed Oct 24 08:00:00 CST 2018, modified=null, categories='默认分类', tags='2018,Python,学习线路图', allowComment=true, thumbnail='null', hits=null, commentsNum=null}, Article{id=1, title='2018新版Java学习路线图', created=Wed Oct 10 08:00:00 CST 2018, modified=null, categories='默认分类', tags='2018,Java,学习路线图', allowComment=true, thumbnail='null', hits=null, commentsNum=null}]

//        Statistic statistic =statisticMapper.selectStatisticWithId(1);
//        System.out.println(statistic);
//        List<Statistic> statisticList=statisticMapper.getStatistic();
//        System.out.println(statisticList);

//        Article article=articleMapper.selectArticleById(3);
//        System.out.println(article);

          //List<Comment> commentList =commentMapper.selectCommentByArticleId(1);
          //System.out.println(commentList);
          //[Comment{id=13, articleId=1, created=Thu Dec 13 08:00:00 CST 2018, ip='0:0:0:0:0:0:0:1', content='好东西，先收藏起来，哈哈', status='approved', author='tom'},
          //Comment{id=12, articleId=1, created=Thu Dec 13 08:00:00 CST 2018, ip='0:0:0:0:0:0:0:1', content='很全，努力学习中...', status='approved', author='东方不败'},
          //Comment{id=11, articleId=1, created=Thu Dec 13 08:00:00 CST 2018, ip='0:0:0:0:0:0:0:1', content='很详细，感谢...', status='approved', author='东方不败'},
          //Comment{id=10, articleId=1, created=Thu Dec 13 08:00:00 CST 2018, ip='0:0:0:0:0:0:0:1', content='博主，这资料怎么弄的？有相关资源和教材推荐吗？', status='approved', author='李四'},
          //Comment{id=9, articleId=1, created=Thu Dec 13 08:00:00 CST 2018, ip='0:0:0:0:0:0:0:1', content='非常不错，赞一个！', status='approved', author='李四'}]

          //新增一条评论
//            Comment comment = new Comment();
//            comment.setArticleId(12);
//            comment.setCreated(new Date());
//            comment.setAuthor("奥特曼");
//            comment.setIp("127.0.0.1");
//            comment.setContent("小红评论的你 very very good");
//            commentMapper.InsertComment(comment);
//           //根据改文章的id查询改文章的数量
//            Statistic statistic=statisticMapper.selectStatisticWithId(comment.getArticleId());
//            System.out.println(statistic);
//            statistic.setCommentsNum(statistic.getCommentsNum()+1);
//            System.out.println(statistic);
//            //同步更新评论的数量
//            statisticMapper.updateArticleCommentWithId(statistic);










    }

    /**
     * 业务层：IArticleService
     * CommentService
     * ISiteService
     */
    //业务层引入
    @Autowired
    private IArticleService articleService;

    @Autowired
    private ICommentService commentService;

    @Autowired
    private ISiteService siteService;

    @Test
    public void test02(){

        List<Comment> commentList=siteService.recentComments(5);
        System.out.println(commentList);
        //Page{count=true, pageNum=1, pageSize=5, startRow=0, endRow=5, total=16, pages=4, reasonable=true, pageSizeZero=false}
        // [Comment{id=21, articleId=11, created=Fri Feb 06 08:00:00 CST 2026, ip='0:0:0:0:0:0:0:1', content='11111111111111', status='approved', author='user'},
        // Comment{id=20, articleId=12, created=Fri Feb 06 08:00:00 CST 2026, ip='0:0:0:0:0:0:0:1', content='好好好好！！！！', status='approved', author='user'},
        // Comment{id=19, articleId=12, created=Thu Feb 05 08:00:00 CST 2026, ip='0:0:0:0:0:0:0:1', content='好好好！！！', status='approved', author='user'},
        // Comment{id=18, articleId=12, created=Thu Feb 05 08:00:00 CST 2026, ip='0:0:0:0:0:0:0:1', content='beautiful，very good', status='approved', author='user'},
        // Comment{id=17, articleId=12, created=Thu Feb 05 08:00:00 CST 2026, ip='0:0:0:0:0:0:0:1', content='很好，很好，非常好', status='approved', author='user'}]


        //List<Article> articleList=siteService.recentArticles(1);
        //System.out.println(articleList);
        //Page{count=true, pageNum=1, pageSize=1, startRow=0, endRow=1, total=12, pages=12, reasonable=true, pageSizeZero=false}
        // [Article{id=12, title='Spring Boot 2 权威发布', created=Wed Dec 12 08:00:00 CST 2018, hits=39, commentsNum=5}]

        //Page{count=true, pageNum=1, pageSize=1, startRow=0, endRow=1, total=12, pages=12, reasonable=true, pageSizeZero=false}
        // [Article{id=12, title='Spring Boot 2 权威发布', created=Wed Dec 12 08:00:00 CST 2018, hits=null, commentsNum=null}]

        //Page{count=true, pageNum=1, pageSize=10, startRow=0, endRow=10, total=12, pages=2, reasonable=true, pageSizeZero=false}
        // [Article{id=12, title='Spring Boot 2 权威发布', created=Wed Dec 12 08:00:00 CST 2018, hits=null, commentsNum=null},
        // Article{id=11, title='Docker数据管理介绍', created=Fri Dec 07 08:00:00 CST 2018, hits=null, commentsNum=null},
        // Article{id=10, title='Docker常用客户端指令介绍', created=Wed Dec 05 08:00:00 CST 2018, hits=null, commentsNum=null},
        // Article{id=9, title='虚拟化容器技术——Docker运行机制介绍', created=Mon Dec 03 08:00:00 CST 2018, hits=null, commentsNum=null},
        // Article{id=8, title='JDK 8——聚合操作', created=Sun Dec 02 08:00:00 CST 2018, hits=null, commentsNum=null},
        // Article{id=7, title='函数式接口', created=Sat Dec 01 08:00:00 CST 2018, hits=null, commentsNum=null},
        // Article{id=6, title='JDK 8——Lambda表达式介绍', created=Tue Nov 27 08:00:00 CST 2018, hits=null, commentsNum=null},
        // Article{id=5, title='2018版Go语言+区块链学习线路图', created=Tue Nov 27 08:00:00 CST 2018, hits=null, commentsNum=null},
        // Article{id=4, title='2018新版PHP学习线路图', created=Fri Nov 16 08:00:00 CST 2018, hits=null, commentsNum=null},
        // Article{id=3, title='2018新版前端与移动开发学习线路图', created=Tue Nov 13 08:00:00 CST 2018, hits=null, commentsNum=null}]


        //StatisticBo statisticBo=siteService.getStatisticBo();
        //System.out.println(statisticBo);
        //StatisticBo{articles=12, comments=14}

        //PageInfo<Article> pageInfo =articleService.selectArticleWithPage(2,3);
        //System.out.println(pageInfo);
        //PageInfo{pageNum=2, pageSize=3, size=3, startRow=4, endRow=6, total=12, pages=4, list=Page{count=true, pageNum=2, pageSize=3, startRow=3, endRow=6, total=12, pages=4, reasonable=true, pageSizeZero=false}[Article{id=9, title='虚拟化容器技术——Docker运行机制介绍'}, Article{id=8, title='JDK 8——聚合操作'}, Article{id=7, title='函数式接口'}], prePage=1, nextPage=3, isFirstPage=false, isLastPage=false, hasPreviousPage=true, hasNextPage=true, navigatePages=8, navigateFirstPage=1, navigateLastPage=4, navigatepageNums=[1, 2, 3, 4]}

        //List<Article> articleList=articleService.getHitArticles();
        //System.out.println(articleList);

//        Article article=articleService.selectArticleWithId(8);
//        System.out.println(article);

        //PageInfo<Comment> pageInfo=commentService.selectCommentsWithPage(1,1,3);
        //System.out.println(pageInfo);
        //PageInfo{pageNum=1, pageSize=3, size=3, startRow=1, endRow=3, total=5, pages=2, list=Page{count=true,
        // pageNum=1, pageSize=3, startRow=0, endRow=3, total=5, pages=2, reasonable=true, pageSizeZero=false}
        // [Comment{id=13, articleId=1, created=Thu Dec 13 08:00:00 CST 2018, ip='0:0:0:0:0:0:0:1', content='好东西，先收藏起来，哈哈',
        // status='approved', author='tom'},
        // Comment{id=12, articleId=1, created=Thu Dec 13 08:00:00 CST 2018, ip='0:0:0:0:0:0:0:1', content='很全，努力学习中...',
        // status='approved', author='东方不败'},
        // Comment{id=11, articleId=1, created=Thu Dec 13 08:00:00 CST 2018, ip='0:0:0:0:0:0:0:1', content='很详细，感谢...',
        // status='approved', author='东方不败'}], prePage=0, nextPage=2, isFirstPage=true, isLastPage=false, hasPreviousPage=false, hasNextPage=true, navigatePages=8, navigateFirstPage=1, navigateLastPage=2, navigatepageNums=[1, 2]}
    }

}

package com.hzxy.web.scheduletask;
import com.hzxy.dao.ArticleMapper;
import com.hzxy.dao.CommentMapper;
import com.hzxy.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务管理类
 */
@Component
public class ScheduleTask {

    @Autowired
    private MailUtils mailUtils;

    //引入Dao数据访问层
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CommentMapper commentMapper;
    /**
     * 定时邮件发送任务
     */
    //@Scheduled(cron="0 */1 * * * ?")  //类似于定时器,每间隔3分钟执行邮件发送任务
    public void sendEmail(){
        Integer articlsCount=articleMapper.countArticles();
        Integer commentsCount=commentMapper.countComment();
        StringBuffer content=new StringBuffer();
        content.append("博客系统总文章数量为："+articlsCount).append("\n");
        content.append("博客系统评论总数量为"+commentsCount).append("\n");


        mailUtils.sendSimpleEmail("1279977926@qq.com","个人博客系统流量统计详情",content.toString());


    }
}

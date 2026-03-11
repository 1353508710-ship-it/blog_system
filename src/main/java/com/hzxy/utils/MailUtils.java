package com.hzxy.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

/**
 * 邮件发送的工具
 */
@Component
public class MailUtils {

    @Autowired
    private JavaMailSenderImpl mailSender;//Java提供的发邮件的类

    @Value("1279977926@qq.com")
    private String mailfrom;//发件人邮件
    /**
     *
     * @param mailto 收件人邮件
     * @param subject 邮件标题
     * @param content 邮件的内容
     */
    public void sendSimpleEmail(String mailto, String subject, String content) {

        //定制邮件发送的相关设置
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailfrom);//设置发件人
        message.setTo(mailto);//设置收件人
        message.setSubject(subject);//设置邮件的标题
        message.setText(content);//设置邮件的内容
        //发送邮件
        mailSender.send(message);

    }
}

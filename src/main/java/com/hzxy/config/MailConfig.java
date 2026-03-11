package com.hzxy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * 关于邮件的配置类
 */
@Configuration
public class MailConfig {

    @Value("${spring.mail.host}")
    private String host;//qq邮箱发送的host地址值
    @Value("${spring.mail.port}")
    private Integer port;//qq邮箱发送的端口号
    @Value("${spring.mail.username}")
    private String username;//qq邮箱发送的用户名
    @Value("${spring.mail.password}")
    private String password;//qq邮箱发送的授权密码

    @Bean
    public JavaMailSenderImpl getJavaMailSender() {


        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        //配置ssl
        java.util.Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.ssl.enable","true");


        return mailSender;
    }
}

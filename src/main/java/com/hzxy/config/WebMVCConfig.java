package com.hzxy.config;

import com.hzxy.web.interceptor.BaseInterception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置类
 * 1、通过配置类的方式注册自定义拦截器BaseInterception
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
    //引入自定义的拦截器并赋值
    @Autowired
    private BaseInterception baseInterception;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义的拦截器
        registry.addInterceptor(baseInterception);
    }
}

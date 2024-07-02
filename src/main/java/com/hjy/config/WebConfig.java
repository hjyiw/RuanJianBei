package com.hjy.config;

import com.hjy.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: WebConfig
 * Package: com.hjy.config
 * Description:
 *
 * @Author 黄嘉宇
 * @Create 2024/5/8 14:35
 * @Version 1.0
 */
//配置类注入到ioc容器
@Configuration
public class WebConfig implements WebMvcConfigurer {
    //注入一个拦截器
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器 , 登陆接口和注册接口不拦截
        String pattern = "/question/\\d+/.+";
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns("/user/login","/user/register",pattern);

    }
}

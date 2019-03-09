package com.bookpurple.iam.config;

import com.bookpurple.interceptor.ClientAuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * Created by gauravsharma on 2019-03-09.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private ClientAuthInterceptor clientAuthInterceptor;

    private String[] pattern = {"/iam/**"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(clientAuthInterceptor).addPathPatterns(pattern);
    }
}

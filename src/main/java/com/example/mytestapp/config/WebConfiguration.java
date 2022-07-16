package com.example.mytestapp.config;

import com.example.mytestapp.interceptor.MaintainInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MaintainInterceptor()).
                addPathPatterns("/**").
                excludePathPatterns("/v2/api-docs",
                "/swagger-resources/**",
                "/swagger-ui/",
                "/swagger-ui.html",
                "/webjars/**");
    }




}
package com.shi.electronicdictionary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Bean
    public LoginHandlerInterceptor loginHandlerInterceptor() {
        return new LoginHandlerInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/", "/user/login",
                        "/forget","/user/forget","/register","/user/register",
                        "/css/**", "/images/**", "/js/**", "/fonts/**");
    }
}

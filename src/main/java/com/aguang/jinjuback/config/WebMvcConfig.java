//package com.aguang.jinjuback.config;
//
//import com.aguang.jinjuback.config.interceptor.AccessTokenVerifyInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//public class WebMvcConfig extends WebMvcConfigurerAdapter {
//
//    @Bean
//    public AccessTokenVerifyInterceptor tokenVerifyInterceptor() {
//        return new AccessTokenVerifyInterceptor();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(tokenVerifyInterceptor());
////        registry.addInterceptor(tokenVerifyInterceptor()).addPathPatterns("/jinju");
////        registry.addInterceptor(new AccessTokenVerifyInterceptor());
//        super.addInterceptors(registry);
//    }
//}

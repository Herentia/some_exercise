package com.pb.config;

import com.pb.interceptors.ResponseResultInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author haohan
 * @date 2021/1/6 16:04
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加包装返回结果拦截器
        registry.addInterceptor(new ResponseResultInterceptor()).addPathPatterns("/**");
    }
}

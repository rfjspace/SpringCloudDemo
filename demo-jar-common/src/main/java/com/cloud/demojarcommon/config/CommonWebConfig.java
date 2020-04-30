package com.cloud.demojarcommon.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.cloud.demojarcommon.interceptor.CommonInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CommonWebConfig implements WebMvcConfigurer {
    @Autowired
    private CommonInterceptor commonInterceptor;

    public CommonWebConfig() {
    }

    @Bean
    public HttpMessageConverters customConverters() {
        return new HttpMessageConverters(new HttpMessageConverter[]{new FastJsonHttpMessageConverter()});
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.commonInterceptor).addPathPatterns(new String[0]);
    }
}

package com.cloud.demojarcommon.annotation;

import com.cloud.demojarcommon.advice.CommonExceptionBodyAdvice;
import com.cloud.demojarcommon.advice.CommonRequestBodyAdvice;
import com.cloud.demojarcommon.advice.CommonResponseBodyAdvice;
import com.cloud.demojarcommon.config.CommonWebConfig;
import com.cloud.demojarcommon.interceptor.CommonInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({CommonExceptionBodyAdvice.class,
        CommonRequestBodyAdvice.class,
        CommonResponseBodyAdvice.class,
        CommonWebConfig.class,
        CommonInterceptor.class})
public @interface EnableCommonAdvice {
}

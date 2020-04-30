package com.cloud.demojarcommon.advice;

import com.alibaba.fastjson.JSON;
import com.cloud.demojarcommon.annotation.IgnorReponseAdvice;
import com.cloud.demojarcommon.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;

@RestControllerAdvice
public class CommonResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    private static final Logger log = LoggerFactory.getLogger(CommonResponseBodyAdvice.class);

    public CommonResponseBodyAdvice() {
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return filer(methodParameter);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof Result) {
            log.info("method [{}], response {}", methodParameter.getMethod().getName(), o.toString());
            return (Result) o;
        } else {
            Result rv = new Result();
            String rvStr = JSON.toJSONString(rv.succ(o));
            log.info("method [{}], response {}", methodParameter.getMethod().getName(), rvStr);
            return rv.succ(o);
        }
    }

    private boolean filer(MethodParameter methodParameter) {
        Class<?> clazz = methodParameter.getDeclaringClass();
        Method method = methodParameter.getMethod();
        if (clazz.isAnnotationPresent(IgnorReponseAdvice.class)) {
            return false;
        }
        if (method.isAnnotationPresent(IgnorReponseAdvice.class)) {
            return false;
        }
        return true;
    }
}

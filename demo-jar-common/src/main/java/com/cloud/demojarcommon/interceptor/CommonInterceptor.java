package com.cloud.demojarcommon.interceptor;

import com.alibaba.fastjson.JSON;
import com.cloud.demojarcommon.annotation.IgnorCommonInterceptor;
import com.cloud.demojarcommon.error.CommonErrorCode;
import com.cloud.demojarcommon.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@Component
public class CommonInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(CommonInterceptor.class);

    public CommonInterceptor() {
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("RequestURI : {}", request.getRequestURI());
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Class<?> clazz = handlerMethod.getBeanType();
            Method method = handlerMethod.getMethod();
            if (clazz.isAnnotationPresent(IgnorCommonInterceptor.class)) {
                return true;
            }
            if (method.isAnnotationPresent(IgnorCommonInterceptor.class)) {
                return true;
            }
            String token = request.getHeader("token");
            if (token == null || !token.equals("ABCDEFG123456")) {
                response.setContentType("json/html;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.write(JSON.toJSONString(new Result().fail(CommonErrorCode.NOT_FOUND)));
                return false;
            }
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}

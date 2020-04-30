package com.cloud.demojarcommon.advice;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

@RestControllerAdvice
public class CommonRequestBodyAdvice implements RequestBodyAdvice {
    private static final Logger log = LoggerFactory.getLogger(CommonRequestBodyAdvice.class);

    public CommonRequestBodyAdvice() {
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return false;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        log.info("method [{}], request {}", methodParameter.getMethod().getName(), JSON.toJSONString(httpInputMessage));
        return httpInputMessage;
    }

    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        log.info("method [{}], request {}", methodParameter.getMethod().getName(), JSON.toJSONString(o));
        return httpInputMessage;
    }

    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }

    class XHttpInputMessage implements HttpInputMessage {
        private HttpHeaders headers;
        private InputStream body;

        public XHttpInputMessage(HttpInputMessage httpInputMessage, String encode) throws IOException {
            this.headers = httpInputMessage.getHeaders();
            this.body = encode(httpInputMessage.getBody(), encode);
        }

        private InputStream encode(InputStream body, String encode) {
            //省略对流进行编码的操作
            return body;
        }

        @Override
        public InputStream getBody() {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return null;
        }
    }
}
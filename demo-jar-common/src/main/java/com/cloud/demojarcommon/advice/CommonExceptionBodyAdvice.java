package com.cloud.demojarcommon.advice;

import com.cloud.demojarcommon.error.CommonErrorCode;
import com.cloud.demojarcommon.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class CommonExceptionBodyAdvice {
    private static final Logger log = LoggerFactory.getLogger(CommonExceptionBodyAdvice.class);

    public CommonExceptionBodyAdvice() {
    }

    /**
     * NoHandlerFoundException 404 异常处理
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result handlerNoHandlerFoundException(NoHandlerFoundException exception) {
        outPutErrorWarn(NoHandlerFoundException.class, CommonErrorCode.NOT_FOUND, exception);
        return new Result().fail(CommonErrorCode.NOT_FOUND);
    }

    /**
     * HttpRequestMethodNotSupportedException 405 异常处理
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handlerHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException exception) {
        outPutErrorWarn(HttpRequestMethodNotSupportedException.class,
                CommonErrorCode.METHOD_NOT_ALLOWED, exception);
        return new Result().fail(CommonErrorCode.METHOD_NOT_ALLOWED);
    }

    /**
     * HttpMediaTypeNotSupportedException 415 异常处理
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result handlerHttpMediaTypeNotSupportedException(
            HttpMediaTypeNotSupportedException exception) {
        outPutErrorWarn(HttpMediaTypeNotSupportedException.class,
                CommonErrorCode.UNSUPPORTED_MEDIA_TYPE, exception);
        return new Result().fail(CommonErrorCode.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * 异常处理
     */
    @ExceptionHandler(Exception.class)
    public Result handlerDefaultException(Exception exception) {
        outPutErrorWarn(Exception.class, CommonErrorCode.EXCEPTION, exception);
        return new Result().fail(CommonErrorCode.EXCEPTION);
    }

    public void outPutErrorWarn(Class errorType, Enum secondaryErrorType, Throwable throwable) {
        log.warn("[{}] {}: {}", errorType.getSimpleName(), secondaryErrorType, throwable.getMessage());
    }

}

package com.xiaoazhai.handler;

import com.alibaba.fastjson.JSON;
import com.xiaoazhai.annotation.ReturnSource;
import com.xiaoazhai.result.ReturnMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author zhai
 * 返回结果统一封装
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.xiaoazhai")
public class ResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //抛出异常不返回state:200
        if (body instanceof ReturnMessage) {
            return body;
        }
        if (body instanceof String) {
            return body;
        }
        if (methodParameter.getMethod().getAnnotation(ReturnSource.class) != null) {
            return body;
        }
        return ReturnMessage.success(body);
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }
}

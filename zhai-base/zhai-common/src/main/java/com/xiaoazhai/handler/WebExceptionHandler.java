package com.xiaoazhai.handler;

import com.xiaoazhai.exception.GlobalException;
import com.xiaoazhai.exception.GlobalExceptionWrapper;
import com.xiaoazhai.result.ReturnMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhai
 * web异常统一拦截
 */
@ControllerAdvice
@Slf4j
public class WebExceptionHandler {


    @ExceptionHandler(GlobalException.class)
    @ResponseBody
    public ReturnMessage resolveGlobalException(HttpServletRequest request, HttpServletResponse response, Object o, GlobalException e) {
        response.setContentType("application/json;charset=UTF-8");
        ReturnMessage resultType = new ReturnMessage();
        try {
            response.setStatus(200);
            resultType.setCode(e.getCode());
            resultType.setMessage(e.getMessage());
            log.error("---- 已捕获异常 ----", e);
        } catch (Exception exception) {
            exception.printStackTrace();
            response.setStatus(200);
            resultType.setCode("1");
            resultType.setMessage("抱歉，系统开小差了，请稍后再试");
            log.error("---- 未捕获异常 ----", e);
        }
        return resultType;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ReturnMessage resloveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        log.info(e.getClass().toString());
        log.error("运行是一场", e);
        response.setContentType("application/json;charset=UTF-8");
        ReturnMessage resultType = new ReturnMessage();
        response.setStatus(200);
        resultType.setCode("1");
        resultType.setMessage("抱歉，系统开小差了，请稍后再试");
        return resultType;
    }

    @ExceptionHandler(GlobalExceptionWrapper.class)
    @ResponseBody
    public ReturnMessage resloveException(HttpServletRequest request, HttpServletResponse response, Object o, GlobalExceptionWrapper exceptionWrapper) {
        response.setContentType("application/json;charset=UTF-8");
        ReturnMessage resultType = new ReturnMessage();
        GlobalException  e = exceptionWrapper.getGlobalException();
        try {
            response.setStatus(200);
            resultType.setCode(e.getCode());
            resultType.setMessage(e.getMessage());
            log.error("---- 已捕获异常 ----", e);
        } catch (Exception exception) {
            exception.printStackTrace();
            response.setStatus(200);
            resultType.setCode("1");
            resultType.setMessage("抱歉，系统开小差了，请稍后再试");
            log.error("---- 未捕获异常 ----", e);
        }
        return resultType;
    }

}

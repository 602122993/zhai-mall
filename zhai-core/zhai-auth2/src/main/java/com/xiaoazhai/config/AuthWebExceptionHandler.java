package com.xiaoazhai.config;

import com.xiaoazhai.exception.GlobalException;
import com.xiaoazhai.exception.GlobalExceptionWrapper;
import com.xiaoazhai.result.ReturnMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
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
public class AuthWebExceptionHandler {




    @ExceptionHandler(InvalidGrantException.class)
    @ResponseBody
    public ReturnMessage resloveException(HttpServletRequest request, HttpServletResponse response, Object o, InvalidGrantException e) {
        response.setContentType("application/json;charset=UTF-8");
        ReturnMessage resultType = new ReturnMessage();
        try {
            response.setStatus(200);
            resultType.setCode("1");
            resultType.setMessage("账号或密码错误");
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

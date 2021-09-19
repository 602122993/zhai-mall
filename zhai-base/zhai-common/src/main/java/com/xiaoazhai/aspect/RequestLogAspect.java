package com.xiaoazhai.aspect;

import org.apache.logging.log4j.ThreadContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class RequestLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(RequestLogAspect.class);
    private static final ThreadLocal<Long> timeThreadLocal = new ThreadLocal<>();

    /**
     * 定义切点Pointcut
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.RequestMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.GetMapping) || @annotation(org.springframework.web.bind.annotation.DeleteMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void executionService() {}

    /**
     * 方法调用之前
     * @param joinPoint
     */
    @Before("executionService()")
    public void doBefore(JoinPoint joinPoint) {
        //新增线程ID标识，用于日志跟踪
        ThreadContext.put("TID","Thread-Controller-"+Thread.currentThread().getId());
        timeThreadLocal.set(System.currentTimeMillis());
        //接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取请求的request
        HttpServletRequest request = attributes.getRequest();

        //获取所有请求的参数，封装为map对象
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        //获取被拦截的方法
        Method method = methodSignature.getMethod();
        //获取被拦截的方法名
        String methodName = method.getName();
        logger.info("AOP begin，请求开始方法:{}",method.getDeclaringClass() + "."+methodName+"()");
        //获取方法调用参数
        logger.info("请求url={}",request.getRequestURL().toString());
        logger.info("请求方法={}",request.getMethod());
        logger.info("请求资源URI={}",request.getRequestURI());
        String requestStr = Arrays.toString(joinPoint.getArgs());
        logger.info("接口实际参数为:{}", requestStr);

    }

    /**
     * 方法调用之后
     * @param result
     * @return
     */
    @AfterReturning(returning = "result",pointcut = "executionService()")
    public Object afterReturn(Object result) {
        if(result != null) {
            logger.info("返回值={}",result.toString());
        }
        long startTime = timeThreadLocal.get();
        double callTime = (System.currentTimeMillis() - startTime) /1000.0;
        logger.info("请求花费时间time={}s",callTime);
        return result;
    }



}

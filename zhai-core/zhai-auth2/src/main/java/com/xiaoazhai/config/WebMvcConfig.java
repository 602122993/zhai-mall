package com.xiaoazhai.config;

import com.xiaoazhai.aspect.CurrentUserHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jiangyun
 * @date 2021/10/8  13:14
 **/
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Resource
    private CurrentUserHandlerMethodArgumentResolver currentUserHandlerMethodArgumentResolver;

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserHandlerMethodArgumentResolver);
        super.addArgumentResolvers(argumentResolvers);
    }
}

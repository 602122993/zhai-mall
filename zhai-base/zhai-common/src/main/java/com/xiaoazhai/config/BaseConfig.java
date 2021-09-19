package com.xiaoazhai.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.FormContentFilter;

/**
 * @author zhai
 * @date 2020年7月4日17:50:56
 * 父类配置类
 */
@Configuration
@MapperScan({"com.xiaoazhai.repository.mapper"})
public class BaseConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


    @Bean
    public FormContentFilter formContentFilter(){
        return  new FormContentFilter();
    }
}

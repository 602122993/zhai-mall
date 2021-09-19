package com.xiaoazhai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author zhai
 * @date 2020/9/13  19:59
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableAuthorizationServer
public class ZhaiAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhaiAuthApplication.class, args);
    }

}

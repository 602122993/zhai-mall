package com.xiaoazhai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhai
 * @date 2020/9/13  19:59
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class ZhaiGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhaiGoodsApplication.class, args);
    }

}

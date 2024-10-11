package com.ddbro;

import cn.hutool.extra.spring.EnableSpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableSpringUtil
@MapperScan("com.ddbro.biz.demo.module.**.mapper")
@SpringBootApplication
@EnableDiscoveryClient
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}  
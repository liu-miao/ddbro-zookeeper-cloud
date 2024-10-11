package com.ddbro.mybatisPlus.config;

import com.github.pagehelper.page.PageAutoDialect;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 在spring boot启动完成后将LocalMySqlDialect注册进pagehelper
 *
 * @Author xiuvee
 * @Date 2024/3/4 11:10
 **/
@Component
public class DialectInit implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        PageAutoDialect.registerDialectAlias("mysql", LocalMySqlDialect.class);
    }
}

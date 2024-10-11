package com.ddbro.swagger.config;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;


@Configuration
@Slf4j
public class DocumentRunner implements ApplicationRunner {
    private final Environment env;

    public DocumentRunner(Environment env) {
        this.env = env;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        String serverPort = env.getProperty("server.port");
        String contextPath = env.getProperty("server.servlet.context-path");
        if (StringUtils.isBlank(contextPath)) {
            contextPath = "/doc.html";
        } else {
            contextPath = contextPath + "/doc.html";
        }
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("The host name could not be determined, using `localhost` as fallback");
        }
        log.info("""
                        \n----------------------------------------------------------
                        应用程序"{}"正在运行中......
                        接口文档访问 URL:
                        本地:     {}://localhost:{}{}
                        外部:     {}://{}:{}{}
                        配置文件: {}
                        ----------------------------------------------------------""",
                env.getProperty("spring.application.name"),
                protocol,
                serverPort,
                contextPath,
                protocol,
                hostAddress,
                serverPort,
                contextPath,
                env.getActiveProfiles());
    }
}

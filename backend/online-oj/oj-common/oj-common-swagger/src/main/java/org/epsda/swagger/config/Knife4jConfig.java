package org.epsda.swagger.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * Description: Knife4j配置类
 * Author: EPSDA
 * Date: 2026/08/04
 * Time: 11:40
 * Package Name: org.epsda.swagger.config
 * Project Name: online-oj
 */
@Configuration
public class Knife4jConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                .title("在线OJ平台系统 API")
                .version("1.0")
                .description("这是一个基于Spring Cloud的在线OJ平台系统API文档"));
    }
}


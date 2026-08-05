package org.epsda.base.config;

import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.stp.StpLogic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * Description: Sa-Token配置类, 配置为JWT形式
 * Author: EPSDA
 * Date: 2026/08/04
 * Time: 15:16
 * Package Name: org.epsda.base.config
 * Project Name: online-oj
 */
@Configuration
public class SaTokenConfig {
    /** 让 sa-token 以 JWT 模式签发/校验 token */
    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForSimple();
    }
}

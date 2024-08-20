package com.efreight.gateway.config;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson2.JSONObject;
import com.efreight.common.constants.CommonResultCode;
import com.efreight.common.response.MessageInfo;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhangYongWei
 * @since 2024/8/16
 */
@Slf4j
@Configuration
public class SaTokenConfigure {

    private static final String[] EXCLUDE_LIST = new String[] {
            "/api/**",
            "/hrs/oauth2/token",
            "/hrs/oauth2/logout",
            "/hrs/image/**",
            //"/**/doc.html",
            //"/**/webjars/**",
            //"/**/swagger-resources/**",
            //"/**/v3/api-docs",
            //"/**/favicon.ico",
            "/base/ws/**"
    };

    // 注册 Sa-Token全局过滤器
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                // 指定 [拦截路由]
                .addInclude("/**")
                // 指定 [放行路由]
                .addExclude(EXCLUDE_LIST)
                // 指定[认证函数]: 每次请求执行
                .setAuth(r -> StpUtil.checkLogin())
                // 指定[异常处理函数]：每次[认证函数]发生异常时执行此函数
                .setError(e -> JSONObject.toJSONString(MessageInfo.failed(CommonResultCode.INVALID_BASIC)));
    }
}

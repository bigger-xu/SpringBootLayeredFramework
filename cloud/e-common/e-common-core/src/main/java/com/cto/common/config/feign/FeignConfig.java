package com.cto.common.config.feign;

import com.cto.common.interceptor.OpenFeignRequestInterceptor;
import feign.RequestInterceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign配置信息
 *
 * @author 张永伟
 * @since 2023/4/25
 */
@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor feignInterceptor() {
        return new OpenFeignRequestInterceptor();
    }
}

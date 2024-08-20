package com.efreight.gateway;

import com.efreight.common.annotation.EnableEFCloud;

import org.springframework.boot.SpringApplication;

/**
 * 网关Application
 *
 * @author 张永伟
 * @since 2023/7/1
 */
@EnableEFCloud
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}

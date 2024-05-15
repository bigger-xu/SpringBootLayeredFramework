package com.cto;

import com.cto.common.annotation.EnableEFCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 用户Application
 *
 * @author 张永伟
 * @since 2023/7/1
 */
@EnableEFCloud
@EnableFeignClients(basePackages = "com.cto")
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}

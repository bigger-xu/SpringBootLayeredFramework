package com.efreight;

import com.efreight.common.annotation.EnableEFCloud;

import org.springframework.boot.SpringApplication;

/**
 * 用户Application
 *
 * @author 张永伟
 * @since 2023/7/1
 */
@EnableEFCloud
public class HrsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrsApplication.class, args);
    }

}

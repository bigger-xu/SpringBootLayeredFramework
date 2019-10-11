package com.cto.freemarker;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Zhang Yongwei
 */
@SpringBootApplication
@MapperScan("com.cto.freemarker.dao")
/**
 * 事务配置
 * @author Evan
 */
@EnableTransactionManagement
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}

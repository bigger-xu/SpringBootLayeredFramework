package com.efreight.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhangYongWei
 * @since 2023/12/22
 */
@Configuration
public class ViewsDruidDataSourceConfiguration {
    
    @Bean
    public ViewsDruidDataSource getViewsDruidDataSource() {
        return new ViewsDruidDataSource();
    }
}

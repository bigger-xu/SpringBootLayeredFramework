package com.efreight.base.config;

import com.efreight.common.thread.MdcThreadPoolTaskExecutor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 线程池配置类
 *
 * @author 张永伟
 * @since 2023/9/26
 */
@Slf4j
@Configuration
public class ThreadPoolExecutorConfig {
    
    /**
     * 初始化MDC自定义线程池
     *
     * @return MdcThreadPoolTaskExecutor
     * @since 2023/9/26
     */
    @Bean
    public MdcThreadPoolTaskExecutor mdcThreadPoolTaskExecutor() {
        //   核心线程数
        int corePoolSize = 10;
        //    最大线程数
        int maxPoolSize = 30;
        //  现成活动秒数 默认60
        int keepAliveTime = 60;
        //  队列大小  默认Integer.MAX_VALUE
        int queueCapacity = 5000;
        // 线程名称前缀
        String poolNamePrefix = "base-executor";
        MdcThreadPoolTaskExecutor executor = new MdcThreadPoolTaskExecutor(corePoolSize, maxPoolSize, keepAliveTime, queueCapacity, poolNamePrefix);
        executor.initialize();
        log.info("初始化MdcThreadPoolTaskExecutor --> 参数: corePoolSize:{},maxPoolSize:{},keepAliveTime:{},queueCapacity:{},poolNamePrefix:{}",
                 corePoolSize, maxPoolSize, keepAliveTime, queueCapacity, poolNamePrefix);
        return executor;
    }
}

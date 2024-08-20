package com.efreight.common.thread;


import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.slf4j.MDC;

import org.springframework.lang.NonNull;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 带有MDC的线程池，异步打印链路trackId
 *
 * @author 张永伟
 * @since 2023/9/26
 */
public class MdcThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {
    
    
    private static final long serialVersionUID = 7861926997686467375L;
    
    /**
     * 初始化线程池
     *
     * @param corePoolSize   核心线程数
     * @param maxPoolSize    最大线程数
     * @param keepAliveTime  现成活动秒数 默认60
     * @param queueCapacity  队列大小  默认Integer.MAX_VALUE
     * @param poolNamePrefix 线程名称前缀
     * @since 2023/9/26
     */
    public MdcThreadPoolTaskExecutor(int corePoolSize, int maxPoolSize, int keepAliveTime, int queueCapacity, String poolNamePrefix) {
        setCorePoolSize(corePoolSize);
        setMaxPoolSize(maxPoolSize);
        setKeepAliveSeconds(keepAliveTime);
        setQueueCapacity(queueCapacity);
        setThreadNamePrefix(poolNamePrefix);
    }
    
    /**
     * 无返回值的执行
     *
     * @param command 线程Runnable
     * @since 2023/9/26
     */
    @Override
    public void execute(@NonNull Runnable command) {
        super.execute(wrap(command, getContextForTask()));
    }
    
    /**
     * 有返回值的执行
     *
     * @param task 线程Callable
     * @since 2023/9/26
     */
    @NonNull
    @Override
    public <T> Future<T> submit(@NonNull Callable<T> task) {
        return super.submit(wrap(task, getContextForTask()));
    }
    
    /**
     * Callable 传递MDC信息
     *
     * @param task    线程Callable
     * @param context 上下文
     */
    private static <T> Callable<T> wrap(final Callable<T> task, final Map<String, String> context) {
        return () -> {
            if (null != context) {
                MDC.setContextMap(context);
            }
            try {
                return task.call();
            } finally {
                if (context != null && !context.isEmpty()) {
                    MDC.clear();
                }
            }
        };
    }
    
    /**
     * Callable 传递MDC信息
     *
     * @param runnable 线程
     * @param context  上下文
     */
    private static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        return () -> {
            if (null != context) {
                MDC.setContextMap(context);
            }
            try {
                runnable.run();
            } finally {
                if (context != null && !context.isEmpty()) {
                    MDC.clear();
                }
            }
        };
    }
    
    /**
     * 获取MDC上下文
     */
    private Map<String, String> getContextForTask() {
        return MDC.getCopyOfContextMap();
    }
}

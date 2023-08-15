package com.cto.common.config.hystrix;

import java.util.concurrent.Callable;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import org.slf4j.MDC;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Hystrix 自定义并发策略
 *
 * @author 张永伟
 * @since 2023/4/25
 */
@Component
public class FeignHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {

    private static final String TRACE_ID = "traceId";

    @Override
    public <T> Callable<T> wrapCallable(Callable<T> callable) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String trackId = MDC.get(TRACE_ID);
        return new WrappedCallable<>(callable, attributes, trackId);
    }

    static class WrappedCallable<T> implements Callable<T> {
        private final Callable<T> callable;

        private final RequestAttributes requestAttributes;

        private final String traceId;

        public WrappedCallable(Callable<T> target, RequestAttributes requestAttributes, String traceId) {
            this.callable = target;
            this.requestAttributes = requestAttributes;
            this.traceId = traceId;
        }

        @Override
        public T call() throws Exception {
            try {
                RequestContextHolder.setRequestAttributes(this.requestAttributes);
                MDC.put(TRACE_ID, traceId);
                return this.callable.call();
            } finally {
                RequestContextHolder.resetRequestAttributes();
                MDC.clear();
            }
        }
    }
}
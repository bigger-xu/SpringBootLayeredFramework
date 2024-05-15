package com.cto.order.feign;

import com.cto.order.feign.fallback.FeignTestFallback;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HRS ORG FEIGN
 *
 * @author 张永伟
 * @since 2023/7/5
 */
@FeignClient(contextId = "feignTestService", value = "order-service", fallback = FeignTestFallback.class)
@Service
public interface FeignTestService {
    /**
     * Test
     */
    @GetMapping("/test/time")
    void test();

}

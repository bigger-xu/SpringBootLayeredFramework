package com.efreight.base.feign;

import com.efreight.base.feign.fallback.FeignTestFallback;
import com.efreight.common.constants.ServiceNameConstants;
import com.efreight.common.response.MessageInfo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HRS ORG FEIGN
 *
 * @author 张永伟
 * @since 2023/7/5
 */
@FeignClient(contextId = "feignTestService", value = ServiceNameConstants.BASE_SERVICE, fallback = FeignTestFallback.class)
@Service
public interface FeignTestService {
    /**
     * Test
     */
    @GetMapping("/test/time")
    MessageInfo<Object> test();

}

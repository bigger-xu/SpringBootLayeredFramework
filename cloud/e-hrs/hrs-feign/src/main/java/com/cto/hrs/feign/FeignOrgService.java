package com.cto.hrs.feign;

import com.cto.common.constants.ServiceNameConstants;
import com.cto.common.response.MessageInfo;
import com.cto.hrs.feign.fallback.FeignOrgServiceFallback;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HRS ORG FEIGN
 *
 * @author 张永伟
 * @since 2023/7/5
 */
@FeignClient(contextId = "feignOrgService", value = ServiceNameConstants.HRS_SERVICE, fallback = FeignOrgServiceFallback.class)
@Service
public interface FeignOrgService {
    /**
     * Test
     */
    @GetMapping("/test/time")
    MessageInfo<Object> test();

}

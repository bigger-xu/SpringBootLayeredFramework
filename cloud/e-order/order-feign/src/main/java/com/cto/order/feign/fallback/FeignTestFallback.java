package com.cto.order.feign.fallback;

import com.cto.order.feign.FeignTestService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

/**
 * FeignOrg Fallback
 *
 * @author 张永伟
 * @since 2023/7/5
 */
@Component
@Slf4j
public class FeignTestFallback implements FeignTestService {

    @Override
    public void test() {
        log.error("FeignTestService#test服务异常，进入熔断");
    }
}

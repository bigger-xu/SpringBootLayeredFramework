package com.cto.order.feign.fallback;

import com.cto.common.constants.CommonResultCode;
import com.cto.common.response.MessageInfo;
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
    public MessageInfo<Object> test() {
        log.error("FeignTestService#test服务异常，进入熔断");
        return MessageInfo.failed(CommonResultCode.S_DEGRADE);
    }
}

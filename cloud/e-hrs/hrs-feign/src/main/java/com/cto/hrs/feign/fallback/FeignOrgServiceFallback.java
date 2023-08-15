package com.cto.hrs.feign.fallback;

import com.cto.common.constants.CommonResultCode;
import com.cto.common.response.MessageInfo;
import com.cto.hrs.feign.FeignOrgService;
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
public class FeignOrgServiceFallback implements FeignOrgService {

    @Override
    public MessageInfo<Object> test() {
        log.error("FeignOrgService#test服务异常，进入熔断");
        return MessageInfo.failed(CommonResultCode.S_DEGRADE);
    }
}

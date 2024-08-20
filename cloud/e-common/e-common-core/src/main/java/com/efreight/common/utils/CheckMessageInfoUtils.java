package com.efreight.common.utils;

import com.efreight.common.constants.CommonResultCode;
import com.efreight.common.exception.EfreightBizException;
import com.efreight.common.exception.EfreightServerException;
import com.efreight.common.response.MessageInfo;

/**
 * MessageInfo校验统一处理
 *
 * @author 张永伟
 * @since 2023/4/25
 */
public class CheckMessageInfoUtils {

    /**
     * 判断服务之间调用是否正常，根据code码判断服务是降级还是运行异常
     * 将服务降级类异常统一处理，用户感知系统繁忙，业务异常直接抛出前端提示
     *
     * @param messageInfo MessageInfo返回
     * @since 2023/4/26
     */
    public static void messageInfoSuccess(MessageInfo<?> messageInfo) {
        if (!MessageInfo.isSuccess(messageInfo)) {
            if (messageInfo.getCode().equals(CommonResultCode.S_DEGRADE.getCode())) {
                throw new EfreightServerException(CommonResultCode.SYSTEM_ERROR);
            }
            throw new EfreightBizException(messageInfo.getCode(), messageInfo.getMessageInfo());
        }
    }
}

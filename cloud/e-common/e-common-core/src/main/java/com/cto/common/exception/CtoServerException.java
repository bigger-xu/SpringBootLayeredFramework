package com.cto.common.exception;

import com.cto.common.constants.ResultCode;
import lombok.Getter;

/**
 * 生态云微服务自定义异常 处理服务降级类异常
 *
 * @author 张永伟
 * @since 2023/4/24
 */
@Getter
public class CtoServerException extends RuntimeException {
    private final Integer code;

    public CtoServerException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public CtoServerException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
    }

}

package com.efreight.common.exception;

import com.efreight.common.constants.CommonResultCode;
import com.efreight.common.constants.ResultCode;
import lombok.Getter;

/**
 * 生态云自定义异常
 *
 * @author 张永伟
 * @since 2023/4/24
 */
@Getter
public class EfreightBizException extends RuntimeException {
    
    private static final long serialVersionUID = -8787338852112350102L;
    
    private final Integer code;

    public EfreightBizException(String message) {
        super(message);
        this.code = CommonResultCode.SYSTEM_ERROR.getCode();
    }

    public EfreightBizException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public EfreightBizException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
    }

    public EfreightBizException(ResultCode resultCode, Throwable cause) {
        super(resultCode.getMsg(), cause);
        this.code = resultCode.getCode();
    }

    public EfreightBizException(String message, Throwable cause) {
        super(message, cause);
        this.code = CommonResultCode.SYSTEM_ERROR.getCode();
    }

    public EfreightBizException(Throwable cause) {
        super(cause);
        this.code = CommonResultCode.SYSTEM_ERROR.getCode();
    }

    public EfreightBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = CommonResultCode.SYSTEM_ERROR.getCode();
    }
}

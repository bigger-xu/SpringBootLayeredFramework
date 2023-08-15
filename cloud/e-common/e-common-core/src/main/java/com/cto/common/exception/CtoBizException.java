package com.cto.common.exception;

import com.cto.common.constants.CommonResultCode;
import com.cto.common.constants.ResultCode;
import lombok.Getter;

/**
 * 生态云自定义异常
 *
 * @author 张永伟
 * @since 2023/4/24
 */
@Getter
public class CtoBizException extends RuntimeException {
    private final Integer code;

    public CtoBizException(String message) {
        super(message);
        this.code = CommonResultCode.SYSTEM_ERROR.getCode();
    }

    public CtoBizException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public CtoBizException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
    }

    public CtoBizException(ResultCode resultCode, Throwable cause) {
        super(resultCode.getMsg(), cause);
        this.code = resultCode.getCode();
    }

    public CtoBizException(String message, Throwable cause) {
        super(message, cause);
        this.code = CommonResultCode.SYSTEM_ERROR.getCode();
    }

    public CtoBizException(Throwable cause) {
        super(cause);
        this.code = CommonResultCode.SYSTEM_ERROR.getCode();
    }

    public CtoBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = CommonResultCode.SYSTEM_ERROR.getCode();
    }

}

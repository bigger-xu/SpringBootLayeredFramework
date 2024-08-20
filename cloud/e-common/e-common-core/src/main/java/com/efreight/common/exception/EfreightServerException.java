package com.efreight.common.exception;



import com.efreight.common.constants.ResultCode;
import lombok.Getter;

/**
 * 生态云微服务自定义异常 处理服务降级类异常
 *
 * @author 张永伟
 * @since 2023/4/24
 */
@Getter
public class EfreightServerException extends RuntimeException {
    
    
    private static final long serialVersionUID = -6766154348828305404L;
    
    private final Integer code;

    public EfreightServerException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public EfreightServerException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
    }
}

package com.cto.freemarker.exception;


/**
 * @author Bigger-Xu
 * @version v1.0.1
 * @date 2020/8/29 17:06
 */
public class CustomException extends Exception{
    private final Integer code;
    private final String customMessage;

    public CustomException(String customMessage){
        super(customMessage);
        this.code = -1;
        this.customMessage = customMessage;
    }

    public CustomException(Integer code, String customMessage){
        super(customMessage);
        this.code = code;
        this.customMessage = customMessage;
    }

    public Integer getCode() {
        return code;
    }

    public String getCustomMessage() {
        return customMessage;
    }
}

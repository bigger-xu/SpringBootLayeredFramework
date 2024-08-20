package com.efreight.common.constants;

/**
 * Gateway服务返回状态码
 *
 * @author 张永伟
 * @since 2023/7/14
 */
public class GatewayResultCode extends ResultCode {

    public static final GatewayResultCode GATEWAY_ERROR = new GatewayResultCode(110001, "参数错误!");

    public GatewayResultCode(Integer code, String msg) {
        super(code, msg);
    }
}

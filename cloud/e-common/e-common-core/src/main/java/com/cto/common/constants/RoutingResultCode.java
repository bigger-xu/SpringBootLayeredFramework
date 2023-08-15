package com.cto.common.constants;

/**
 * CRM服务返回状态码
 *
 * @author 张永伟
 * @since 2023/7/14
 */
public class RoutingResultCode extends ResultCode {

    public static final RoutingResultCode ROUTING_ERROR = new RoutingResultCode(210001, "参数错误!");

    public RoutingResultCode(Integer code, String msg) {
        super(code, msg);
    }
}

package com.cto.common.constants;

/**
 * CRM服务返回状态码
 *
 * @author 张永伟
 * @since 2023/7/14
 */
public class BaseResultCode extends ResultCode {

    public static final BaseResultCode BASE_ERROR = new BaseResultCode(170001, "参数错误!");

    public BaseResultCode(Integer code, String msg) {
        super(code, msg);
    }
}

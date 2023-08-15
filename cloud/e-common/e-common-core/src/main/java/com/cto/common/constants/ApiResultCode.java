package com.cto.common.constants;

/**
 * CRM服务返回状态码
 *
 * @author 张永伟
 * @since 2023/7/14
 */
public class ApiResultCode extends ResultCode {

    public static final ApiResultCode API_ERROR = new ApiResultCode(140001, "参数错误!");

    public ApiResultCode(Integer code, String msg) {
        super(code, msg);
    }
}

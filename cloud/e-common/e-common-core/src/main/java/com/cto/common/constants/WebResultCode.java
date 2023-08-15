package com.cto.common.constants;

/**
 * CRM服务返回状态码
 *
 * @author 张永伟
 * @since 2023/7/14
 */
public class WebResultCode extends ResultCode {

    public static final WebResultCode WEB_ERROR = new WebResultCode(200001, "参数错误!");

    public WebResultCode(Integer code, String msg) {
        super(code, msg);
    }
}

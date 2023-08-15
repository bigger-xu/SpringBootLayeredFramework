package com.cto.common.constants;

/**
 * CRM服务返回状态码
 *
 * @author 张永伟
 * @since 2023/7/14
 */
public class OptResultCode extends ResultCode {

    public static final OptResultCode OPT_ERROR = new OptResultCode(190001, "参数错误!");

    public OptResultCode(Integer code, String msg) {
        super(code, msg);
    }
}

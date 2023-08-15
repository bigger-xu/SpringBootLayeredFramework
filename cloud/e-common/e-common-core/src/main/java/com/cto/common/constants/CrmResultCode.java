package com.cto.common.constants;

/**
 * CRM服务返回状态码
 *
 * @author 张永伟
 * @since 2023/7/14
 */
public class CrmResultCode extends ResultCode {

    public static final CrmResultCode CRM_ERROR = new CrmResultCode(120001, "参数错误!");

    public CrmResultCode(Integer code, String msg) {
        super(code, msg);
    }
}

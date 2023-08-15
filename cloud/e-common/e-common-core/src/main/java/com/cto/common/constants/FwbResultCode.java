package com.cto.common.constants;

/**
 * CRM服务返回状态码
 *
 * @author 张永伟
 * @since 2023/7/14
 */
public class FwbResultCode extends ResultCode {

    public static final FwbResultCode FWB_ERROR = new FwbResultCode(180001, "参数错误!");

    public FwbResultCode(Integer code, String msg) {
        super(code, msg);
    }
}

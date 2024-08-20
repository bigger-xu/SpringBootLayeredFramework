package com.efreight.common.constants;

/**
 * TMS服务返回状态码
 *
 * @author 张永伟
 * @since 2023/7/14
 */
public class TmsResultCode extends ResultCode {

    public static final TmsResultCode TMS_ERROR = new TmsResultCode(140001, "参数错误!");

    public TmsResultCode(Integer code, String msg) {
        super(code, msg);
    }
}

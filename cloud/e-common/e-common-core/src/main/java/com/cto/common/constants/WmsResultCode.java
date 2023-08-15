package com.cto.common.constants;

/**
 * WMS服务返回状态码
 *
 * @author 张永伟
 * @since 2023/7/14
 */
public class WmsResultCode extends ResultCode {

    public static final WmsResultCode WMS_ERROR = new WmsResultCode(150001, "参数错误!");

    public WmsResultCode(Integer code, String msg) {
        super(code, msg);
    }
}

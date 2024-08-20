package com.efreight.common.constants;

/**
 * FFM服务返回状态码
 *
 * @author 张永伟
 * @since 2023/7/14
 */
public class FfmResultCode extends ResultCode {

    public static final FfmResultCode FFM_ERROR = new FfmResultCode(160001, "参数错误!");

    public FfmResultCode(Integer code, String msg) {
        super(code, msg);
    }
}

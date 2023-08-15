package com.cto.common.constants;

/**
 * base服务返回状态码
 *
 * @author 张永伟
 * @since 2023/4/24
 */
public class HrsResultCode extends ResultCode {

    /**
     * ORG 状态码 130000
     */
    public static final HrsResultCode HRS_NO_ORG_ID_ERROR = new HrsResultCode(130001, "签约公司ID不能为空");

    public static final HrsResultCode HRS_NO_ORG_ERROR = new HrsResultCode(130002, "签约客户不存在");
    public static final HrsResultCode INVALID_TOKEN_ERROR = new HrsResultCode(130003, "Token无效,请重新登录!");

    public HrsResultCode(Integer code, String msg) {
        super(code, msg);
    }
}

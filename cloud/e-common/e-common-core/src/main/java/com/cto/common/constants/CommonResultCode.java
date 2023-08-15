package com.cto.common.constants;

/**
 * 公共的返回状态码
 *
 * @author 张永伟
 * @since 2023/4/24
 */
public class CommonResultCode extends ResultCode {

    public static final CommonResultCode SUCCESS = new CommonResultCode(0, "操作成功");

    public static final CommonResultCode SYSTEM_ERROR = new CommonResultCode(1, "系统繁忙，请稍后再试");

    public static final CommonResultCode ERROR = new CommonResultCode(2, "操作失败，请稍后重试");

    public static final CommonResultCode ILLEGAL_PARAM = new CommonResultCode(103, "参数无效");

    public static final CommonResultCode EMPTY_PARAM = new CommonResultCode(104, "必填字段");

    public static final CommonResultCode NO_SEARCH_DATA = new CommonResultCode(105, "未查询到数据");

    public static final CommonResultCode DATA_ALREADY_EXISTS = new CommonResultCode(106, "数据已经存在");

    public static final CommonResultCode PARAMS_ERROR = new CommonResultCode(107, "参数错误");

    public static final CommonResultCode EMAIL_PARAMS_ERROR = new CommonResultCode(108, "请在个人设置中检验邮箱配置");

    public static final CommonResultCode REQUEST_METHOD_ERROR = new CommonResultCode(109, "请求方式错误");

    public static final CommonResultCode EMAIL_RECIPIENT_IS_NOT_NULL = new CommonResultCode(110, "收件人不能为空");

    public static final CommonResultCode S_SERVICE_PAUSE = new CommonResultCode(1002, "服务暂停");

    public static final CommonResultCode S_SERVICE_BUSY = new CommonResultCode(1003, "服务器忙");

    public static final CommonResultCode S_INTERFACE_ERROR = new CommonResultCode(1004, "接口不存在");

    public static final CommonResultCode S_INTERFACE_TIMEOUT = new CommonResultCode(1005, "接口超时");

    public static final CommonResultCode S_ERR_INVALID_REQUEST = new CommonResultCode(1007, "请求无效");

    public static final CommonResultCode S_DEGRADE = new CommonResultCode(1010, "系统降级保护，请稍后重试");

    public static final CommonResultCode S_BLOCK = new CommonResultCode(1011, "系统保护中，请稍后重试");

    public static final CommonResultCode FAILED_DECODE = new CommonResultCode(1012, "无法解码基本身份验证令牌");

    public static final CommonResultCode INVALID_BASIC = new CommonResultCode(1013, "无效的基本身份验证令牌");

    public static final CommonResultCode CLIENT_HEADER_IS_NULL = new CommonResultCode(1014, "请求头中client信息为空");

    public static final CommonResultCode FILE_ERROR = new CommonResultCode(1015, "上传文件失败，请稍后重试");

    public static final CommonResultCode FILE_CONFIG_ERROR = new CommonResultCode(1016, "MinIO初始化失败");

    public CommonResultCode(Integer code, String msg) {
        super(code, msg);
    }
}

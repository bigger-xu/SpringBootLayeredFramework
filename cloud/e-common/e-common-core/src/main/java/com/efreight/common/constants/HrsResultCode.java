package com.efreight.common.constants;

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
    
    public static final HrsResultCode MAIL_SEND_ERROR= new HrsResultCode(130011, "邮件发送失败，请稍后重试");
    public static final HrsResultCode MAIL_UPLOAD_FILE_IS_NULL= new HrsResultCode(130012, "请上传附件");
    public static final HrsResultCode MAIL_ATTACHMENT_IS_MISSING = new HrsResultCode(130013, "邮件附件缺失必要的参数信息");
    public static final HrsResultCode MAIL_NICKNAME_IS_MISSING= new HrsResultCode(130014, "邮件昵称不能为空");
    public static final HrsResultCode MAIL_USERNAME_IS_MISSING= new HrsResultCode(130015, "邮件用户名不能为空");
    public static final HrsResultCode MAIL_PASSWORD_IS_MISSING= new HrsResultCode(130016, "邮件密码不能为空");
    public static final HrsResultCode MAIL_SMTP_IS_MISSING= new HrsResultCode(130017, "邮件SMTP不能为空");
    public static final HrsResultCode MAIL_PORT_IS_MISSING= new HrsResultCode(130018, "邮件端口不能为空");
    public static final HrsResultCode MAIL_SSL_IS_MISSING= new HrsResultCode(130019, "邮件是否开启ssl不能为空");
    public static final HrsResultCode MAIL_SENDER_ADDRESS_IS_MISSING= new HrsResultCode(130020, "邮件用户名不能为空");
    public static final HrsResultCode MAIL_VALIDATE_IS_ERROR= new HrsResultCode(130021, "邮件验证失败");


    /**
     * USER & Role & dept 状态码 133000
     * @param code
     * @param msg
     */
    public static final HrsResultCode MASTER_ROLE_NULL = new HrsResultCode(133001, "主角色不能为空");
    public static final HrsResultCode HRS_NO_USER_ID_ERROR = new HrsResultCode(133002, "用户ID不能为空");
    public static final HrsResultCode INVALID_USER = new HrsResultCode(133003, "用户不存在");
    public static final HrsResultCode USER_LOGINNAME_MULTIPLE = new HrsResultCode(133004, "账号错误");



    /**
     * PERMISSION & LOGIN  状态码  136000
     * @param code
     * @param msg
     */
    public static final HrsResultCode IMAGE_CODE_ERROR= new HrsResultCode(136001, "验证码不正确");
    public static final HrsResultCode INVALID_PHONE = new HrsResultCode(136002, "手机号格式不对");
    public static final HrsResultCode INVALID_LOGIN_WAY = new HrsResultCode(136003, "登陆方式不合法");
    public static final HrsResultCode INVALID_PWD= new HrsResultCode(136004, "账号或者密码不正确");
    public static final HrsResultCode NO_DEFAULT_ORG= new HrsResultCode(136005, "您没有默认公司，请联系管理员");
    public static final HrsResultCode HRS_ORG_USER_INVALID= new HrsResultCode(136006, "您不属于当前公司！请联系管理员");



    public HrsResultCode(Integer code, String msg) {
        super(code, msg);
    }
}

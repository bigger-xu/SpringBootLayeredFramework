package com.cto.common.message.model;

import java.io.Serializable;

import lombok.Data;

/**
 * @author 张永伟
 * @since 2023/5/4
 */
@Data
public class MailSendUserInfo implements Serializable {

    private Integer userId;

    private String userEmail;

    //是否配置
    private Boolean mailValid;

    //昵称
    private String mailName;

    //发件箱SMTP
    private String mailSmtp;

    //发件人账号
    private String mailUser;

    //发件人地址
    private String mailAddress;

    //发件人邮箱验证编码
    private String mailVerifyCode;

    // ssl
    private Boolean mailSsl;

    //端口
    private Integer mailPort;

    //邮箱签名
    private String mailSignature;
}

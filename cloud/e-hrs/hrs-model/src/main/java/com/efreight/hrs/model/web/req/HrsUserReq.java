package com.efreight.hrs.model.web.req;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * HRS 用户表
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
@Getter
@Setter
@Schema(name = "HrsUserReq对象", description = "HRS 用户表")
public class HrsUserReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户id")
    private Long id;

    @Schema(description = "用户状态：Y正常，N黑名单")
    private String userStatus;

    @Schema(description = "登陆名")
    private String loginName;

    @Schema(description = "加密密码")
    private String passWord;

    @Schema(description = "加密密码验证")
    private String passWordVerification;

    @Schema(description = "邮箱")
    private String userEmail;

    @Schema(description = "国际电话区号")
    private String internationalCountryCode;

    @Schema(description = "电话号码")
    private String phoneNumber;

    @Schema(description = "姓名 中文")
    private String userName;

    @Schema(description = "姓名 英文")
    private String userEname;

    @Schema(description = "订单编辑打开新窗口  Y：是；N：否；")
    private String orderEditNewPage;

    @Schema(description = "订单保存后关闭窗口 Y：是；N：否；")
    private String orderSaveClosePage;

    @Schema(description = "工号")
    private String jobNumber;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "邮箱是否启用：Y 是 N 否")
    private String mailValid;

    @Schema(description = "发件人")
    private String mailName;

    @Schema(description = "发件人地址")
    private String mailAddress;

    @Schema(description = "发件箱SMTP")
    private String mailSmtp;

    @Schema(description = "发件人账号")
    private String mailUser;

    @Schema(description = "发件人邮箱验证编码")
    private String mailVerifyCode;

    @Schema(description = "发件人端口 ，默认 25")
    private Integer mailPort;

    @Schema(description = "支持SSL 默认N， N 否 Y是")
    private String mailSsl;

    @Schema(description = "邮件签名")
    private String mailSignature;

    @Schema(description = "来源ID")
    private String sourceId;

    @Schema(description = "座机")
    private String telephone;

    @Schema(description = "QQ号")
    private String qq;

    @Schema(description = "微信号")
    private String wechat;

    @Schema(description = "邮箱验证通过：Y是，N否")
    private String emailVerificationPassed;

    @Schema(description = "创建人ID")
    private Long creatorId;

    @Schema(description = "创建人")
    private String creatorName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "操作人ID")
    private Long editorId;

    @Schema(description = "操作人")
    private String editorName;

    @Schema(description = "操作时间")
    private LocalDateTime editTime;
}
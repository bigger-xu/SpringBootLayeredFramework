package com.efreight.hrs.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("hrs_user")
public class HrsUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户状态：Y正常，N黑名单
     */
    private String userStatus;

    /**
     * 登陆名
     */
    private String loginName;

    /**
     * 加密密码
     */
    private String passWord;

    /**
     * 加密密码验证
     */
    private String passWordVerification;

    /**
     * 邮箱
     */
    private String userEmail;

    /**
     * 国际电话区号
     */
    private String internationalCountryCode;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 姓名 中文
     */
    private String userName;

    /**
     * 姓名 英文
     */
    private String userEname;

    /**
     * 订单编辑打开新窗口  Y：是；N：否；
     */
    private String orderEditNewPage;

    /**
     * 订单保存后关闭窗口 Y：是；N：否；
     */
    private String orderSaveClosePage;

    /**
     * 工号
     */
    private String jobNumber;

    /**
     * 备注
     */
    private String remark;

    /**
     * 邮箱是否启用：Y 是 N 否
     */
    private String mailValid;

    /**
     * 发件人
     */
    private String mailName;

    /**
     * 发件人地址
     */
    private String mailAddress;

    /**
     * 发件箱SMTP
     */
    private String mailSmtp;

    /**
     * 发件人账号
     */
    private String mailUser;

    /**
     * 发件人邮箱验证编码
     */
    private String mailVerifyCode;

    /**
     * 发件人端口 ，默认 25
     */
    private Integer mailPort;

    /**
     * 支持SSL 默认N， N 否 Y是
     */
    private String mailSsl;

    /**
     * 邮件签名
     */
    private String mailSignature;

    /**
     * 来源ID
     */
    private String sourceId;

    /**
     * 座机
     */
    private String telephone;

    /**
     * QQ号
     */
    private String qq;

    /**
     * 微信号
     */
    private String wechat;

    /**
     * 邮箱验证通过：Y是，N否
     */
    private String emailVerificationPassed;

    /**
     * 创建人ID
     */
    @TableField(fill = FieldFill.INSERT)
    private Long creatorId;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String creatorName;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 操作人ID
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long editorId;

    /**
     * 操作人
     */
    @TableField(fill = FieldFill.UPDATE)
    private String editorName;

    /**
     * 操作时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime editTime;
}

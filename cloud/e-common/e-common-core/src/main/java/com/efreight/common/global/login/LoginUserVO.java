package com.efreight.common.global.login;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * HRS 用户表
 * </p>
 *
 * @author Ma YuLong
 * @since 2023-08-18
 */
@Getter
@Setter
@ToString
@Schema(description = "HRS 用户表")
public class LoginUserVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户id")
    private Long id;

    @Schema(description = "登陆名")
    private String loginName;
    @Schema(description = "登陆方式")
    private String loginWay;

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
    @Schema(description = "oa用户Id")
    private String sourceId;

    @Schema(description = "订单权限：ALL 全部，SELF 个人，GROUP工作组,DEPT部门")
    private String orderPermission;

    @Schema(description = "AI订单权限：ALL 全部，SELF 个人，GROUP工作组,DEPT部门")
    private String orderPermissionAi;

    @Schema(description = "订单编辑打开新窗口  Y：是；N：否；")
    private String orderEditNewPage;

    @Schema(description = "订单保存后关闭窗口 Y：是；N：否；")
    private String orderSaveClosePage;
    @Schema(description = "主角色Id")
    private Long masterRoleId;

}
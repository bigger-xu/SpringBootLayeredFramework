package com.efreight.hrs.model.web.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * HRS 签约公司
 * </p>
 *
 * @author Ma YuLong
 * @since 2023-08-18
 */
@Getter
@Setter
@Schema(name = "HrsManagerUserDetailVO详情")
public class HrsManagerUserDetailVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "签约公司ID")
    private Long orgId;
    
    @Schema(description = "用户id")
    private Long id;
    
    @Schema(description = "签约公司全称")
    private String orgName;
    
    @Schema(description = "邮箱")
    private String userEmail;
    
    @Schema(description = "部门Id")
    private Long deptId;
    
    @Schema(description = "部门名称")
    private String deptName;
    
    @Schema(description = "电话号码")
    private String phoneNumber;
    
    @Schema(description = "姓名 中文")
    private String userName;
    
    @Schema(description = "姓名 英文")
    private String userEname;
    
    @Schema(description = "主角色Id")
    private Long mastRoleId;
    
    @Schema(description = "附加角色ids")
    private List<Long> slaveRoleIds;

    @Schema(description = "数据集权限")
    private String orderPermission;
    
    @Schema(description = "数据分享")
    private String shareUserIds;

    @Schema(description = "进口数据集权限")
    private String orderPermissionAi;

    @Schema(description = "进口数据分享")
    private String shareUserIdsAi;
    
    @Schema(description = "备注")
    private String remark;
    
    @Schema(description = "QQ号")
    private String qq;
    
    @Schema(description = "微信号")
    private String wechat;
    
    @Schema(description = "座机")
    private String telephone;
    
}
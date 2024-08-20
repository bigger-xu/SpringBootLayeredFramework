package com.efreight.hrs.model.web.vo;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 马玉龙
 * @since 2023/8/30
 */
@Getter
@Setter
@Schema(name = "OrgDeptTreeVO")
public class OrgDeptTreeVO extends TreeVO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(description = "部门名称")
    private String deptName;
    @Schema(description = "展示名称")
    private String name;
    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "工号")
    private String jobNumber;

    @Schema(description = "邮箱")
    private String userEmail;


    @Schema(description = "电话号码")
    private String phoneNumber;

    @Schema(description = "姓名 中文")
    private String userName;

    @Schema(description = "姓名 英文")
    private String userEname;

    @Schema(description = "主角色")
    private String mastRoleName;

    @Schema(description = "附加角色")
    private String slaveRoleNames;

}

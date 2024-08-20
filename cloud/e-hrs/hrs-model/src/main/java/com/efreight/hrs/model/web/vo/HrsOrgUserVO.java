package com.efreight.hrs.model.web.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * HRS 签约公司：用户关联表
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
@Getter
@Setter
@Schema(name = "HrsOrgUserVO对象", description = "HRS 签约公司：用户关联表")
public class HrsOrgUserVO implements Serializable {

    
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    private Long id;

    @Schema(description = "公司id")
    private Long orgId;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "是否默认签约公司：Y是，N否")
    private String isDefaultOrg;

    @Schema(description = "是否管理员：Y是，N否")
    private String isAdmin;

    @Schema(description = "是否启用：Y是，N否")
    private String used;

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

    @Schema(description = "AE订单权限：SELF 个人，GROUP工作组,DEPT部门")
    private String orderPermission;

    @Schema(description = "AI订单权限：SELF 个人，GROUP工作组,DEPT部门")
    private String orderPermissionAi;

    @Schema(description = "AE用户数据分享人 多个英文逗号分隔")
    private String shareUserIds;

    @Schema(description = "AI用户数据分享人 多个英文逗号分隔")
    private String shareUserIdsAi;
}
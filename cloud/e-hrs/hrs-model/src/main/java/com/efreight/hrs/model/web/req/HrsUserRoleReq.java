package com.efreight.hrs.model.web.req;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * HRS 用户权限关系表
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
@Getter
@Setter
@Schema(name = "HrsUserRoleReq对象", description = "HRS 用户权限关系表")
public class HrsUserRoleReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    private Long id;

    @Schema(description = "公司id")
    private Long orgId;

    @Schema(description = "用户Id")
    private Long userId;

    @Schema(description = "角色id")
    private Long roleId;

    @Schema(description = "角色类型：mast主角色。slave附属权限。")
    private String roleType;

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
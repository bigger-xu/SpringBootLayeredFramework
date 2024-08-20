package com.efreight.hrs.model.web.req;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * HRS 角色表
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
@Getter
@Setter
@Schema(name = "HrsRoleReq对象", description = "HRS 角色表")
public class HrsRoleReq implements Serializable {

    
    private static final long serialVersionUID = 1L;

    @Schema(description = "角色id")
    private Long id;

    @Schema(description = "公司id")
    private Long orgId;

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "角色状态：Y启用，N不启用")
    private String roleStatus;

    @Schema(description = "是否管理员：Y是，N不是")
    private String isAdmin;

    @Schema(description = "备注")
    private String remark;

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
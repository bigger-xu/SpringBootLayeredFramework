package com.efreight.hrs.model.web.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * HRS 签约公司：权限表
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
@Getter
@Setter
@Schema(name = "HrsOrgPermissionVO对象", description = "HRS 签约公司：权限表")
public class HrsOrgPermissionVO implements Serializable {

    
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    private Long id;

    @Schema(description = "公司id")
    private Long orgId;

    @Schema(description = "权限id")
    private Long permissionId;

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
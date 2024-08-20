package com.efreight.hrs.model.web.req;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * HRS 签约公司：部门
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
@Getter
@Setter
@Schema(name = "HrsOrgDeptReq对象", description = "HRS 签约公司：部门")
public class HrsOrgDeptReq implements Serializable {

    
    private static final long serialVersionUID = 1L;

    @Schema(description = "部门id")
    private Long id;

    @Schema(description = "公司id")
    private Long orgId;

    @Schema(description = "部门code")
    private String deptCode;

    @Schema(description = "部门名称")
    private String deptName;

    @Schema(description = "父级部门id")
    private Long parentId;

    @Schema(description = "父子部门名称，/分割")
    private String parentChildName;

    @Schema(description = "部门负责人id")
    private Long managerId;

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
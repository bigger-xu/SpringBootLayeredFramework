package com.efreight.hrs.model.web.req;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * HRS 用户页面访问记录
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
@Getter
@Setter
@Schema(name = "HrsUserAccessRecordReq对象", description = "HRS 用户页面访问记录")
public class HrsUserAccessRecordReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "公司ID")
    private Long orgId;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "页面ID")
    private Long permissionId;

    @Schema(description = "排序")
    private Integer sort;

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
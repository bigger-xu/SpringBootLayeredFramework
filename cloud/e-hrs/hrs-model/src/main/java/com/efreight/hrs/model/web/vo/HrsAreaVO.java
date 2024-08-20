package com.efreight.hrs.model.web.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * HRS 签约公司：区域表
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
@Getter
@Setter
@Schema(name = "HrsAreaVO对象", description = "HRS 签约公司：区域表")
public class HrsAreaVO implements Serializable {

    
    private static final long serialVersionUID = 1L;

    @Schema(description = "区域id")
    private Long id;

    @Schema(description = "区域中文名称")
    private String areaName;

    @Schema(description = "区域英文名称")
    private String areaEnName;

    @Schema(description = "区域code")
    private String areaCode;

    @Schema(description = "集团id")
    private Long parentOrgId;

    @Schema(description = "区域管理者id")
    private Long areaManagerId;

    @Schema(description = "区域管理者名称")
    private String areaManagerName;

    @Schema(description = "区域备注")
    private String areaRemark;

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
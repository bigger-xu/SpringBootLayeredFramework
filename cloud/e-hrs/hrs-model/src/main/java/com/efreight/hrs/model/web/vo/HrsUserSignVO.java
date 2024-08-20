package com.efreight.hrs.model.web.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * HRS 用户签章
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
@Getter
@Setter
@Schema(name = "HrsUserSignVO对象", description = "HRS 用户签章")
public class HrsUserSignVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键Id")
    private Long id;

    @Schema(description = "签约公司id")
    private Long orgId;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "名称")
    private String signName;

    @Schema(description = "签章用途")
    private String signUse;

    @Schema(description = "备注")
    private String remarks;

    @Schema(description = "图片地址")
    private String imageUrl;

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
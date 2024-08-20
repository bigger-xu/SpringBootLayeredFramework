package com.efreight.base.model.web.vo;


import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 全局参数详情表
 * </p>
 *
 * @author Zhang Yongwei
 * @since 2023-08-29
 */
@Getter
@Setter
@ToString
@Schema(name = "GlobalParamDetailToValueDTO对象", description = "全局参数详情表")
public class GlobalParamDetailToValueVO implements Serializable {
    
    
    
    private static final long serialVersionUID = -3594593341035787585L;
    
    @Schema(description = "参数名称")
    private String paramName;

    @Schema(description = "视图ID")
    private Long viewId;

    @Schema(description = "视图字段ID")
    private Long fieldId;

    @Schema(description = "视图字段名称")
    private String fieldCode;

    @Schema(description = "视图字段驼峰")
    private String fieldCodeHump;
}
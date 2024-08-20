package com.efreight.base.model.feign.req;


import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 视图字段表
 * </p>
 *
 * @author Zhang Yongwei
 * @since 2024-08-13
 */
@Getter
@Setter
@ToString
@Schema(name = "BaseGlobalViewFieldReq对象", description = "视图字段表")
public class BaseGlobalViewFieldReq implements Serializable {

    
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "视图表ID")
    private Long viewId;

    @Schema(description = "字段名称")
    private String fieldCode;

    @Schema(description = "字段名称-驼峰")
    private String fieldCodeHump;

    @Schema(description = "字段注释")
    private String fieldComment;
}
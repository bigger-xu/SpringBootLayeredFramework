package com.efreight.base.model.feign.req;


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
 * @since 2024-08-13
 */
@Getter
@Setter
@ToString
@Schema(name = "BaseGlobalParamDetailReq对象", description = "全局参数详情表")
public class BaseGlobalParamDetailReq implements Serializable {

    
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "参数分类ID")
    private Long categoryId;

    @Schema(description = "订单类型")
    private String orderType;

    @Schema(description = "参数名称")
    private String paramName;

    @Schema(description = "视图ID")
    private Long viewId;

    @Schema(description = "视图字段ID")
    private Long fieldId;

    @Schema(description = "备注")
    private String remark;
}
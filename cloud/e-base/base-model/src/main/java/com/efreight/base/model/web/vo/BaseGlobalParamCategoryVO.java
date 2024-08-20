package com.efreight.base.model.web.vo;


import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 全局参数分类表
 * </p>
 *
 * @author Zhang Yongwei
 * @since 2024-08-13
 */
@Getter
@Setter
@ToString
@Schema(name = "BaseGlobalParamCategoryVO对象", description = "全局参数分类表")
public class BaseGlobalParamCategoryVO implements Serializable {

    
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "订单类型")
    private String orderType;

    @Schema(description = "分类名称")
    private String categoryName;

    @Schema(description = "分类英文")
    private String categoryNameEn;

    @Schema(description = "序号")
    private Integer sort;

    @Schema(description = "表格分类子集")
    private String childTable;
    
    @Schema(description = "表格分类")
    private List<BaseGlobalParamCategoryVO> child;
}
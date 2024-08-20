package com.efreight.base.model.web.req;

import java.io.Serializable;
import java.util.List;

import com.efreight.common.enums.OrderTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 视图表
 * </p>
 *
 * @author Zhang Yongwei
 * @since 2023-08-29
 */
@Getter
@Setter
@ToString
@Schema(name = "GlobalViewValuesWithStringReq对象", description = "自定义标签转换")
public class GlobalViewValuesWithStringReq implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Schema(description = "标签输出格式  true输出fieldCode否则为全局参数名 默认false")
    private Boolean tagsFlag = false;

    @Schema(description = "参数")
    private List<String> params;
    
    @Schema(description = "带全局标签的字符串")
    private String sourceStr;
    
    @Schema(description = "订单类型")
    private OrderTypeEnum orderType;
}

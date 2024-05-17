package com.cto.order.model.web.vo;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * </p>
 *
 * @author Zhang Yongwei
 * @since 2023-07-04
 */
@Data
@Schema(title = "测试VO")
public class TestVO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Schema(title = "ID")
    private Long id;
    @Schema(title = "英文名")
    private String nameEn;
    @Schema(title = "中文名")
    private String name;
}
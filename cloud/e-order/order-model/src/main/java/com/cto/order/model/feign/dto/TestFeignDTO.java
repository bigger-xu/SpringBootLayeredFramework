package com.cto.order.model.feign.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * </p>
 *
 * @author Zhang Yongwei
 * @since 2023-07-04
 */
@Getter
@Setter
@Schema(title = "TestDTO对象", description = "")
public class TestFeignDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "主键ID")
    private Long id;

    @Schema(title = "英文名称")
    private String nameEn;

    @Schema(title = "中文名")
    private String name;
}

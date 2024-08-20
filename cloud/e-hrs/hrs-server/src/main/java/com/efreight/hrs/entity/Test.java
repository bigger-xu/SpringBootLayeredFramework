package com.efreight.hrs.entity;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * </p>
 *
 * @author Zhang Yongwei
 * @since 2023-07-03
 */
@Getter
@Setter
@Schema(name = "Test对象", description = "")
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "英文名称")
    private String nameEn;

    @Schema(description = "中文名")
    private String name;
}

package com.efreight.base.model.feign.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 基础信息-航司
 * </p>
 *
 * @author caiwd
 * @since 2024-08-14
 */
@Getter
@Setter
@ToString
@Schema(name = "BaseAirlineDTO对象", description = "基础信息-航司")
public class BaseAirlineDTO implements Serializable {


    private static final long serialVersionUID = 1L;

    @Schema(description = "航司ID")
    private Long id;

    @Schema(description = "航司两字码")
    private String codeTwo;

    @Schema(description = "航司三字码")
    private String codeThree;

    @Schema(description = "网址链接")
    private String webUrl;

    @Schema(description = "航司logo地址")
    private String logoUrl;

    @Schema(description = "提单背景")
    private String awbUrl;

    @Schema(description = "航司英文名称")
    private String nameEn;

    @Schema(description = "航司中文名称")
    private String nameCn;

    @Schema(description = "隶属国家")
    private String countryCode;

    @Schema(description = "是否删除 N：否 Y：是")
    private String deleteFlag;
}
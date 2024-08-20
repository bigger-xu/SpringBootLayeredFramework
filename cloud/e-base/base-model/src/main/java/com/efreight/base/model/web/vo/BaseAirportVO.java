package com.efreight.base.model.web.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 基础信息-机场信息
 * </p>
 *
 * @author caiwd
 * @since 2024-08-14
 */
@Getter
@Setter
@ToString
@Schema(name = "BaseAirportVO对象", description = "基础信息-机场信息")
public class BaseAirportVO implements Serializable {


    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "机场三字码")
    private String apCode;

    @Schema(description = "机场英文名称")
    private String apNameEn;

    @Schema(description = "机场中文名称")
    private String apNameCn;

    @Schema(description = "ICAO CODE")
    private String icaoCode;

    @Schema(description = "城市三字码")
    private String cityCode;

    @Schema(description = "航线名称")
    private String routingName;

    @Schema(description = "城市英文名称")
    private String cityNameEn;

    @Schema(description = "城市中文名称")
    private String cityNameCn;

    @Schema(description = "州省名称")
    private String nationCode;

    @Schema(description = "国家两字码")
    private String countryCodeTwo;

    @Schema(description = "国家三字码")
    private String countryCodeThree;

    @Schema(description = "国家数字码")
    private String countryCodeNumber;

    @Schema(description = "国家英文名称")
    private String countryNameEn;

    @Schema(description = "国家中文名称")
    private String countryNameCn;

    @Schema(description = "IATA分区")
    private String iataGroup;

    @Schema(description = "时区")
    private String timeZone;

    @Schema(description = "经度")
    private String apLongitude;

    @Schema(description = "纬度")
    private String apLatitude;

    @Schema(description = "是否生效 N：否 Y：是")
    private String apStatus;

    @Schema(description = "是否删除 N：否 Y：是")
    private String deleteFlag;

    @Schema(description = "创建人id")
    private Long creatorId;

    @Schema(description = "创建人名称-中文姓名")
    private String creatorName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人id")
    private Long editorId;

    @Schema(description = "更新人名称-中文姓名")
    private String editorName;

    @Schema(description = "更新时间")
    private LocalDateTime editTime;
}
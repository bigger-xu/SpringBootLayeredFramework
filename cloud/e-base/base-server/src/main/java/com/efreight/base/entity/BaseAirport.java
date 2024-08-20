package com.efreight.base.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("base_airport")
public class BaseAirport implements Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 机场三字码
     */
    private String apCode;

    /**
     * 机场英文名称
     */
    private String apNameEn;

    /**
     * 机场中文名称
     */
    private String apNameCn;

    /**
     * ICAO CODE
     */
    private String icaoCode;

    /**
     * 城市三字码
     */
    private String cityCode;

    /**
     * 航线名称
     */
    private String routingName;

    /**
     * 城市英文名称
     */
    private String cityNameEn;

    /**
     * 城市中文名称
     */
    private String cityNameCn;

    /**
     * 州省名称
     */
    private String nationCode;

    /**
     * 国家两字码
     */
    private String countryCodeTwo;

    /**
     * 国家三字码
     */
    private String countryCodeThree;

    /**
     * 国家数字码
     */
    private String countryCodeNumber;

    /**
     * 国家英文名称
     */
    private String countryNameEn;

    /**
     * 国家中文名称
     */
    private String countryNameCn;

    /**
     * IATA分区
     */
    private String iataGroup;

    /**
     * 时区
     */
    private String timeZone;

    /**
     * 经度
     */
    private String apLongitude;

    /**
     * 纬度
     */
    private String apLatitude;

    /**
     * 是否生效 N：否 Y：是
     */
    private String apStatus;

    /**
     * 是否删除 N：否 Y：是
     */
    private String deleteFlag;

    /**
     * 创建人id
     */
    @TableField(fill = FieldFill.INSERT)
    private Long creatorId;

    /**
     * 创建人名称-中文姓名
     */
    @TableField(fill = FieldFill.INSERT)
    private String creatorName;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新人id
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long editorId;

    /**
     * 更新人名称-中文姓名
     */
    @TableField(fill = FieldFill.UPDATE)
    private String editorName;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime editTime;
}

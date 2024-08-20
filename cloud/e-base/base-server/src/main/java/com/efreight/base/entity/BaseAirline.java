package com.efreight.base.entity;

import java.io.Serial;
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
 * 基础信息-航司
 * </p>
 *
 * @author caiwd
 * @since 2024-08-14
 */
@Getter
@Setter
@ToString
@TableName("base_airline")
public class BaseAirline implements Serializable {


    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 航司ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 航司两字码
     */
    private String codeTwo;

    /**
     * 航司三字码
     */
    private String codeThree;

    /**
     * 网址链接
     */
    private String webUrl;

    /**
     * 航司logo地址
     */
    private String logoUrl;

    /**
     * 提单背景
     */
    private String awbUrl;

    /**
     * 航司英文名称
     */
    private String nameEn;

    /**
     * 航司中文名称
     */
    private String nameCn;

    /**
     * 隶属国家
     */
    private String countryCode;

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

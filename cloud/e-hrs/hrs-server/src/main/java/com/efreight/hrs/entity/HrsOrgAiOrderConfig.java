package com.efreight.hrs.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * HRS 签约公司：ai订单配置表
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
@Getter
@Setter
@TableName("hrs_org_ai_order_config")
public class HrsOrgAiOrderConfig implements Serializable {

    
    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 签约公司ID
     */
    private Long orgId;

    /**
     * AI订单前缀
     */
    private String orderPrefixAi;

    /**
     * 年 位数 2、4
     */
    private Integer numberYear;

    /**
     * 月 位数  NULL、2
     */
    private Integer numberMonth;

    /**
     * 日 位数  NULL、2
     */
    private Integer numberDay;

    /**
     * 流水号位数  3、4、5、6
     */
    private Integer numberSerial;

    /**
     * AI订单：毛重位数0、1、2
     */
    private Integer orderAiDigitsWeight;

    /**
     * AI订单：体积位数0、1、2、3
     */
    private Integer orderAiDigitsVolume;

    /**
     * AI订单：计费重量位数0、1、2
     */
    private Integer orderAiDigitsChargeWeight;

    /**
     * AI订单：尺寸cm、mm
     */
    private String orderAiDigitsSize;

    /**
     * 创建人ID
     */
    @TableField(fill = FieldFill.INSERT)
    private Long creatorId;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String creatorName;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 操作人ID
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long editorId;

    /**
     * 操作人
     */
    @TableField(fill = FieldFill.UPDATE)
    private String editorName;

    /**
     * 操作时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime editTime;
}
package com.efreight.hrs.model.web.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * HRS 签约公司：ae订单配置表
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
@Getter
@Setter
@Schema(name = "HrsOrgAeOrderConfigVO对象", description = "HRS 签约公司：ae订单配置表")
public class HrsOrgAeOrderConfigVO implements Serializable {

    
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键Id")
    private Long id;

    @Schema(description = "签约公司Id")
    private Long orgId;

    @Schema(description = "AE订单前缀")
    private String orderPrefixAe;

    @Schema(description = "年 位数 2、4")
    private Integer numberYear;

    @Schema(description = "月 位数  NULL、2")
    private Integer numberMonth;

    @Schema(description = "日 位数  NULL、2")
    private Integer numberDay;

    @Schema(description = "流水号位数  3、4、5、6")
    private Integer numberSerial;

    @Schema(description = "AE订单：毛重位数0、1、2")
    private Integer orderAeDigitsWeight;

    @Schema(description = "AE订单：体积位数0、1、2、3")
    private Integer orderAeDigitsVolume;

    @Schema(description = "AE订单：计费重量位数0、1、2")
    private Integer orderAeDigitsChargeWeight;

    @Schema(description = "AE订单：尺寸cm、mm")
    private String orderAeDigitsSize;

    @Schema(description = "创建人ID")
    private Long creatorId;

    @Schema(description = "创建人")
    private String creatorName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "操作人ID")
    private Long editorId;

    @Schema(description = "操作人")
    private String editorName;

    @Schema(description = "操作时间")
    private LocalDateTime editTime;
}
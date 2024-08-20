package com.efreight.common.global.login;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * HRS 签约公司
 * </p>
 *
 * @author Ma YuLong
 * @since 2023-08-18
 */
@Getter
@Setter
@Schema(description = "HRS 签约公司")
public class LoginOrgVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "签约公司ID")
    private Long id;

    @Schema(description = "签约公司全称")
    private String orgName;

    @Schema(description = "签约公司简称")
    private String shortName;

    @Schema(description = "签约公司英文全称")
    private String orgEname;

    @Schema(description = "签约公司英文简称")
    private String shortEname;
    
    @Schema(description = "本币")
    private String localCurrency;

    @Schema(description = "指定城市")
    private String executionPort;
    
    @Schema(description = "AE订单：毛重位数0、1、2")
    private Integer orderAeDigitsWeight;
    
    @Schema(description = "AE订单：体积位数0、1、2、3")
    private Integer orderAeDigitsVolume;
    
    @Schema(description = "AE订单：计费重量位数0、1、2")
    private Integer orderAeDigitsChargeWeight;
    
    @Schema(description = "AE订单：尺寸cm、mm")
    private String orderAeDigitsSize;

    @Schema(description = "AI订单：毛重位数0、1、2")
    private Integer orderAiDigitsWeight;
    
    @Schema(description = "AI订单：体积位数0、1、2、3")
    private Integer orderAiDigitsVolume;
    
    @Schema(description = "AI订单：计费重量位数0、1、2")
    private Integer orderAiDigitsChargeWeight;
    
    @Schema(description = "AI订单：尺寸cm、mm")
    private String orderAiDigitsSize;
    
}
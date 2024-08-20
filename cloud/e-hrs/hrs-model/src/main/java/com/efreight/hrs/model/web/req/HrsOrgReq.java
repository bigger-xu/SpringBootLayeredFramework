package com.efreight.hrs.model.web.req;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * HRS 签约公司
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
@Getter
@Setter
@Schema(name = "HrsOrgReq对象", description = "HRS 签约公司")
public class HrsOrgReq implements Serializable {

    
    private static final long serialVersionUID = 1L;

    @Schema(description = "签约公司ID")
    private Long id;

    @Schema(description = "区域id")
    private Long areaId;

    @Schema(description = "签约公司全称")
    private String orgName;

    @Schema(description = "签约公司代码")
    private String orgCode;

    @Schema(description = "签约公司简称")
    private String shortName;

    @Schema(description = "签约公司英文全称")
    private String orgEname;

    @Schema(description = "签约公司英文简称")
    private String shortEname;

    @Schema(description = "社会信用代码")
    private String socialCreditCode;

    @Schema(description = "开户行账号 英文")
    private String bankAccountEn;

    @Schema(description = "开户行账号中文")
    private String bankAccountCn;

    @Schema(description = "开户行名称_中文")
    private String bankNameCn;

    @Schema(description = "开户行名称_英文")
    private String bankNameEn;

    @Schema(description = "开户行电话 中文")
    private String bankTelCn;

    @Schema(description = "开户行电话 英文")
    private String bankTelEn;

    @Schema(description = "签约公司地址信息_中文")
    private String orgAddressInfoCn;

    @Schema(description = "签约公司地址信息_英文")
    private String orgAddressInfoEn;

    @Schema(description = "签约公司logo")
    private String orgLogo;

    @Schema(description = "签约公司印章")
    private String orgSeal;

    @Schema(description = "本币位")
    private String localCurrency;

    @Schema(description = "指定城市")
    private String executionPort;

    @Schema(description = "AE订单配置")
    private HrsOrgAeOrderConfigReq aeOrderConfigReq;

    @Schema(description = "AI订单配置")
    private HrsOrgAiOrderConfigReq aiOrderConfigReq;
}
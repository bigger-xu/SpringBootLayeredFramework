package com.efreight.hrs.model.web.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * HRS 用户 扩展表
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
@Getter
@Setter
@Schema(name = "HrsUserExtendVO对象", description = "HRS 用户 扩展表")
public class HrsUserExtendVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    private Long id;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "公司id")
    private Long orgId;

    @Schema(description = "AE订单：订单默认日期")
    private String orderAeDefaultDate;

    @Schema(description = "AE订单：默认服务产品")
    private String orderAeDefaultServiceProduct;

    @Schema(description = "AE订单：订单默认始发港口")
    private String orderAeDefaultStation;

    @Schema(description = "AE订单：订单默认电池")
    private String orderAeDefaultBattery;

    @Schema(description = "AE订单：订单默认货物种类")
    private String orderAeDefaultGoodsType;

    @Schema(description = "AE订单：订单默认货站")
    private String orderAeWarehouseDef;

    @Schema(description = "AE默认前置仓库")
    private String orderAeWarehouse;

    @Schema(description = "订单配置-AE-默认地磅")
    private String orderAeWeighbridge;

    @Schema(description = "贸易条款")
    private String tradeTerms;

    @Schema(description = "责任结算id")
    private Long settlementUserId;

    @Schema(description = "责任结算名称")
    private String settlementUserName;

    @Schema(description = "责任操作ID")
    private Long operateId;

    @Schema(description = "责任操作名称")
    private String operateName;

    @Schema(description = "责任单证ID")
    private Long makeBillId;

    @Schema(description = "责任单证名称")
    private String makeBillName;

    @Schema(description = "责任航线ID")
    private Long routingerId;

    @Schema(description = "责任航线名称")
    private String routingerName;

    @Schema(description = "分泡比")
    private String bubbleSplitRatio;

    @Schema(description = "AI订单：订单默认日期")
    private String orderAiDefaultDate;

    @Schema(description = "AI订单：订单默认目的港口")
    private String orderAiDefaultStation;

    @Schema(description = "订单配置-AI-默认仓库")
    private String orderAiWarehouse;

    @Schema(description = "订单配置-AI-默认地磅")
    private String orderAiWeighbridge;

    @Schema(description = "AI订单：订单默认货物种类")
    private String orderAiDefaultGoodsType;

    @Schema(description = "默认出口责任客服ID")
    private Long orderAeCustomerId;

    @Schema(description = "默认出口责任客服名称")
    private String orderAeCustomerName;

    @Schema(description = "默认进口责任客服ID")
    private Long orderAiCustomerId;

    @Schema(description = "默认进口责任客服名称")
    private String orderAiCustomerName;

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

    @Schema(description = "AI订单：主单收货人")
    private Long orderAiAwbReceiverId;

    @Schema(description = "英文品名console Y:N")
    private String goodsNameConsol;

    @Schema(description = "木:非木")
    private String nonWood;

    @Schema(description = "责任单证id")
    private Long orderAiMakeBillId;

    @Schema(description = "责任单证name")
    private String orderAiMakeBillName;

    @Schema(description = "服务产品")
    private String orderAiBusinessProduct;
}
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
 * HRS 用户 扩展表
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
@Getter
@Setter
@TableName("hrs_user_extend")
public class HrsUserExtend implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 公司id
     */
    private Long orgId;

    /**
     * AE订单：订单默认日期
     */
    private String orderAeDefaultDate;

    /**
     * AE订单：默认服务产品
     */
    private String orderAeDefaultServiceProduct;

    /**
     * AE订单：订单默认始发港口
     */
    private String orderAeDefaultStation;

    /**
     * AE订单：订单默认电池
     */
    private String orderAeDefaultBattery;

    /**
     * AE订单：订单默认货物种类
     */
    private String orderAeDefaultGoodsType;

    /**
     * AE订单：订单默认货站
     */
    private String orderAeWarehouseDef;

    /**
     * AE默认前置仓库
     */
    private String orderAeWarehouse;

    /**
     * 订单配置-AE-默认地磅
     */
    private String orderAeWeighbridge;

    /**
     * 贸易条款
     */
    private String tradeTerms;

    /**
     * 责任结算id
     */
    private Long settlementUserId;

    /**
     * 责任结算名称
     */
    private String settlementUserName;

    /**
     * 责任操作ID
     */
    private Long operateId;

    /**
     * 责任操作名称
     */
    private String operateName;

    /**
     * 责任单证ID
     */
    private Long makeBillId;

    /**
     * 责任单证名称
     */
    private String makeBillName;

    /**
     * 责任航线ID
     */
    private Long routingerId;

    /**
     * 责任航线名称
     */
    private String routingerName;

    /**
     * 分泡比
     */
    private String bubbleSplitRatio;

    /**
     * AI订单：订单默认日期
     */
    private String orderAiDefaultDate;

    /**
     * AI订单：订单默认目的港口
     */
    private String orderAiDefaultStation;

    /**
     * 订单配置-AI-默认仓库
     */
    private String orderAiWarehouse;

    /**
     * 订单配置-AI-默认地磅
     */
    private String orderAiWeighbridge;

    /**
     * AI订单：订单默认货物种类
     */
    private String orderAiDefaultGoodsType;

    /**
     * 默认出口责任客服ID
     */
    private Long orderAeCustomerId;

    /**
     * 默认出口责任客服名称
     */
    private String orderAeCustomerName;

    /**
     * 默认进口责任客服ID
     */
    private Long orderAiCustomerId;

    /**
     * 默认进口责任客服名称
     */
    private String orderAiCustomerName;

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

    /**
     * AI订单：主单收货人
     */
    private Long orderAiAwbReceiverId;

    /**
     * 英文品名console Y:N
     */
    private String goodsNameConsol;

    /**
     * 木:非木
     */
    private String nonWood;

    /**
     * 责任单证id
     */
    private Long orderAiMakeBillId;

    /**
     * 责任单证name
     */
    private String orderAiMakeBillName;

    /**
     * 服务产品
     */
    private String orderAiBusinessProduct;
}

package com.efreight.common.enums;

import lombok.Getter;

/**
 * 订单类型枚举
 *
 * @author LB
 */
@Getter
public enum OrderTypeEnum {

    /**
     * AE
     */
    AE("空运出口"),

    /**
     * AI
     */
    AI("空运进口");
    
    
    private final String label;

    OrderTypeEnum(String label) {
        this.label = label;
    }

    public static OrderTypeEnum getOrderType(String orderType) {
        return OrderTypeEnum.valueOf(orderType);
    }

}

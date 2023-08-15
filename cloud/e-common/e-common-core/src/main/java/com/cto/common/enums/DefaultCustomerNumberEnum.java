package com.cto.common.enums;

import lombok.Getter;

/**
 * 公司配置-订单配置-默认客户单号枚举
 */
@Getter
public enum DefaultCustomerNumberEnum {

    EMPTY("空"), ORDER_CODE("订单号"), INTERNAL_NUMBER("内部识别号");

    private final String desc;

    DefaultCustomerNumberEnum(String desc) {
        this.desc = desc;
    }
}

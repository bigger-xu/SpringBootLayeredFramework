package com.cto.common.enums;

import lombok.Getter;

/**
 * 订单节点记录枚举
 */
@Getter
public enum OrderNodeEnum {

    ORDER_CREATE("订单创建", 0),

    WAREHOUSE_CONFIRM("仓位确认", 10),

    WAREHOUSE_STORAGE("仓库入库", 20),

    CARGO_OUT_WEIGHT("货物出重", 30),

    CARGO_ARRIVED("货已到齐", 40),

    PRE_MATCHING_MANIFEST("预配舱单", 50),

    CUSTOMS_DECLARATION("海关申报", 60),

    PRINT_TAG("打印标签", 70),

    DELIVERY_ESCROW("交货托书", 80),

    LANE_SIGNED("航线签单", 90),

    ENTER_REGISTER("入区登记", 100),

    CARGO_OUT_WAREHOUSE("货已出库", 110),
    ;

    /**
     * 节点描述
     */
    private final String desc;

    /**
     * 节点序号
     */
    private final Integer sort;

    OrderNodeEnum(String desc, Integer sort) {
        this.desc = desc;
        this.sort = sort;
    }
}

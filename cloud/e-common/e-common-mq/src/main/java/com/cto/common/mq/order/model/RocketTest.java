package com.cto.common.mq.order.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * 订单消息实体
 *
 * @author 张永伟
 * @since 2023/4/19
 */
@Data
public class RocketTest implements Serializable {


    private Long id;

    private String uuid;

    private String orderNo;

    private BigDecimal price;

    private Integer orderly;

}

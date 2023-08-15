package com.cto.common.mq.order.topic;

/**
 * RocketMq Order主题统一管理
 *
 * @author 张永伟
 * @since 2023/5/8
 */
public interface OrderTopicConstants {

    /**
     * af订单创建后续处理topic
     */
    String AFBASE_TOPIC_ORDER_CREATE_SUBSEQUENT = "order-order-create-subsequent";

    /**
     * af订单创建后续处理Listener对应的分组
     */
    String AFBASE_TOPIC_ORDER_CREATE_SUBSEQUENT_CONSUMER_GROUP = "order-order-create-subsequent-consumer-group";

}

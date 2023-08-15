package com.cto.common.mq.hrs.topic;

/**
 * RocketMq HRS主题统一管理
 *
 * @author 张永伟
 * @since 2023/5/8
 */
public interface HrsTopicConstants {

    /**
     * 发邮件topic
     */
    String TOPIC_GLOBAL_MAIL_SEND = "topic-global-mail-send";

    /**
     * 邮件发送消费者分组
     */
    String TOPIC_GLOBAL_MAIL_SEND_CONSUMER_GROUP = "topic-global-mail-consumer-group";

}

package com.efreight.common.mq.base.topic;

/**
 * RocketMq HRS主题统一管理
 *
 * @author 张永伟
 * @since 2023/5/8
 */
public interface BaseTopicConstants {

    /**
     * 发邮件topic
     */
    String TOPIC_GLOBAL_MAIL_SEND = "topic-global-mail-send";

    /**
     * 邮件发送消费者分组
     */
    String TOPIC_GLOBAL_MAIL_SEND_CONSUMER_GROUP = TOPIC_GLOBAL_MAIL_SEND + "-consumer-group";


    /**
     * 发系统管理员邮件topic
     */
    String TOPIC_SYSTEM_MAIL_SEND = "topic-system-mail-send";

    /**
     * 邮件发送系统管理员消费者分组
     */
    String TOPIC_SYSTEM_MAIL_SEND_CONSUMER_GROUP = TOPIC_SYSTEM_MAIL_SEND + "-consumer-group";

}

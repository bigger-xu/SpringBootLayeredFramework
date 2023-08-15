package com.cto.common.mq.order.productor;

import com.cto.common.mq.order.model.RocketTest;
import com.cto.common.mq.order.topic.OrderTopicConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 订单相关生产者
 *
 * @author 张永伟
 * @since 2023/5/8
 */

@Component
@Slf4j
public class OrderMessageProducer {


    @Autowired
    private RocketMQTemplate rocketMqTemplate;

    /**
     * 订单创建后续操作消息
     *
     * @param rocketTest 订单数据对象
     * @return SendResult
     * @since 2023/5/8
     */

    public SendResult sendOrderCreateSubsequent(RocketTest rocketTest) {
        //写法一
        //设置keys是为了处理消息幂等性，不需要幂等的数据可以不设
        Message<RocketTest> message = MessageBuilder.withPayload(rocketTest).setHeader("KEYS", rocketTest.getUuid()).build();
        SendResult sendResultObj = rocketMqTemplate.syncSend(OrderTopicConstants.AFBASE_TOPIC_ORDER_CREATE_SUBSEQUENT, message);
        log.info("RocketMq发送订单后续处理消息-->方法名:【sendOrderCreateSubsequent】-->  sendResult = {}", sendResultObj);
        return sendResultObj;
        //同步消息
        //return rocketMqTemplate.syncSend(RocketMqTopicConstants.AFBASE_TOPIC_ORDER_CREATE_SUBSEQUENT, message);
        //异步消息
        //return rocketMqTemplate.asyncSend(RocketMqTopicConstants.AFBASE_TOPIC_ORDER_CREATE_SUBSEQUENT, message, new SendCallback() {
        //    @Override
        //    public void onSuccess(SendResult sendResult) {
        //
        //    }
        //
        //    @Override
        //    public void onException(Throwable e) {
        //
        //    }
        //});

        //单向发送，没返回值的消息
        //rocketMqTemplate.sendOneWay(RocketMqTopicConstants.AFBASE_TOPIC_ORDER_CREATE_SUBSEQUENT, message);
        //同步延时消息，异步延时消息和上面的异步一样，只不过多了俩参数
        //return rocketMqTemplate.syncSend(RocketMqTopicConstants.AFBASE_TOPIC_ORDER_CREATE_SUBSEQUENT, message, 1000, 2);
        //事务消息
        //return rocketMqTemplate.sendMessageInTransaction(RocketMqTopicConstants.AFBASE_TOPIC_ORDER_CREATE_SUBSEQUENT, message, null);


        //写法二  对应consumer的写法二 主要是消息Message的区别
        //SendResult sendResultObj = rocketMqTemplate.syncSend(RocketMqTopicConstants.AFBASE_TOPIC_ORDER_CREATE_SUBSEQUENT, afOrderMqDTO);
        //log.info("RocketMq发送订单后续处理消息-->方法名:【sendOrderCreateSubsequent】-->  sendResult = {}", sendResultObj);
        //return rocketMqTemplate.syncSend(RocketMqTopicConstants.AFBASE_TOPIC_ORDER_CREATE_SUBSEQUENT, message);
    }

}

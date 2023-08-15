package com.cto.hrs.mq.comsumer;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSONObject;
import com.cto.common.message.model.MailMessageInfo;
import com.cto.common.mq.hrs.topic.HrsTopicConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 张永伟
 * @since 2023/5/8
 */

@Component
@Slf4j
@RocketMQMessageListener(topic = HrsTopicConstants.TOPIC_GLOBAL_MAIL_SEND, consumerGroup = HrsTopicConstants.TOPIC_GLOBAL_MAIL_SEND_CONSUMER_GROUP)
public class MailSendConsumerListener implements RocketMQListener<MessageExt> {

    public static final String SEND_MAIL_LOCK_KEY = "lock:mail:send:";

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public void onMessage(MessageExt message) {
        String keys = message.getKeys();
        //通过keys对应的orderId来校验幂等，防止重复消费
        RLock lock = redissonClient.getLock(SEND_MAIL_LOCK_KEY + keys);
        try {
            boolean b = lock.tryLock(1, TimeUnit.MINUTES);
            if (b) {
                //mq在信息发送的时候会对obj进行json序列化，默认方式，所以直接json对象
                String body = new String(message.getBody(), StandardCharsets.UTF_8);
                MailMessageInfo mailMessageInfo = JSONObject.parseObject(body, MailMessageInfo.class);
                log.info("邮件发送收到消息 --> 参数:message = {}", mailMessageInfo);
                //根据类型发送邮件
                log.info("发送邮件");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }

    }
}

//写法二
//public class OrderCreateSubsequentConsumerListener implements RocketMQListener<AfOrderMqDTO> {
//    @Override
//    public void onMessage(AfOrderMqDTO message) {
//        log.info("处理剩下的逻辑,afOrderMqDTO = {}", message);
//    }
//}

package com.efreight.base.comsumer;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson2.JSONObject;
import com.efreight.common.message.MailSendService;
import com.efreight.common.message.model.MailSystemMessageInfo;
import com.efreight.common.mq.base.topic.BaseTopicConstants;
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
@RocketMQMessageListener(topic = BaseTopicConstants.TOPIC_SYSTEM_MAIL_SEND,
        consumerGroup = BaseTopicConstants.TOPIC_SYSTEM_MAIL_SEND_CONSUMER_GROUP, maxReconsumeTimes = 3)
public class MailSysSendConsumerListener implements RocketMQListener<MessageExt> {

    public static final String SEND_MAIL_LOCK_KEY = "lock:mail:sys:send:";

    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private MailSendService mailSendService;

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
                MailSystemMessageInfo mailMessageInfo = JSONObject.parseObject(body, MailSystemMessageInfo.class);
                log.info("邮件系统发送收到消息 --> 参数:message = {}", mailMessageInfo);
                mailSendService.sendSystemMessage(mailMessageInfo.getReceiverList(), mailMessageInfo.getSubject(),
                                                  mailMessageInfo.getContent(), mailMessageInfo.getFileMaps(), mailMessageInfo.getImgMap(),false
                );
            }
        } catch (RuntimeException e) {
            log.error("系统邮件发送异常：", e);
        } catch (InterruptedException e) {
            log.error("系统邮件发送异常：", e);
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }
}
package com.efreight.base.comsumer;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson2.JSONObject;
import com.efreight.common.message.MailSendService;
import com.efreight.common.message.model.MailMessageInfo;
import com.efreight.common.mq.base.topic.BaseTopicConstants;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import org.springframework.stereotype.Component;

/**
 * @author 张永伟
 * @since 2023/5/8
 */

@Component
@Slf4j
@RocketMQMessageListener(topic = BaseTopicConstants.TOPIC_GLOBAL_MAIL_SEND,
                         consumerGroup = BaseTopicConstants.TOPIC_GLOBAL_MAIL_SEND_CONSUMER_GROUP, maxReconsumeTimes = 3)
public class MailSendConsumerListener implements RocketMQListener<MessageExt> {
    
    public static final String SEND_MAIL_LOCK_KEY = "lock:mail:send:";
    
    @Resource
    private RedissonClient redissonClient;
    
    @Resource
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
                MailMessageInfo mailMessageInfo = JSONObject.parseObject(body, MailMessageInfo.class);
                log.info("邮件发送收到消息 --> 参数:message = {}", mailMessageInfo);
                //发送信息入库
               
                //发送邮件
                mailSendService.sendHtmlMailNewForHrs(mailMessageInfo.getMailSendUserInfo(),
                                                      mailMessageInfo.getReceiverList(),
                                                      mailMessageInfo.getCcUserList(),
                                                      mailMessageInfo.getBccUserList(),
                                                      mailMessageInfo.getSubject(),
                                                      mailMessageInfo.getContent(),
                                                      mailMessageInfo.getFileMaps(),
                                                      mailMessageInfo.getImgMap());
                //修改发送状态
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

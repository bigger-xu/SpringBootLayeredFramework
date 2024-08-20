package com.efreight.common.i18n;

import com.efreight.common.utils.MessageUtil;
import jakarta.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author 张永伟
 * @since 2023/7/3
 */
@Component
public class ApplicationEvent implements ApplicationListener<ContextRefreshedEvent> {
    @Resource
    protected MessageSource messageSource;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        MessageUtil.setMessageSource(messageSource);
    }
}
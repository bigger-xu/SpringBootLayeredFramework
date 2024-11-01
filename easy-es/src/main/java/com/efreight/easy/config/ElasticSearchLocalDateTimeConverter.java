package com.efreight.easy.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.elasticsearch.core.mapping.PropertyValueConverter;
import org.springframework.stereotype.Component;

/**
 * @author ZhangYongWei
 * @since 2024/11/1
 */
@Component
@Slf4j
public class ElasticSearchLocalDateTimeConverter implements PropertyValueConverter {
    
    // 使用系统默认时区
    private static final ZoneId ZONE_ID = ZoneId.systemDefault();
    
    @Override
    public Object write(Object value) {
        if (value instanceof LocalDateTime localDateTime) {
            Instant instant = localDateTime.atZone(ZONE_ID).toInstant();
            long timestamp = instant.toEpochMilli();
            log.info("将 LocalDateTime [{}] 转换为时间戳 [{}]", localDateTime, timestamp);
            return timestamp;
        } else {
            String errorMessage = String.format("写入操作接收到非 LocalDateTime 值: [%s], 类型: [%s]", value, value.getClass().getName());
            log.error(errorMessage);
            throw new IllegalStateException(errorMessage);
        }
    }
    
    @Override
    public Object read(Object value) {
        if (value instanceof Long timestamp) {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZONE_ID);
            log.info("将时间戳 [{}] 转换为 LocalDateTime [{}]", timestamp, localDateTime);
            return localDateTime;
        } else {
            String errorMessage = String.format("无法将值 '值: [%s], 类型: [%s] 解析为 LocalDateTime", value,value.getClass().getName());
            log.error(errorMessage);
            throw new IllegalStateException(errorMessage);
        }
    }
}

package com.efreight.easy.entity;

import java.time.LocalDateTime;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author ZhangYongWei
 * @since 2024/10/24
 */
@Document(indexName = "system_log")
@Data
public class SystemLog {
    
    /** ID */
    @Id
    @Field(store = true, index = false, type = FieldType.Long)
    private Long id;
    
    /** 签约公司ID */
    @Field(store = true, type = FieldType.Keyword)
    private Long orgId;
    
    /** 签约公司名称 */
    @Field(store = true, type = FieldType.Keyword)
    private String orgName;
    
    /** 模块 */
    @Field(store = true, type = FieldType.Keyword)
    private String module;
    
    /** 菜单 */
    @Field(store = true, type = FieldType.Keyword)
    private String menu;
    
    /** 功能 */
    @Field(store = true, type = FieldType.Keyword)
    private String function;
    
    /** traceId */
    @Field(store = true, type = FieldType.Keyword)
    private String traceId;
    
    /** 内容 */
    @Field(analyzer = "ik_smart", store = true, searchAnalyzer = "ik_smart", type = FieldType.Text)
    private String content;
    
    /** 操作人 */
    @Field(store = true, type = FieldType.Keyword)
    private String createName;
    
    /** 操作时间 */
    @Field(store = true, type = FieldType.Date, format = { DateFormat.date_hour_minute_second, DateFormat.date_hour_minute_second_fraction, DateFormat.date_hour_minute_second_millis })
    private LocalDateTime createTime;
}

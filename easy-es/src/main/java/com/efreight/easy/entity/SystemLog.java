package com.efreight.easy.entity;

import lombok.Data;

import org.springframework.data.annotation.Id;
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
    
    //@Id 文档主键 唯一标识
    @Id
    //@Field 每个文档的字段配置（类型、是否分词、是否存储、分词器 ）
    @Field(store = true, index = false, type = FieldType.Long)
    private Integer id;
    
    @Field(analyzer = "ik_smart", store = true, searchAnalyzer = "ik_smart", type = FieldType.Text)
    private String title;
    
    @Field(analyzer = "ik_smart", store = true, searchAnalyzer = "ik_smart", type = FieldType.Text)
    private String content;
    
    @Field(store = true, type = FieldType.Double)
    private Double price;
}

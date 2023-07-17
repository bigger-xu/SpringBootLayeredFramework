package com.cto.freemarker.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author Bigger-Xu
 * @version v1.0.1
 * @date 2020/9/1 22:01
 */
@Document(indexName = "594cto_content")
public class EsContent {
    @Id
    private Long id;
    @Field(type = FieldType.Keyword)
    private String uuid;
    @Field(type = FieldType.Keyword)
    private String channelId;
    @Field(type = FieldType.Keyword)
    private String catUuid;
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String title;
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String keywords;
    @Field(type = FieldType.Keyword)
    private String categoryName;
    @Field(type = FieldType.Keyword)
    private String nickName;
    @Field(type = FieldType.Keyword)
    private Date createTime;
    @Field(type = FieldType.Keyword)
    private String clickCount;

    public EsContent() {
    }

    public EsContent(Long id, String uuid, String channelId, String catUuid, String title, String keywords, String categoryName, String nickName, Date createTime, String clickCount) {
        this.id = id;
        this.uuid = uuid;
        this.channelId = channelId;
        this.catUuid = catUuid;
        this.title = title;
        this.keywords = keywords;
        this.categoryName = categoryName;
        this.nickName = nickName;
        this.createTime = createTime;
        this.clickCount = clickCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getCatUuid() {
        return catUuid;
    }

    public void setCatUuid(String catUuid) {
        this.catUuid = catUuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getClickCount() {
        return clickCount;
    }

    public void setClickCount(String clickCount) {
        this.clickCount = clickCount;
    }
}

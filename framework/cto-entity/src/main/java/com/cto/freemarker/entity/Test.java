package com.cto.freemarker.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

/**
 * @author Zhang Yongei
 * @version 1.0
 * @date 2019-10-11
 */
public class Test implements Serializable {

    @Field("uuid")
    private String uuid;
    @Field("keywords")
    private String keywords;
    @Field("title")
    private String title;
    @Field("saveTime")
    private Date saveTime;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
    }

    @Override
    public String toString() {
        return "Test{" +
                "uuid='" + uuid + '\'' +
                ", keywords='" + keywords + '\'' +
                ", title='" + title + '\'' +
                ", saveTime=" + saveTime +
                '}';
    }
}

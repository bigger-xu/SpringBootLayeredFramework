package com.cto.freemarker.entity.base;

import java.io.Serializable;

/**
 * @author Zhang Yongei
 * @version 1.0
 * @date 2019-03-25
 */
public class BaseEntity implements Serializable {
    private Integer pageNum = 1;
    private Integer pageSize = 10;
    private Integer startIndex;
    private String sortStr = "id desc";

    public Integer getStartIndex() {
        return (pageNum - 1) * pageSize < 0 ? 0 : (pageNum - 1) * pageSize;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortStr() {
        return sortStr;
    }

    public void setSortStr(String sortStr) {
        this.sortStr = sortStr;
    }
}

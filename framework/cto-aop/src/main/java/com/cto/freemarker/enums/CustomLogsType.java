package com.cto.freemarker.enums;

/**
 * @author Zhang Yongei
 * @version 1.0
 * @date 2019-11-19
 */
public enum CustomLogsType {
    /**
     * 操作类型
     */
    UNKNOWN("unknown"),
    DELETE("delete"),
    SELECT("select"),
    UPDATE("update"),
    INSERT("insert"),
    INSERT_UPDATE("insertOrUpdate");

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    CustomLogsType(String type) {
        this.value = type;
    }
}

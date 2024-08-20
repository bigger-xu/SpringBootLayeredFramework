package com.efreight.common.enums;

/**
 * 识别号规范.
 * <p>
 * Company: 翌飞锐特
 * <p>
 *
 * @author liyulong
 * @version 1.0.0
 **/
public enum InternalNumberStandardEnum {

    /**
     * 公司三字码+订单号
     */
    COMPANY_THREE(1, "公司三字码+订单号"),

    /**
     * 自定义
     */
    CUSTOM_DEFINITION(2, "自定义"),

    ;


    private Integer code;

    private String name;

    InternalNumberStandardEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

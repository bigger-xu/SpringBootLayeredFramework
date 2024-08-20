package com.efreight.common.enums;

/**
 * 业务范畴
 **/
public enum BusinessScopeEnum {
    
    AE("AE", "AE订单"),
    AI("AI", "AI订单"),
    TE("TE", "TE订单"),
    TI("TI", "TI订单"),
    SE("SE", "SE订单"),
    SI("SI", "SI订单"),
    LC("LC", "LC订单"),
    IO("IO", "IO订单"),
    ;
    
    private String code;
    
    private String name;
    
    BusinessScopeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}

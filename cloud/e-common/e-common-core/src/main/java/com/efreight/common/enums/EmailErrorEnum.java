package com.efreight.common.enums;

import lombok.Getter;

/**
 * 邮件异常枚举
 *
 * @author ZhangYongWei
 * @since 2023/12/27
 */
@Getter
public enum EmailErrorEnum {
    PASSWORD("密码错误"),
    
    CHAR("字符集错误"),
    
    RECEIVER("收件人为空"),
    ;
    
    private final String label;
    
    EmailErrorEnum(String label) {
        this.label = label;
    }
}

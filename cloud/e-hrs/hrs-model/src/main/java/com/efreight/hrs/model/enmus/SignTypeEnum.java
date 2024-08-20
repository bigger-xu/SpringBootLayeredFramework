package com.efreight.hrs.model.enmus;

import lombok.Getter;

/**
 * @author 马玉龙
 * @since 2023/8/18
 * 用户签章用途枚举
 */
@Getter
public enum SignTypeEnum {
    /**
     * 安检申报
     */

    SECURITY_DECLARATION("安检申报"),
    /**
     * 发送邮件
     */
    SEND_EMAIL("发送邮件"),
    /**
     * 运单
     */
    WAY_BILL("运单");
    
    private final String value;

    SignTypeEnum(String value) {
        this.value = value;
    }
}

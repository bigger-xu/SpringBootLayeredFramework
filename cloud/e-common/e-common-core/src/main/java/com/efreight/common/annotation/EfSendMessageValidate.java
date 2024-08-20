package com.efreight.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 发送报文时校验参数信息的注解
 *
 * @author 张永伟
 * @since 2023/5/15
 */
@Documented
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EfSendMessageValidate {

    /**
     * 是否必填
     */
    boolean required() default false;

    /**
     * 错误描述信息
     */
    String description() default "";
}

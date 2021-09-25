package com.cto.freemarker.entity;

import com.cto.freemarker.enums.CustomLogsType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Zhang Yongwei
 * @version 1.0
 * @date 2019-11-20
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomLogs {
    /**
     * 日志名称
     * @return String
     */
    String description() default "";

    /**
     * 日志类型
     * @return String
     */
    CustomLogsType type() default CustomLogsType.UNKNOWN;
}

package com.efreight.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 千位符转换
 *
 * @author Libiao
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ThousandConvert {

    /**
     * 前后缀是否区分大小写
     * false：不忽略大小写
     * true：忽略大小写
     */
    boolean ignoreCase() default false;

    /**
     * 排除前缀
     */
    String prefix() default "";

    /**
     * 排除后缀
     */
    String suffix() default "";

}

package com.efreight.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志对比注解
 *
 * @author ZhangYongWei
 * @since 2024/4/30
 */

@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CustomLogProperty {
    
    String value() default "";
}

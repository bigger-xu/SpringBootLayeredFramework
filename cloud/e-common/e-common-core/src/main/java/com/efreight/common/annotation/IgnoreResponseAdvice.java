package com.efreight.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记为无需全局返回拦截，添加该注解，全局Advice会跳过封装处理
 *
 * @author 张永伟
 * @since 2023/4/24
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreResponseAdvice {

}
package com.efreight.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记为Feign接口,只用于方法标记，无任何逻辑意义
 *
 * @author 张永伟
 * @since 2023/4/24
 */
@Documented
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.SOURCE)
public @interface MarkAsFeignService {

}

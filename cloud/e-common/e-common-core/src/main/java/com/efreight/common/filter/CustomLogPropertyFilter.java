package com.efreight.common.filter;

import java.lang.reflect.Field;

import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.filter.NameFilter;
import com.alibaba.fastjson2.filter.PropertyPreFilter;
import com.efreight.common.annotation.CustomLogProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * fastjson 日志JSON转化工具
 * @author ZhangYongWei
 * @since 2024/4/30
 */
@Slf4j
public class CustomLogPropertyFilter implements PropertyPreFilter, NameFilter {
    
    /**
     * 通过CustomLogProperty注解字段名称转化
     */
    @Override
    public String process(Object obj, String fieldName, Object o1) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            // 判断字段上是否存在OaProperty注解
            boolean flag = field.isAnnotationPresent(CustomLogProperty.class);
            if (flag) {
                CustomLogProperty customLogProperty = field.getAnnotation(CustomLogProperty.class);
                String oaFieldName = customLogProperty.value();
                if (StringUtils.isNotBlank(oaFieldName)){
                    return oaFieldName;
                }
            }
        } catch (NoSuchFieldException e) {
            log.error("对象obj：{}，通过OaProperty注解字段名称转化失败", obj.getClass().getName(), e);
        }
        return fieldName;
    }
    
    /**
     * 只序列化带CustomLogProperty注解的字段
     *
     * @return boolean
     * @since 2024/8/16
     */
    @Override
    public boolean process(JSONWriter writer, Object source, String name) {
        boolean flag = false;
        try {
            Field field = source.getClass().getDeclaredField(name);
            // 判断字段上是否存在OaProperty注解
            flag = field.isAnnotationPresent(CustomLogProperty.class);
        } catch (NoSuchFieldException e) {
            log.error("对象obj：{}，只序列化CustomLogProperty注解字段失败", source.getClass().getName(), e);
        }
        return flag;
    }
}

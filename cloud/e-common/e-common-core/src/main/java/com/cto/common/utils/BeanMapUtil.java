package com.cto.common.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import lombok.SneakyThrows;

/**
 * @author Libiao
 */
public class BeanMapUtil {

    /**
     * 对象转Map
     */
    @SneakyThrows
    public static Map<String, Object> beanToMap(Object object) {
        Map<String, Object> map = new HashMap<>();
        BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter != null ? getter.invoke(object) : null;
            map.put(key, value);
        }
        return map;
    }

    /**
     * map转对象
     */
    @SneakyThrows
    public static <T> T mapToBean(Map<String, Object> map, Class<T> beanClass) {
        T object = beanClass.newInstance();
        BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            Method setter = property.getWriteMethod();
            if (setter != null) {
                setter.invoke(object, map.get(property.getName()));
            }
        }
        return object;
    }
}

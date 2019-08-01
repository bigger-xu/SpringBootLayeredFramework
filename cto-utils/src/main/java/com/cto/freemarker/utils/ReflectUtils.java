package com.cto.freemarker.utils;

import java.lang.reflect.Field;

/**
 * @author Zhang Yongwei
 * @version 1.0
 * @date 2019-03-27
 */
public class ReflectUtils {
    private ReflectUtils() {
    }

    public static Object setIdKeyValue(Object obj, String fieldName, String value) throws Exception {
        Class<?> cls = obj.getClass();
        Field field = null;

        try {
            field = getDeclaredField(obj, fieldName);
        } catch (Exception var7) {
            var7.printStackTrace();
            System.out.println("没有这个字段：" + fieldName);
        }

        if (field == null) {
            return null;
        } else {
            field.setAccessible(true);
            String fieldNameType = field.getType().getSimpleName();
            if (!fieldNameType.contains("Integer") && !fieldNameType.contains("Long")) {
                field.set(obj, value);
            }

            Object val = field.get(obj);
            field.setAccessible(false);
            return val;
        }
    }

    public static Field getDeclaredField(Object object, String fieldName) {
        Field field = null;
        Class clazz = object.getClass();

        while(clazz != Object.class) {
            try {
                field = clazz.getDeclaredField(fieldName);
                return field;
            } catch (Exception var5) {
                clazz = clazz.getSuperclass();
            }
        }

        return null;
    }

    public static void setFieldValue(Object object, String fieldName, Object value) {
        Field field = getDeclaredField(object, fieldName);
        field.setAccessible(true);

        try {
            field.set(object, value);
            field.setAccessible(false);
        } catch (IllegalArgumentException var5) {
            var5.printStackTrace();
        } catch (IllegalAccessException var6) {
            var6.printStackTrace();
        }

    }
}

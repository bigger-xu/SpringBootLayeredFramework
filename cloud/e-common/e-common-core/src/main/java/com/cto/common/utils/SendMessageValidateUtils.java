package com.cto.common.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

import com.cto.common.annotation.EfSendMessageValidate;

/**
 * 发送报文校验工具类
 *
 * @author 张永伟
 * @since 2023/5/15
 */
public class SendMessageValidateUtils {

    public static String checkObjectParams(Object o,
            Class voClass) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        StringBuilder sb = new StringBuilder();
        Field[] fields = voClass.getDeclaredFields();
        for (Field field : fields) {
            EfSendMessageValidate annotation = field.getAnnotation(EfSendMessageValidate.class);
            if (Objects.nonNull(annotation)) {
                if (annotation.required()) {
                    field.setAccessible(true);
                    PropertyDescriptor pd = new PropertyDescriptor(field.getName(), voClass);
                    Method getMethod = pd.getReadMethod();//获得get方法
                    Object val = getMethod.invoke(o);//执行get方法返回一个Object
                    if (Objects.isNull(val)) {
                        sb.append(annotation.description()).append("\n");
                    }
                }
            }
        }
        return sb.toString();
    }
}

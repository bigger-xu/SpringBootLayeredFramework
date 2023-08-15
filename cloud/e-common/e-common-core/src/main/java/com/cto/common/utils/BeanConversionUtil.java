package com.cto.common.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 *
 **/
@Slf4j
@UtilityClass
public class BeanConversionUtil {

    /**
     * 接收不为空的数据
     * 实体类中tableField注解为false的不会覆盖
     *
     * @param bean1 赋值的bean
     * @param bean2 接收值的bean
     * @param map
     */
    public static void conversionValue(Object bean1, Object bean2, HashMap<String, String> map) {
        Field[] bean1Fields = bean1.getClass().getDeclaredFields();
        //        Field[] bean2Fields = bean2.getClass().getDeclaredFields();
        Class<?> bean2Class = bean2.getClass();
        Object o = JSON.toJSON(bean2);
        JSONObject jsonObject = new JSONObject();
        if (o instanceof JSONObject) {
            jsonObject = (JSONObject) o;
        }
        for (Field bean1Field : bean1Fields) {
            //赋值字段
            bean1Field.setAccessible(true);
            try {
                //接收字段
                Field declaredField = null;
                String name = bean1Field.getName();
                if (CollectionUtils.isNotEmpty(map) && map.get(name) != null) {
                    declaredField = bean2Class.getDeclaredField(map.get(name));
                } else if (jsonObject.containsKey(name)) {
                    declaredField = bean2Class.getDeclaredField(name);
                }


                /*for (Field bean2Field : bean2Fields) {
                    bean2Field.setAccessible(true);
                    if(name.equals(bean2Field.getName())){
                        declaredField = bean2Field;
                    }
                }*/

                if (declaredField != null) {
                    declaredField.setAccessible(true);
                    boolean tableField = bean1Field.getDeclaredAnnotation(TableField.class) == null || bean1Field.getDeclaredAnnotation(
                            TableField.class).exist();
                    boolean tableField1 = declaredField.getDeclaredAnnotation(
                            TableField.class) == null || declaredField.getDeclaredAnnotation(TableField.class).exist();
                    if (tableField && tableField1) {
                        Object value = bean1Field.get(bean1);
                        conversionType(declaredField, value, bean2);
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    /**
     * 接收不为空的数据
     *
     * @param json  赋值的json
     * @param bean2 接收值的bean
     * @param map
     */
    public static void conversionJSONValue(JSONObject json, Object bean2, HashMap<String, String> map) {
        Class<?> bean2Class = bean2.getClass();
        Object o = JSON.toJSON(bean2);
        JSONObject jsonObject = new JSONObject();
        if (o instanceof JSONObject) {
            jsonObject = (JSONObject) o;
        }
        for (String name : json.keySet()) {

            try {
                //                name = StrUtil.lowerFirst(name);首字母转小写
                //接收字段
                Field declaredField = null;
                if (CollectionUtils.isNotEmpty(map) && map.get(name) != null) {
                    declaredField = bean2Class.getDeclaredField(map.get(name));
                } else if (jsonObject.containsKey(name)) {
                    declaredField = bean2Class.getDeclaredField(name);
                }

                if (declaredField != null) {
                    declaredField.setAccessible(true);
                    Object value = json.get(name);
                    conversionType(declaredField, value, bean2);
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    /**
     * 覆盖赋值bean和接收bean的数据字段都不为空，任何一方数据字段为空的将不会进行覆盖
     *
     * @param bean1 赋值的bean
     * @param bean2 接收值的bean
     * @param map
     */
    public static void conversionValueNotNull(Object bean1, Object bean2, HashMap<String, String> map) {
        Field[] bean1Fields = bean1.getClass().getDeclaredFields();
        Class<?> bean2Class = bean2.getClass();
        Object o = JSON.toJSON(bean2);
        JSONObject jsonObject = new JSONObject();
        if (o instanceof JSONObject) {
            jsonObject = (JSONObject) o;
        }
        for (Field bean1Field : bean1Fields) {
            bean1Field.setAccessible(true);
            try {
                Field declaredField = null;
                String name = bean1Field.getName();
                if (CollectionUtils.isNotEmpty(map) && map.get(name) != null) {
                    declaredField = bean2Class.getDeclaredField(map.get(name));
                } else if (jsonObject.containsKey(name)) {
                    declaredField = bean2Class.getDeclaredField(name);
                }
                //字段在接收bean中存在并且值不为空
                if (declaredField != null) {
                    declaredField.setAccessible(true);
                    boolean tableField = bean1Field.getDeclaredAnnotation(TableField.class) == null || bean1Field.getDeclaredAnnotation(
                            TableField.class).exist();
                    boolean tableField1 = declaredField.getDeclaredAnnotation(
                            TableField.class) == null || declaredField.getDeclaredAnnotation(TableField.class).exist();
                    if (tableField && tableField1) {
                        Object value1 = bean1Field.get(bean1);
                        if (declaredField.get(bean2) != null) {
                            conversionType(declaredField, value1, bean2);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 将能匹配的数据赋NULL空值
     *
     * @param bean1 赋值的bean
     * @param bean2 接收值的bean
     * @param map   优先获取map中的对应关系
     */
    public static void conversionNull(Object bean1, Object bean2, HashMap<String, String> map) {
        Field[] bean1Fields = bean1.getClass().getDeclaredFields();
        Class<?> bean2Class = bean2.getClass();
        Object o = JSON.toJSON(bean2);
        JSONObject jsonObject = new JSONObject();
        if (o instanceof JSONObject) {
            jsonObject = (JSONObject) o;
        }
        for (Field bean1Field : bean1Fields) {
            bean1Field.setAccessible(true);
            try {
                Field declaredField = null;
                String name = bean1Field.getName();
                if (CollectionUtils.isNotEmpty(map) && map.get(name) != null) {
                    declaredField = bean2Class.getDeclaredField(map.get(name));
                } else if (jsonObject.containsKey(name)) {
                    declaredField = bean2Class.getDeclaredField(name);
                }
                if (declaredField != null) {
                    boolean tableField = bean1Field.getDeclaredAnnotation(TableField.class) == null || bean1Field.getDeclaredAnnotation(
                            TableField.class).exist();
                    if (tableField) {
                        declaredField.setAccessible(true);
                        declaredField.set(bean2, null);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 接收不为空的数据
     * 实体类中tableField注解为false的不会覆盖
     *
     * @param bean1 赋值的bean
     * @param bean2 接收值的bean
     * @param map   只映射接收map中建立的对应字段
     */
    public static void conversionValue2(Object bean1, Object bean2, HashMap<String, String> map) {
        Field[] bean1Fields = bean1.getClass().getDeclaredFields();
        Class<?> bean2Class = bean2.getClass();
        for (Field bean1Field : bean1Fields) {
            //赋值字段
            bean1Field.setAccessible(true);
            try {
                //接收字段
                Field declaredField = null;
                String name = bean1Field.getName();
                if (CollectionUtils.isNotEmpty(map) && map.get(name) != null) {
                    declaredField = bean2Class.getDeclaredField(map.get(name));
                } else {
                    continue;
                }

                if (declaredField != null) {
                    declaredField.setAccessible(true);
                    boolean tableField = bean1Field.getDeclaredAnnotation(TableField.class) == null || bean1Field.getDeclaredAnnotation(
                            TableField.class).exist();
                    boolean tableField1 = declaredField.getDeclaredAnnotation(
                            TableField.class) == null || declaredField.getDeclaredAnnotation(TableField.class).exist();
                    if (tableField && tableField1) {
                        Object value = bean1Field.get(bean1);
                        conversionType(declaredField, value, bean2);
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    public static void conversionToUppercase(Object bean) {
        Field[] bean1Fields = bean.getClass().getDeclaredFields();
        for (Field bean1Field : bean1Fields) {
            //赋值字段
            bean1Field.setAccessible(true);
            try {
                if (bean1Field.getType() == String.class) {
                    boolean tableField = bean1Field.getDeclaredAnnotation(TableField.class) == null || bean1Field.getDeclaredAnnotation(
                            TableField.class).exist();
                    Object value = bean1Field.get(bean);
                    if (value != null && !"".equals(value) && tableField) {
                        bean1Field.set(bean, value.toString().toUpperCase());
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    public static void conversionType(Field fieldTw, Object value, Object bean) throws IllegalAccessException {
        if (value != null && fieldTw != null) {
            if (fieldTw.getType() == Integer.class) {
                fieldTw.set(bean, Integer.valueOf(value.toString()));
            } else if (fieldTw.getType() == BigDecimal.class) {
                fieldTw.set(bean, new BigDecimal(value.toString()));
            } else if (fieldTw.getType() == Double.class) {
                fieldTw.set(bean, new Double(value.toString()));
            } else if (fieldTw.getType() == Long.class) {
                fieldTw.set(bean, Long.valueOf(value.toString()));
            } else if (fieldTw.getType() == LocalDateTime.class) {
                if (value instanceof Date) {
                    fieldTw.set(bean, ((Date) value).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                } else if (value.toString().contains("T")) {
                    fieldTw.set(bean, value);
                } else {
                    fieldTw.set(bean, LocalDateTime.parse(value.toString().replace("-", "").replace(":", "").replace(" ", ""),
                            DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
                }
            } else if (fieldTw.getType() == LocalDate.class) {
                fieldTw.set(bean, LocalDate.parse(value.toString().replace("-", ""), DateTimeFormatter.ofPattern("yyyyMMdd")));
            } else if (fieldTw.getType() == Boolean.class) {
                fieldTw.set(bean, Boolean.valueOf(String.valueOf(value)));
            } else if (fieldTw.getType() == value.getClass()) {
                fieldTw.set(bean, value);
            } else {
                fieldTw.set(bean, value.toString());
            }
        }
    }
}

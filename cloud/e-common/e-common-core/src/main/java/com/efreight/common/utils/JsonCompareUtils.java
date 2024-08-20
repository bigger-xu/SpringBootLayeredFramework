package com.efreight.common.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

/**
 * 对比JSON工具类， 用于记录操作日志变动
 * @author ZhangYongWei
 * @since 2024/4/30
 */
public class JsonCompareUtils {
    
    /**
     * json转map
     */
    public static void analysisJson(Object obj, Map<String, Object> values, String prefix) {
        if (obj == null || values == null) {
            return;
        }
        
        if(prefix == null){
            prefix = "";
        }
        
        if (obj instanceof JSONArray) {
            JSONArray objArray = (JSONArray) obj;
            for (int i = 0; i < objArray.size(); i++) {
                analysisJson(objArray.get(i), values, prefix + "[" + i + "].");
            }
        // 如果为json对象
        } else if (obj instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) obj;
            for (String s : jsonObject.keySet()) {
                Object object = jsonObject.get(s);
                
                
                // 如果得到的是数组
                if (object instanceof JSONArray) {
                    JSONArray objArray = (JSONArray) object;
                    analysisJson(objArray, values, s);
                }
                // 如果key中是一个json对象
                else if (object instanceof JSONObject) {
                    StringBuilder currentPrefix = new StringBuilder();
                    if (!prefix.isEmpty()) {
                        currentPrefix.append(prefix).append(s);
                    } else {
                        currentPrefix.append(s);
                    }
                    currentPrefix.append(".");
                    analysisJson(object, values, currentPrefix.toString());
                }
                // 如果key中是其他
                else {
                    values.put(prefix + s, object);
                }
            }
        }
    }
    
    /**
     * 对比两个json的不同字段
     */
    public static String compareJsonObject(String oldJsonStr, String newJsonStr1) {
        //将字符串转换为json对象
        JSONObject oldJson = JSON.parseObject(oldJsonStr);
        JSONObject newJson = JSON.parseObject(newJsonStr1);
        //递归遍历json对象所有的key-value，将其封装成path:value格式进行比较
        Map<String, Object> oldMap = new LinkedHashMap<>();
        Map<String, Object> newMap = new LinkedHashMap<>();
        convertJsonToMap(oldJson, "", oldMap);
        convertJsonToMap(newJson, "", newMap);
        Map<String, Object> differenceMap = compareMap(oldMap, newMap);
        //将最终的比较结果把不相同的转换为json对象返回
        return convertMapToJson(differenceMap);
    }
    
    /**
     * 将json数据转换为map存储用于比较
     */
    private static void convertJsonToMap(Object json, String root, Map<String, Object> resultMap) {
        if (json instanceof JSONObject) {
            JSONObject jsonObject = ((JSONObject) json);
            for (Object key : jsonObject.keySet()) {
                Object value = jsonObject.get(key);
                String newRoot = "".equals(root) ? key + "" : root + "." + key;
                if (value instanceof JSONObject || value instanceof JSONArray) {
                    convertJsonToMap(value, newRoot, resultMap);
                } else {
                    resultMap.put(newRoot, value);
                }
            }
        } else if (json instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) json;
            for (int i = 0; i < jsonArray.size(); i++) {
                Object value = jsonArray.get(i);
                String newRoot = "".equals(root) ? "[" + i + "]" : root + ".[" + i + "]";
                if (value instanceof JSONObject || value instanceof JSONArray) {
                    convertJsonToMap(value, newRoot, resultMap);
                } else {
                    resultMap.put(newRoot, value);
                }
            }
        }
    }
    
    /**
     * 比较两个map，返回不同数据
     */
    private static Map<String, Object> compareMap(Map<String, Object> oldMap, Map<String, Object> newMap) {
        //遍历newMap，将newMap的不同数据装进oldMap，同时删除oldMap中与newMap相同的数据
        compareNewToOld(oldMap, newMap);
        //將旧的有新的沒有的数据封装数据结构存在旧的里面
        compareOldToNew(oldMap);
        return oldMap;
    }
    
    /**
     * 将旧的有新的没有的数据封装数据结构存在旧的里面
     */
    private static void compareOldToNew(Map<String, Object> oldMap) {
        //统一oldMap中newMap不存在的数据的数据结构，便于解析
        for (Entry<String, Object> item : oldMap.entrySet()) {
            String key = item.getKey();
            Object value = item.getValue();
            if (!(value instanceof Map)) {
                Map<String, Object> differenceMap = new HashMap<>();
                //differenceMap.put("oldValue", value);
                //differenceMap.put("newValue", "");
                differenceMap.put("value", value + "-> ");
                oldMap.put(key, differenceMap);
            }
        }
    }
    
    /**
     * 将新的map与旧的比较，并将数据统一存在旧的里面
     */
    private static void compareNewToOld(Map<String, Object> oldMap, Map<String, Object> newMap) {
        for (Entry<String, Object> item : newMap.entrySet()) {
            String key = item.getKey();
            Object newValue = item.getValue();
            Map<String, Object> differenceMap = new HashMap<>();
            if (oldMap.containsKey(key)) {
                Object oldValue = oldMap.get(key);
                if (newValue.equals(oldValue)) {
                    oldMap.remove(key);
                } else {
                    //differenceMap.put("oldValue", oldValue);
                    //differenceMap.put("newValue", newValue);
                    differenceMap.put("value", oldValue + "->" + newValue);
                    oldMap.put(key, differenceMap);
                }
            } else {
                //differenceMap.put("oldValue", "");
                //differenceMap.put("newValue", newValue);
                differenceMap.put("value", " ->" + newValue);
                oldMap.put(key, differenceMap);
            }
        }
    }
    
    /**
     * 将已经找出不同数据的map根据key的层级结构封装成json返回
     */
    private static String convertMapToJson(Map<String, Object> map) {
        JSONObject resultJSONObject = new JSONObject();
        for (Entry<String, Object> item : map.entrySet()) {
            String key = item.getKey();
            Object value = item.getValue();
            String[] paths = key.split("\\.");
            int i = 0;
            //用于深度标识对象
            Object remarkObject = null;
            int indexAll = paths.length - 1;
            while (i <= paths.length - 1) {
                String path = paths[i];
                if (i == 0) {
                    //初始化对象标识
                    if (resultJSONObject.containsKey(path)) {
                        remarkObject = resultJSONObject.get(path);
                    } else {
                        if (indexAll > i) {
                            if (paths[i + 1].matches("\\[[0-9]+]")) {
                                remarkObject = new JSONArray();
                            } else {
                                remarkObject = new JSONObject();
                            }
                            resultJSONObject.put(path, remarkObject);
                        } else {
                            resultJSONObject.put(path, value);
                        }
                    }
                    i++;
                    continue;
                }
                //匹配集合对象
                if (path.matches("\\[[0-9]+]")) {
                    int startIndex = path.lastIndexOf("[");
                    int endIndext = path.lastIndexOf("]");
                    int index = Integer.parseInt(path.substring(startIndex + 1, endIndext));
                    if (indexAll > i) {
                        if (paths[i + 1].matches("\\[[0-9]+]")) {
                            if (remarkObject != null) {
                                while (((JSONArray) remarkObject).size() <= index) {
                                    if (((JSONArray) remarkObject).size() == index) {
                                        ((JSONArray) remarkObject).add(index, new JSONArray());
                                    } else {
                                        ((JSONArray) remarkObject).add(null);
                                    }
                                }
                            }
                        } else {
                            if (remarkObject != null) {
                                while (((JSONArray) remarkObject).size() <= index) {
                                    if (((JSONArray) remarkObject).size() == index) {
                                        ((JSONArray) remarkObject).add(index, new JSONObject());
                                    } else {
                                        ((JSONArray) remarkObject).add(null);
                                    }
                                }
                            }
                        }
                        if (remarkObject != null) {
                            remarkObject = ((JSONArray) remarkObject).get(index);
                        }
                    } else {
                        if (remarkObject != null) {
                            while (((JSONArray) remarkObject).size() <= index) {
                                if (((JSONArray) remarkObject).size() == index) {
                                    ((JSONArray) remarkObject).add(index, value);
                                } else {
                                    ((JSONArray) remarkObject).add(null);
                                }
                            }
                        }
                    }
                } else {
                    if (indexAll > i) {
                        if (paths[i + 1].matches("\\[[0-9]+]")) {
                            if (remarkObject != null && !((JSONObject) remarkObject).containsKey(path)) {
                                ((JSONObject) remarkObject).put(path, new JSONArray());
                            }
                        } else {
                            if (remarkObject != null && !((JSONObject) remarkObject).containsKey(path)) {
                                ((JSONObject) remarkObject).put(path, new JSONObject());
                            }
                        }
                        if (remarkObject != null) {
                            remarkObject = ((JSONObject) remarkObject).get(path);
                        }
                    } else {
                        if (remarkObject != null) {
                            ((JSONObject) remarkObject).put(path, value);
                        }
                    }
                }
                i++;
            }
        }
        return JSON.toJSONString(resultJSONObject);
    }
}

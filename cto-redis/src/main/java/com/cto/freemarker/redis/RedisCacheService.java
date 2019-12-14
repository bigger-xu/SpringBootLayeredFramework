package com.cto.freemarker.redis;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface RedisCacheService {
    /**
     * 插入String
     * @param key
     * @param value
     */
    void setCacheString(String key, String value);
    /**
     * 获取String
     * @param key
     */
    String getCacheString(String key);

    /**
     * 插入Object
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    <T> ValueOperations<String, T> setCacheObject(String key, T value);

    /**
     * 插入带有过期时间的Object
     * @param key
     * @param value
     * @param num
     * @param time
     * @param <T>
     * @return
     */
    <T> ValueOperations<String, T> setCacheObject(String key, T value, long num, TimeUnit time);

    /**
     * 获取Object
     * @param key
     * @param <T>
     * @return
     */
    <T> T getCacheObject(String key);

    /**
     * 插入list
     * @param key
     * @param dataList
     * @param <T>
     * @return
     */
    <T> ListOperations<String, T> setCacheList(String key, List<T> dataList);

    /**
     * 获取list
     * @param key
     * @param <T>
     * @return
     */
    <T> List<T> getCacheList(String key);

    /**
     * 插入map
     * @param key
     * @param dataMap
     * @param <T>
     * @return
     */
    <T> HashOperations<String, String, T> setCacheMap(String key, Map<String, T> dataMap);
    
    /**
     * 插入带有过期时间的map
     * @param key
     * @param dataMap
     * @param num
     * @param time
     * @param <T>
     * @return
     */
    <T> HashOperations<String, String, T> setCacheMap(String key, Map<String, T> dataMap, long num, TimeUnit time);

    /**
     * 获取map
     * @param key
     * @param <T>
     * @return
     */
    <T> Map<Object, Object> getCacheMap(String key);

    boolean isRequestOutInterface(String key, Long time);

    /**
     * 设置过期 即删除
     * @param key
     * @return
     */
    boolean setFailure(String key);

    /**
     * 删除map中的某一个值
     * @param key
     * @param loanType
     */
    void delete(String key, String loanType);
}

package com.cto.freemarker.service.redis;

/**
 * @author Zhang yongwei
 */
public interface RedisStringService {

    /**
     * 通过key查询数据
     * @param redisKey
     * @return
     */
    String getStringByKey(final String redisKey);

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    boolean exists(final String key);

    /**
     * 设置值--没有时间  永久保存
     * @param key
     * @param value
     * @return
     */
    void setStringKeyValue(final String key, final String value);


    /**
     * 设置值
     * @param key
     * @param value
     * @param times
     * @return
     */
    void setStringKeyValue(final String key, final String value, final long times);


    /**
     * 通过key删除数据
     * @param redisKey
     * @return
     */
    void deleteByKey(final String redisKey);

}

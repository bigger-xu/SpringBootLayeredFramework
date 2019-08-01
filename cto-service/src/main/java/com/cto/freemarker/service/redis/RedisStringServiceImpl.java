package com.cto.freemarker.service.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Zhang yongwei
 */
@Service
public class RedisStringServiceImpl implements RedisStringService {
    private Logger LOGGER = LoggerFactory.getLogger(RedisStringServiceImpl.class);
    private static final String CHART_UTF8 = "UTF-8";

    @Resource
    private RedisTemplate<?, ?> redisTemplate;
    /**
     * 通过key查询数据
     *
     * @param redisKey
     * @return
     */
    @Override
    public String getStringByKey(final String redisKey) {
        String value = redisTemplate.execute((RedisCallback<String>) connection -> {
            try {
                byte[] val = connection.get(redisKey.getBytes());
                if (val != null) {
                    return new String(val, CHART_UTF8);
                }
            } catch (Exception e) {
                LOGGER.error("queryByKey.Exception", e);
            }
            return "";
        });

        return value;
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    @Override
    public boolean exists(final String key) {
        boolean exists = redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            boolean exi = false;
            try {
                exi = connection.exists(key.getBytes(CHART_UTF8));
            } catch (Exception e) {
                LOGGER.error("exists.Exception", e);
            }
            return exi;
        });

        return exists;
    }

    /**
     * 设置值
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public void setStringKeyValue(final String key, final String value) {
        set(key, value, 0);
    }

    /**
     * 设置值
     *
     * @param key
     * @param value
     * @param times
     * @return
     */
    @Override
    public void setStringKeyValue(final String key, final String value,final long times) {
        set(key, value, times);
    }

    /**
     * 通过key删除数据
     *
     * @param redisKey
     * @return
     */
    @Override
    public void deleteByKey(final String redisKey) {
        redisTemplate.execute((RedisCallback<Long>) connection -> {
            Long result = 0L;
            try {
                result = connection.del(redisKey.getBytes(CHART_UTF8));
            } catch (Exception e) {
                LOGGER.error("deleteByKey.Exception", e);
            }
            return result;
        });
    }

    /**
     * 设置值
     *
     * @param key
     * @param value
     * @return
     */
    public void set(final String key, final String value, final long times) {
        redisTemplate.execute((RedisCallback<Long>) connection -> {
            try {
                connection.set(key.getBytes(CHART_UTF8),
                        value.getBytes(CHART_UTF8));
                if (times > 0) {
                    // 设置有效时间
                    connection.expire(key.getBytes(CHART_UTF8), times);
                }
            } catch (Exception e) {
                LOGGER.error("set.Exception", e);
            }
            return 1L;
        });
    }
}

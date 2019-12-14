package com.cto.freemarker.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Zhang yongwei
 */
@Service
public class RedisCacheServiceImpl implements RedisCacheService {

    private final Logger LOGGER = LoggerFactory.getLogger(RedisCacheServiceImpl.class);
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void setCacheString(String key, String value) {
        ValueOperations<String, String> operation = redisTemplate.opsForValue();
        operation.set(key, value);
    }

    @Override
    public String getCacheString(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public <T> ValueOperations<String, T> setCacheObject(String key, T value) {
        ValueOperations<String, String> operation = redisTemplate.opsForValue();
        operation.set(key, value.toString());
        return (ValueOperations<String, T>) operation;
    }

    @Override
    public <T> ValueOperations<String, T> setCacheObject(String key, T value, long num, TimeUnit time) {
        ValueOperations<String, String> operation = redisTemplate.opsForValue();
        operation.set(key, value.toString(), num, time);
        return (ValueOperations<String, T>) operation;
    }

    @Override
    public <T> T getCacheObject(String key) {
        ValueOperations<String, T> operation = (ValueOperations<String, T>) redisTemplate.opsForValue();
        return operation.get(key);
    }

    @Override
    public <T> ListOperations<String, T> setCacheList(String key, List<T> dataList) {
        ListOperations listOperation = redisTemplate.opsForList();
        if (null != dataList) {
            int size = dataList.size();
            for (int i = 0; i < size; i++) {
                listOperation.leftPush(key, dataList.get(i));
            }
        }
        return listOperation;
    }

    @Override
    public <T> List<T> getCacheList(String key) {
        List<T> dataList = new ArrayList<T>();
        ListOperations<String, String> listOperation = redisTemplate.opsForList();
        Long size = listOperation.size(key);
        for (int i = 0; i < size; i++) {
            dataList.add((T) listOperation.index(key,i));
        }
        return dataList;
    }

    @Override
    public <T> HashOperations<String, String, T> setCacheMap(String key, Map<String, T> dataMap) {

        HashOperations hashOperations = redisTemplate.opsForHash();
        if (null != dataMap) {
            for (Map.Entry<String, T> entry : dataMap.entrySet()) {
                hashOperations.put(key, entry.getKey(), entry.getValue());
            }
        }
        return hashOperations;
    }

    @Override
    public <T> HashOperations<String, String, T> setCacheMap(String key, Map<String, T> dataMap, long num, TimeUnit time) {
        //存值
        HashOperations hashOperations = redisTemplate.opsForHash();
        if (null != dataMap) {
            for (Map.Entry<String, T> entry : dataMap.entrySet()) {
                hashOperations.put(key, entry.getKey(), entry.getValue());
                //设置超时时间10秒 第三个参数控制时间单位，详情查看TimeUnit
                redisTemplate.expire(key,num,time);
            }
        }
        return hashOperations;
    }

    @Override
    public <T> Map<Object, Object> getCacheMap(String key) {
        Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
        return map;
    }

    @Override
    public synchronized boolean isRequestOutInterface(String key, Long outTime) {

        long count = redisTemplate.opsForValue().increment(key, 1);
        if (count == 1) {
            redisTemplate.expire(key, outTime, TimeUnit.SECONDS);
            return true;
        }
        return false;
    }

    @Override
    public boolean setFailure(String key) {
        return redisTemplate.expire(key, 0, TimeUnit.SECONDS);
    }

    @Override
    public void delete(String key, String loanType) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(key,loanType);
    }

}

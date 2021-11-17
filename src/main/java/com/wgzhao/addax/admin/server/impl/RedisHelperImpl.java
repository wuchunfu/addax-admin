package com.wgzhao.addax.admin.server.impl;

import com.wgzhao.addax.admin.server.RedisHelper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service("RedisHelper")
public class RedisHelperImpl<HK, T> implements RedisHelper<HK, T>
{
    // Get redisTemplate instance in constructor, key(not hashKey) uses String type by default
    private RedisTemplate<String, T> redisTemplate;
    // Instantiate the operation object in the constructor through the redisTemplate factory method
    private HashOperations<String, HK, T> hashOperations;
    private ListOperations<String, T> listOperations;
    private ZSetOperations<String, T> zSetOperations;
    private SetOperations<String, T> setOperations;
    private ValueOperations<String, T> valueOperations;

    // IDEA can be injected successfully even though it has errors. After instantiating the operation object, the method can be called directly to operate the Redis database
    @Autowired
    public RedisHelperImpl(RedisTemplate<String, T> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
        this.listOperations = redisTemplate.opsForList();
        this.zSetOperations = redisTemplate.opsForZSet();
        this.setOperations = redisTemplate.opsForSet();
        this.valueOperations = redisTemplate.opsForValue();
    }

    @Override
    public void hashPut(String key, HK hashKey, T domain) {
        hashOperations.put(key, hashKey, domain);
    }

    @Override
    public Map<HK, T> hashFindAll(String key) {
        return hashOperations.entries(key);
    }

    @Override
    public T hashGet(String key, HK hashKey) {
        return hashOperations.get(key, hashKey);
    }

    @Override
    public void hashRemove(String key, HK hashKey) {
        hashOperations.delete(key, hashKey);
    }

    @Override
    public Long listPush(String key, T domain) {
        return listOperations.rightPush(key, domain);
    }

    @Override
    public Long listUnshift(String key, T domain) {
        return listOperations.leftPush(key, domain);
    }

    @Override
    public List<T> listFindAll(String key) {
        if (!redisTemplate.hasKey(key)) {
            return null;
        }
        return listOperations.range(key, 0, listOperations.size(key));
    }

    @Override
    public T listLPop(String key) {
        return listOperations.leftPop(key);
    }

    @Override
    public void valuePut(String key, T domain) {
        valueOperations.set(key, domain);
    }

    @Override
    public void valuePut(String key, T domain, TimeUnit timeUnit)
    {
        long timeout = 0;
        timeUnit.toSeconds(timeout);
        valueOperations.set(key, domain, timeout, timeUnit);
    }

    @Override
    public void valuePut(String key, T domain, long time)
    {
        valueOperations.set(key, domain, time, TimeUnit.SECONDS);
    }

    @Override
    public T getValue(String key) {
        return valueOperations.get(key);
    }

    @Override
    public void remove(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public boolean expire(String key, long timeout, TimeUnit timeUnit) {
        return redisTemplate.expire(key, timeout, timeUnit);
    }

    @Override
    public long getExpire(String key, TimeUnit timeUnit)
    {
        if (this.redisTemplate.hasKey(key)) {
            return this.redisTemplate.getExpire(key, timeUnit);
        }
        return 0;
    }

    @Override
    public boolean hasKey(String key) {
        return this.redisTemplate.hasKey(key);
    }
}

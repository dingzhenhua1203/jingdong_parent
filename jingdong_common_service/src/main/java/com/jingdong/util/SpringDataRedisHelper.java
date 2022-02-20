package com.jingdong.util;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Component
public class SpringDataRedisHelper {
    @Autowired
    private RedisTemplate redisTemplate;


    //- - - - - - - - - - - - - - - - - - - - -  公共方法 - - - - - - - - - - - - - - - - - - - -

    /**
     * 是否存在
     *
     * @param key
     * @return
     */
    public Boolean hasKey(String key) {
        Boolean result = catchInvoke(() -> redisTemplate.hasKey(key));
        return result == null ? false : result;
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key
     * @return
     */
    public Boolean delKey(String key) {
        Boolean result = catchInvoke(() -> redisTemplate.delete(key));
        return result == null ? false : result;
    }

    /**
     * 根据key 获取过期时间
     *
     * @param keys
     * @return
     */
    public Boolean delKeys(String... keys) {
        Boolean result = catchInvoke(() -> {
            if (keys != null && keys.length > 0) {
                if (keys.length == 1) {
                    return redisTemplate.delete(keys[0]);
                } else {
                    return redisTemplate.delete(CollectionUtils.arrayToList(keys)) > 0;
                }
            }
            return true;
        });
        return result == null ? false : result;
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param expireSecond
     * @return
     */
    public Boolean expire(String key, long expireSecond) {

        Boolean result = catchInvoke(() -> redisTemplate.expire(key, expireSecond, TimeUnit.SECONDS));
        return result == null ? false : result;
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key
     * @return -1 表示没有获取到
     */
    public Long ttl(String key) {

        Long result = catchInvoke(() -> redisTemplate.getExpire(key, TimeUnit.SECONDS));
        return result == null ? -1 : result;
    }


    //- - - - - - - - - - - - - - - - - - - - -  String类型 - - - - - - - - - - - - - - - - - - - -

    /**
     * 根据key获取值
     *
     * @param key 键
     * @return 值
     */
    @Nullable
    public Object sgGet(String key) {
        Object result = catchInvoke(() -> {
            return key == null ? null : redisTemplate.opsForValue().get(key);
        });
        return result;
    }

    /**
     * 将值放入缓存并设置时间
     *
     * @param key          键
     * @param value        值
     * @param expireSecond 时间(秒) -1为无期限
     * @return true成功 false 失败
     */
    public void sgSet(String key, String value, long expireSecond) {
        if (expireSecond > 0) {
            redisTemplate.opsForValue().set(key, JsonUtil.toJSONString(value), expireSecond, TimeUnit.SECONDS);
        } else {
            redisTemplate.opsForValue().set(key, value);
        }
    }

    /**
     * 对一个 key-value 的值进行加减操作,
     * 如果该 key 不存在 将创建一个key 并赋值该 number
     * 如果 key 存在,但 value 不是长整型 ,将报错
     *
     * @param key
     * @param delta
     */
    public Long incrBy(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }


    public Integer sgSetNx(String key, String value, int expireSecond) {
        Boolean result = catchInvoke(() -> {
            return redisTemplate.opsForValue().setIfAbsent(key, value, expireSecond, TimeUnit.SECONDS);
        });
        return result == null ? -1 : result.booleanValue() ? 1 : 0;
    }

    //- - - - - - - - - - - - - - - - - - - - -  set类型 - - - - - - - - - - - - - - - - - - - -

    /**
     * 将数据放入set缓存
     *
     * @param key 键
     * @return
     */
    public void sAdd(String key, String value) {
        redisTemplate.opsForSet().add(key, value);
    }

    /**
     * 获取变量中的值
     *
     * @param key 键
     * @return
     */
    public Set<Object> sGet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 随机获取变量中指定个数的元素
     *
     * @param key   键
     * @param count 值
     * @return
     *//*
    public void sRandomMembers(String key, long count) {
        redisTemplate.opsForSet().randomMembers(key, count);
    }

    *//**
     * 随机获取变量中的元素
     *
     * @param key 键
     * @return
     *//*
    public Object randomMember(String key) {
        return redisTemplate.opsForSet().randomMember(key);
    }*/

    /**
     * 弹出变量中的元素
     *
     * @param key 键
     * @return
     */
    public Object sPOP(String key) {
        return redisTemplate.opsForSet().pop("setValue");
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 批量移除set缓存中元素
     *
     * @param key    键
     * @param values 值
     * @return
     */
    public void sRemove(String key, Object... values) {
        redisTemplate.opsForSet().remove(key, values);
    }


    //- - - - - - - - - - - - - - - - - - - - -  hash类型 - - - - - - - - - - - - - - - - - - - -

    /**
     * 加入缓存
     *
     * @param key 键
     * @param map 键
     * @return
     */
    public void hAdd(String key, Map<String, String> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 加入缓存
     *
     * @param key   键
     * @param field 键
     * @param value value
     * @return
     */
    public void hAdd(String key, String field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 获取指定key的值string
     *
     * @param key   键
     * @param field 键
     * @return
     */
    public <T> T hGet(String key, String field, Class<T> clazz) {
        String result = redisTemplate.opsForHash().get(key, field).toString();
        return JsonUtil.parseObject(result, clazz);
    }

    public Object hGet(String key, String field) {
        Object result = redisTemplate.opsForHash().get(key, field);
        return result;
    }

    /**
     * 获取 key 下的 所有  hashkey 和 value
     *
     * @param key 键
     * @return
     */
    public Map<Object, Object> hGetMaps(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 验证指定 key 下 有没有指定的 hashkey
     *
     * @param key
     * @param field
     * @return
     */
    public boolean hHasKey(String key, String field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * 删除指定 hash 的 HashKey
     *
     * @param key
     * @param hashKeys
     * @return 删除成功的 数量
     */
    public Long delete(String key, String... hashKeys) {
        return redisTemplate.opsForHash().delete(key, hashKeys);
    }


    @Nullable
    private <T> T catchInvoke(Supplier<T> callback) {
        try {
            return callback.get();
        } catch (Exception ex) {
            return null;
        }
    }
}

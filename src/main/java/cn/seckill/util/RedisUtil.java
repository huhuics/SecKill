/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2016 All Rights Reserved.
 */
package cn.seckill.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * 缓存工具,使用Redis对数据进行缓存、读取等操作
 * @author HuHui
 * @version $Id: RedisUtil.java, v 0.1 2017年1月11日 下午15:59:34 HuHui Exp $
 */
public final class RedisUtil {

    private static RedisTemplate<Serializable, Object> redisTemplate;

    /** 缓存失效时间 */
    private static final long                          EXPIRE_TIME = 10L;

    /**
     * 保存数据至Redis,数据将按照unit时间单位保持timeout时长
     * @param key     
     * @param value
     * @param timeout  保存时长
     * @param unit     时间单位{@link TimeUnit}
     */
    public static void set(Serializable key, Object value, Long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * 保存数据至Redis,没有失效时间
     * @param key
     * @param value
     */
    public static void set(Serializable key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 保存数据至Redis,并设置默认10s失效时间
     * @param key
     * @param value
     */
    public static void setWithDefaultExpire(Serializable key, Object value) {
        set(key, value, EXPIRE_TIME, TimeUnit.SECONDS);
    }

    /**
     * 泛型方法.通过key从Redis中取缓存数据
     * @param key
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(Serializable key, Class<T> clazz) {
        return (T) redisTemplate.boundValueOps(key).get();
    }

    /**
     * 通过key从Redis中取缓存数据
     * @param key
     * @return
     */
    public static Object get(Serializable key) {
        return redisTemplate.boundValueOps(key).get();
    }

    /**
     * 根据key从Redis中删除缓存数据
     * @param key
     */
    public static void delete(Serializable... key) {
        redisTemplate.delete(Arrays.asList(key));
    }

    /**
     * 根据key模糊匹配并删除缓存数据
     * @param pattern
     */
    public static void deletePattern(Serializable... key) {
        for (Serializable pattern : key) {
            redisTemplate.delete(redisTemplate.keys(pattern + "*"));
        }
    }

    /**
     * 根据key判断数据是否存在Redis中
     * @param key
     * @return
     */
    public static boolean exists(Serializable key) {
        return redisTemplate.hasKey(key);
    }

    public static void setRedisTemplate(RedisTemplate<Serializable, Object> redisTemplate) {
        RedisUtil.redisTemplate = redisTemplate;
    }

}

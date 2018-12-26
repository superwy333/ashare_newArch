package com.zynsun.openplatform.cache;

import com.jeesuite.cache.command.RedisObject;
import com.jeesuite.cache.redis.JedisProviderFactory;
import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存帮助类
 *
 * @author david
 * @description 基于jeesuite的Redis缓存工具类实现。
 * @create 2017-09-25 22:15:00
 */
public abstract class CacheHelper {

    /**
     * 全局缓存映射（缓存类型、缓存数据提供者）
     */
    private static final Map<String, CacheDataProvider> cacheDataProviderMap = new ConcurrentHashMap<>();

    /**
     * 绑定指定类型的缓存数据提供者
     *
     * @param type     缓存类型（建议声明成常量）
     * @param provider 缓存数据提供者
     */
    public static void bind(String type, CacheDataProvider provider) {
        synchronized (cacheDataProviderMap) {
            cacheDataProviderMap.put(type, provider);
        }
    }

    /**
     * 解除指定类型的缓存数据提供者绑定
     *
     * @param type 缓存类型（建议声明成常量）
     */
    public static void unbind(String type) {
        synchronized (cacheDataProviderMap) {
            cacheDataProviderMap.remove(type);
        }
    }

    /**
     * 根据类型缓存数据（缓存到Redis服务器）
     *
     * @param type 缓存类型
     */
    public static void cache(String type, long expire) {
        synchronized (cacheDataProviderMap) {
            if (cacheDataProviderMap.containsKey(type)) {
                cacheDataProviderMap.get(type).cache(type, expire);
            }
        }
    }

    /**
     * 根据类型清理缓存（从服务器上删除缓存）
     *
     * @param type 缓存类型
     */
    public static void flush(String type) {
        synchronized (cacheDataProviderMap) {
            if (cacheDataProviderMap.containsKey(type)) {
                cacheDataProviderMap.get(type).flush(type);
            }
        }
    }

    /**
     * 根据类型重新加载缓存（从服务器上删除缓存后重新缓存）
     *
     * @param type 缓存类型
     */
    public static void reload(String type, long expire) {
        synchronized (cacheDataProviderMap) {
            if (cacheDataProviderMap.containsKey(type)) {
                flush(type);
                cache(type, expire);
            }
        }
    }

    /**
     * 根据KEY获取缓存（从服务器上获取缓存）
     *
     * @param key 缓存KEY
     */
    public static <T> T getByKey(String key) {
        RedisObject redisObject = new RedisObject(key);
        return redisObject.get();
    }

    /**
     * 根据KEY设置缓存（更新/添加服务器上的缓存）
     *
     * @param key 缓存KEY
     */
    public static boolean setByKey(String key, Object value, long expire) {
        RedisObject redisObject = new RedisObject(key);
        return redisObject.set(value, expire);
    }

    /**
     * 根据KEY清理缓存（更新/添加服务器上的缓存）
     *
     * @param key 缓存KEY
     */
    public static boolean delByKey(String key) {
        RedisObject redisObject = new RedisObject(key);
        return redisObject.remove();
    }

    /**
     * 根据表达式清理缓存（更新/添加服务器上的缓存）
     *
     * @param pattern 缓存KEY的表达式，支持通配符（具体参考redis语法）
     */
    public static boolean delByPattern(String pattern) {
        Jedis jedis = (Jedis) JedisProviderFactory.getJedisCommands(null);
        try {
            Set<String> keys = jedis.keys(pattern);
            for (String key1 : keys){
                jedis.del(key1);
            }
        } finally{
            JedisProviderFactory.getJedisProvider(null).release();
        }
        return true;
    }

    /**
     * 根据KEY获取剩余时间（获取服务器上缓存的剩余时间）
     *
     * @param key 缓存KEY
     */
    public static long ttlByKey(String key) {
        RedisObject redisObject = new RedisObject(key);
        return redisObject.getTtl();
    }

    /**
     * 根据KEY获取剩余时间（获取服务器上缓存的剩余时间）
     *
     * @param key 缓存KEY
     */
    public static Object getByTTL(String key, long ttl) {
        RedisObject redisObject = new RedisObject(key);
        if (redisObject.getTtl() >= ttl) { // 过期时间还剩指定时间的话，则从缓存中获取
            return redisObject.get();
        }
        return null;
    }
}

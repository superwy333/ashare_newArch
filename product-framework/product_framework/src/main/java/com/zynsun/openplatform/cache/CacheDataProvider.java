package com.zynsun.openplatform.cache;

/**
 * 缓存数据提供者。（不同的类型需要使用不同的获取方式）
 *
 * @author david
 * @create 2017-09-25 22:42
 */
public interface CacheDataProvider {

    /**
     * 缓存数据
     *
     * @param type   缓存类型
     * @param expire 失效时间（单位：s）
     */
    void cache(String type, long expire);

    /**
     * 清理缓存
     *
     * @param type 缓存类型
     */
    void flush(String type);

}

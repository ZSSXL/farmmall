package com.fmall.util;

import com.fmall.common.RedisPool;
import com.fmall.common.RedisShardedPool;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

/**
 * @author ZSS
 * @date 2019/9/11 21:24
 * @description redis 连接池工具
 */
@Slf4j
public class ShardedRedisPoolUtil {

    /**
     * set
     *
     * @param key   键
     * @param value 值
     * @return String
     */
    public static String set(String key, String value) {
        ShardedJedis jedis = null;
        String result = null;
        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.set(key, value);
        } catch (Exception e) {
            log.error("set key:{} value:{} error", key, value, e);
            assert jedis != null;
            RedisShardedPool.returnResource(jedis);
            return result;
        }
        RedisShardedPool.returnResource(jedis);
        return result;
    }

    /**
     * getset
     *
     * @param key   键
     * @param value 值
     * @return String
     */
    public static String getSet(String key, String value) {
        ShardedJedis jedis = null;
        String result = null;
        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.getSet(key, value);
        } catch (Exception e) {
            log.error("getSet key:{} value:{} error", key, value, e);
            assert jedis != null;
            RedisShardedPool.returnResource(jedis);
            return result;
        }
        RedisShardedPool.returnResource(jedis);
        return result;
    }

    /**
     * 获取值
     *
     * @param key 键
     * @return String
     */
    public static String get(String key) {
        ShardedJedis jedis = null;
        String result = null;

        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.get(key);
        } catch (Exception e) {
            log.error("set key:{} error", key, e);
            assert jedis != null;
            RedisShardedPool.returnResource(jedis);
            return result;
        }
        RedisShardedPool.returnResource(jedis);
        return result;
    }

    /**
     * 设置过期时间
     *
     * @param key    键
     * @param value  值
     * @param exTime 过期时间，单位秒
     * @return String
     */
    public static String setEx(String key, String value, int exTime) {
        ShardedJedis jedis = null;
        String result = null;
        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.setex(key, exTime, value);
        } catch (Exception e) {
            log.error("setEx key:{} exTime:{} value:{} error", key, exTime, value, e);
            assert jedis != null;
            RedisShardedPool.returnResource(jedis);
            return result;
        }
        RedisShardedPool.returnResource(jedis);
        return result;
    }

    /**
     * 设置key的有效期，单位是秒
     *
     * @param key    键
     * @param exTime 值
     * @return Long
     */
    public static Long setEx(String key, int exTime) {
        ShardedJedis jedis = null;
        Long result = null;
        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.expire(key, exTime);
        } catch (Exception e) {
            log.error("setEx key:{} exTime:{} error", key, exTime, e);
            assert jedis != null;
            RedisShardedPool.returnResource(jedis);
            return result;
        }
        RedisShardedPool.returnResource(jedis);
        return result;
    }

    /**
     * 删除
     *
     * @param key 键
     * @return String
     */
    public static Long del(String key) {
        ShardedJedis jedis = null;
        Long result = null;
        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.del(key);
        } catch (Exception e) {
            log.error("set key:{} error", key, e);
            assert jedis != null;
            RedisShardedPool.returnResource(jedis);
            return result;
        }
        RedisShardedPool.returnResource(jedis);
        return result;
    }

    /**
     * setnx
     *
     * @param key   键
     * @param value 值
     * @return String
     */
    public static Long setNx(String key, String value) {
        ShardedJedis jedis = null;
        Long result = null;
        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.setnx(key, value);
        } catch (Exception e) {
            log.error("setnx key:{} value:{} error", key, value, e);
            assert jedis != null;
            RedisShardedPool.returnResource(jedis);
            return result;
        }
        RedisShardedPool.returnResource(jedis);
        return result;
    }


}

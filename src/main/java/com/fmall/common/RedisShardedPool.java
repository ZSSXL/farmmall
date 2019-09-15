package com.fmall.common;

import com.fmall.util.PropertiesUtil;
import redis.clients.jedis.*;
import redis.clients.jedis.util.Hashing;
import redis.clients.jedis.util.Sharded;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZSS
 * @date 2019/9/15 15:38
 * @description sharded redis 分片连接池
 */
public class RedisShardedPool {
    /**
     * sharded jedis连接池
     */
    private static ShardedJedisPool pool;

    /**
     * 最大连接数
     */
    private static Integer maxTotal = Integer.valueOf(PropertiesUtil.getProperty("redis.max.total", "20"));

    /**
     * 在Jedis pool中最大的idle（空闲）状态的jedis实例个数
     */
    private static Integer maxIdle = Integer.valueOf(PropertiesUtil.getProperty("redis.max.idle", "10"));

    /**
     * 在Jedis pool中最大的idle（空闲）状态的jedis实例个数
     */
    private static Integer minIdle = Integer.valueOf(PropertiesUtil.getProperty("redis.min.idle", "2"));

    /**
     * 在borrow一个jedis实例的时候，是否要进行验证操作，如果赋值true，则得到的jedis实例肯定是可以用的
     */
    private static Boolean testOnBorrow = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.borrow", "true"));

    /**
     * 在return一个jedis实例的时候，是否要进行验证操作，如果赋值true，则放回的jedis实例肯定是可以用的
     */
    private static Boolean testOnReturn = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.return", "true"));

    /**
     * redis的端口
     */
    private static Integer redisPort = Integer.valueOf(PropertiesUtil.getProperty("redis.port", "6379"));

    /**
     * redis的ip地址
     */
    private static String redisIp = PropertiesUtil.getProperty("redis.ip");

    /**
     * redis2的端口
     */
    private static Integer redis2Port = Integer.valueOf(PropertiesUtil.getProperty("redis.2.port", "6379"));

    /**
     * redis2的ip地址
     */
    private static String redis2Ip = PropertiesUtil.getProperty("redis.2.ip");

    private static void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);
        // 连接耗尽的时候，是否阻塞，false会抛出异常，true阻塞直到超时，默认为true
        config.setBlockWhenExhausted(true);

        JedisShardInfo info1 = new JedisShardInfo(redisIp, redisPort, 1000 * 2);
        JedisShardInfo info2 = new JedisShardInfo(redis2Ip, redis2Port, 1000 * 2);
        List<JedisShardInfo> jedisShardInfoList = new ArrayList<JedisShardInfo>(2);
        jedisShardInfoList.add(info1);
        jedisShardInfoList.add(info2);

        pool = new ShardedJedisPool(config, jedisShardInfoList, Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN);
    }

    static {
        initPool();
    }

    /**
     * 从连接池中获取jedis
     *
     * @return Jedis
     */
    public static ShardedJedis getJedis() {
        return pool.getResource();
    }

    public static void returnResource(ShardedJedis jedis) {
        jedis.close();
    }

    public static void main(String[] args) {
        ShardedJedis jedis = pool.getResource();

        for (int i = 0; i <= 10; i++) {
            jedis.set("key" + i, "value" + i);
        }
        returnResource(jedis);
        System.out.println("program is end ... ");
    }

}

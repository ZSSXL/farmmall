package com.fmall.common;

import com.fmall.util.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author ZSS
 * @date 2019/9/19 17:27
 * @description redisson 初始化类
 */
@Slf4j
@Component
public class RedissonManager {

    private Config config = new Config();

    private Redisson redisson = null;

    public Redisson getRedisson() {
        return redisson;
    }

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

    @PostConstruct
    private void init() {
        try {
            config.useSingleServer().setAddress(redisIp + ":" + redisPort);
            redisson = (Redisson) Redisson.create(config);
            log.info("初始化Redisson结束");
        } catch (Exception e) {
            log.error("Redisson init error", e);
        }
    }

}

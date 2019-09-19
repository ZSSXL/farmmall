package com.fmall.task;

import com.fmall.common.Const;
import com.fmall.common.RedissonManager;
import com.fmall.service.IOrderService;
import com.fmall.util.PropertiesUtil;
import com.fmall.util.ShardedRedisPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

/**
 * @author ZSS
 * @date 2019/9/19 9:17
 * @description 定时关单
 */
@Component
@Slf4j
public class CloseOrderTask {

    private final IOrderService orderService;
    private final RedissonManager redissonManager;

    @Autowired
    public CloseOrderTask(IOrderService orderService, RedissonManager redissonManager) {
        this.orderService = orderService;
        this.redissonManager = redissonManager;
    }

    /**
     * 关闭超时订单，每1分钟检查一次
     */
    // @Scheduled(cron = "0 */1 * * * ?")
    public void closeOrderTaskV1() {
        int hour = Integer.parseInt(PropertiesUtil.getProperty("close.order.task.time.hour"));
        orderService.closeOrder(hour);
    }

    /**
     * 分布式锁
     */
    // @Scheduled(cron = "0 */1 * * * ?")
    public void closeOrderTaskV2() {
        long lockTimeout = Long.parseLong((PropertiesUtil.getProperty("lock.timeout", "5000")));
        Long setNaResult = ShardedRedisPoolUtil.setNx(Const.Redis.CLOSE_ORDER_TASK_LOCK, String.valueOf(System.currentTimeMillis() + lockTimeout));
        if (setNaResult != null && setNaResult.intValue() == 1) {
            // 如果返回值是1，代表设置成功，获取锁
            closeOrder();
        } else {
            log.info("没有获得分布式锁： {}", Const.Redis.CLOSE_ORDER_TASK_LOCK);
        }
    }

    @PreDestroy
    public void delLock() {
        ShardedRedisPoolUtil.del(Const.Redis.CLOSE_ORDER_TASK_LOCK);
    }

    /**
     * 分布式锁V3
     */
    // @Scheduled(cron = "0 */1 * * * ?")
    public void closeOrderTaskV3() {
        long lockTimeout = Long.parseLong((PropertiesUtil.getProperty("lock.timeout", "5000")));
        Long setNaResult = ShardedRedisPoolUtil.setNx(Const.Redis.CLOSE_ORDER_TASK_LOCK, String.valueOf(System.currentTimeMillis() + lockTimeout));
        if (setNaResult != null && setNaResult.intValue() == 1) {
            // 如果返回值是1，代表设置成功，获取锁
            closeOrder();
        } else {
            // 未获取到锁，继续判断时间戳，看是否可以重置并获取到锁
            String lockValueStr = ShardedRedisPoolUtil.get(Const.Redis.CLOSE_ORDER_TASK_LOCK);
            if (lockValueStr != null && System.currentTimeMillis() > Long.parseLong(lockValueStr)) {
                // 再次用当前时间戳getSet
                // 返回给定的key的值 -> 旧值判断，是否可以获取锁
                // 当key不存在，返回nil ， 获取锁
                String getSetResult = ShardedRedisPoolUtil.getSet(Const.Redis.CLOSE_ORDER_TASK_LOCK, String.valueOf(System.currentTimeMillis() + lockTimeout));
                if (getSetResult == null || StringUtils.equals(lockValueStr, getSetResult)) {
                    // 真正获取到锁
                    closeOrder();
                } else {
                    log.info("没有获取到分布式锁：{}", Const.Redis.CLOSE_ORDER_TASK_LOCK);
                }
            } else {
                log.info("没有获取到分布式锁：{}", Const.Redis.CLOSE_ORDER_TASK_LOCK);
            }
        }
    }

    /**
     * Redisson 分布式锁
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void closeOrderTaskV4() {
        RLock lock = redissonManager.getRedisson().getLock(Const.Redis.CLOSE_ORDER_TASK_LOCK);
        boolean getLock = false;
        try {
            if (getLock = lock.tryLock(2, 5, TimeUnit.SECONDS)) {
                log.info("Redisson获取分布式锁: {},ThreadName: {}", Const.Redis.CLOSE_ORDER_TASK_LOCK, Thread.currentThread().getName());
                int hour = Integer.parseInt(PropertiesUtil.getProperty("close.order.task.time.hour"));
                orderService.closeOrder(hour);
            } else {
                log.info("Redisson没有获取到分布式锁：{}", Const.Redis.CLOSE_ORDER_TASK_LOCK);
            }
        } catch (InterruptedException e) {
            log.error("Redisson分布式锁获取异常", e);
        } finally {
            lock.unlock();
            log.info("Redisson分布式锁释放");
        }
    }

    private void closeOrder() {
        // 设置有效期为50秒，防止死锁
        ShardedRedisPoolUtil.expire(Const.Redis.CLOSE_ORDER_TASK_LOCK, 5);
        log.info("获取： {}, ThreadName: {}", Const.Redis.CLOSE_ORDER_TASK_LOCK, Thread.currentThread().getName());
        int hour = Integer.parseInt(PropertiesUtil.getProperty("close.order.task.time.hour"));
        orderService.closeOrder(hour);
        ShardedRedisPoolUtil.del(Const.Redis.CLOSE_ORDER_TASK_LOCK);
        log.info("释放分布式锁");
    }

}

package com.fmall.test;

import com.fmall.util.RedisPoolUtil;
import org.junit.Test;

/**
 * @author ZSS
 * @date 2019/9/11 21:42
 * @description redis 工具类测试
 */
public class RedisUtilTest extends BaseTest {

    @Test
    public void setTest() {
        String set = RedisPoolUtil.set("name", "zss");
        System.out.println(set);
    }

    @Test
    public void getTest() {
        String redis = RedisPoolUtil.get("name");
        System.out.println(redis);
    }

    @Test
    public void setExTest() {
        String setEx = RedisPoolUtil.setEx("redisEx", "exTest", 20);
        System.out.println(setEx);
    }

    @Test
    public void setKeyExTest() {
        Long name = RedisPoolUtil.setEx("name", 20);
        System.out.println(name);
    }

    @Test
    public void delKeyTest() {
        Long name = RedisPoolUtil.del("name");
        System.out.println(name);
    }

}

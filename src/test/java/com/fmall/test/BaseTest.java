package com.fmall.test;

import org.junit.After;
import org.junit.Before;

/**
 * @author ZSS
 * @date 2019/9/11 21:45
 * @description 测试类父类
 */
public class BaseTest {
    @Before
    public void before() {
        System.out.println("=========开始测试=========");
    }

    @After
    public void after() {
        System.out.println("=========结束测试=========");
    }
}

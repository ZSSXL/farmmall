package com.fmall.test;


import com.fmall.common.ServerResponse;
import com.fmall.dao.LivestockMapper;
import com.fmall.dao.UserMapper;
import com.fmall.pojo.Livestock;
import com.fmall.pojo.User;
import com.fmall.service.ILivestockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ServiceTest {

    @Autowired
    private ILivestockService iLivestockService;

    @Autowired
    private LivestockMapper livestockMapper;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void LivestockTest(){
        Integer label = 1000101;
        Integer labelTest = null;
        String varities = "家猪";
        Livestock livestock = livestockMapper.selectLivestockByLabel(label);
        System.out.println("牲畜信息："+livestock);
    }

    @Test
    public void UserTest(){
        Integer userId = 123;
        User user = userMapper.selectByPrimaryKey(userId);
        System.out.println(user);
    }
}

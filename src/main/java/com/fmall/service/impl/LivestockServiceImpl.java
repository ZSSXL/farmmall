package com.fmall.service.impl;

import com.fmall.common.ServerResponse;
import com.fmall.dao.LivestockMapper;
import com.fmall.pojo.Livestock;
import com.fmall.service.ILivestockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZSS
 * @description livestock service implement
 */
@Service("iLivestockService")
public class LivestockServiceImpl implements ILivestockService {

    private final LivestockMapper livestockMapper;

    @Autowired
    public LivestockServiceImpl(LivestockMapper livestockMapper) {
        this.livestockMapper = livestockMapper;
    }

    @Override
    public ServerResponse<List<Livestock>> getAllLivestock() {
        List<Livestock> livestockList = livestockMapper.getAllLivestock();
        if (livestockList == null) {
            return ServerResponse.createByErrorMessage("没有牲畜信息");
        }
        return ServerResponse.createBySuccess("查询成功", livestockList);
    }

    @Override
    public ServerResponse<Livestock> selectLivestockByLabel(Integer label) {
        // 1、查询信息
        Livestock livestock = livestockMapper.selectLivestockByLabel(label);
        if (livestock == null) {
            return ServerResponse.createByErrorMessage("没有该牲畜信息");
        }
        livestock.setLabel(livestock.getLabel() / 100);
        return ServerResponse.createBySuccess("查询成功", livestock);
    }

}

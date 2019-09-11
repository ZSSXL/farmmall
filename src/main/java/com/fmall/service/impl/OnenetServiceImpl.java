package com.fmall.service.impl;

import com.fmall.dao.EnviromentMapper;
import com.fmall.dao.LivestockMapper;
import com.fmall.dao.LogisticsMapper;
import com.fmall.pojo.Enviroment;
import com.fmall.pojo.Livestock;
import com.fmall.pojo.Logistics;
import com.fmall.service.OnenetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZSH
 * @description one net service implements
 */
@Service
public class OnenetServiceImpl implements OnenetService {

    private final LogisticsMapper logisticsMapper;
    private final LivestockMapper liveStockMapper;
    private final EnviromentMapper enviromentMapper;

    @Autowired
    public OnenetServiceImpl(LogisticsMapper logisticsMapper, LivestockMapper liveStockMapper, EnviromentMapper enviromentMapper) {
        this.logisticsMapper = logisticsMapper;
        this.liveStockMapper = liveStockMapper;
        this.enviromentMapper = enviromentMapper;
    }


    @Override
    public void insertById(Double id) {
        logisticsMapper.insertById(id);
    }

    @Override
    public Logistics select(int i) {

        return logisticsMapper.selectByPrimaryKey(i);
    }

    @Override
    public void sava(Livestock liveStock) {
        liveStockMapper.insert(liveStock);
    }

    @Override
    public void saveBox(Logistics logistics) {
        logisticsMapper.insert(logistics);
    }

    @Override
    public void saveEnvironment(Enviroment enviroment) {
         enviromentMapper.insert(enviroment);
    }
}

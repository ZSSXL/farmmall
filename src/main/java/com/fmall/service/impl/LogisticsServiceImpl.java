package com.fmall.service.impl;

import com.fmall.dao.LogisticsMapper;
import com.fmall.service.ILogisticsService;
import com.fmall.vo.LogisticsSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LogisticsServiceImpl implements ILogisticsService {
    @Autowired
    private LogisticsMapper logisticsMapper;
    @Override
    public List<Double> getTemperature(Integer boxId) {
        return logisticsMapper.getTemperature(boxId);
    }

    @Override
    public List<Date> getTime(Integer boxId) {
        return logisticsMapper.getTime(boxId);
    }

    @Override
    public List<Double> getHumidity(Integer boxId) {
        return logisticsMapper.getHumidity(boxId);
    }

    @Override
    public List<LogisticsSimple> selectOne(Integer boxId) {
        return logisticsMapper.selectOne(boxId);
    }

}

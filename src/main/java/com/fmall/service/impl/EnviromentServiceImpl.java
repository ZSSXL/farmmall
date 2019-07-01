package com.fmall.service.impl;

import com.fmall.dao.EnviromentMapper;
import com.fmall.service.IEnviromentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EnviromentServiceImpl implements IEnviromentService {

    @Autowired
    private EnviromentMapper enviromentMapper;

    @Override
    public List<Double> getTemperature(Integer annimalId) {
        return enviromentMapper.getTemperature(annimalId);
    }

    @Override
    public List<Date> getTime(Integer annimalId) {
        return enviromentMapper.getTime(annimalId);
    }

    @Override
    public List<Double> getHumidity(Integer annimalId) {
        return enviromentMapper.getHumidity(annimalId);
    }
}

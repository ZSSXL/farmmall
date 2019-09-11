package com.fmall.service.impl;

import com.fmall.common.ServerResponse;
import com.fmall.dao.EnviromentMapper;
import com.fmall.service.IEnviromentService;
import com.fmall.vo.EnviromentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ZSS
 * @description enviroment service implement
 */
@Service("iEnviromentService")
public class EnviromentServiceImpl implements IEnviromentService {

    private final EnviromentMapper enviromentMapper;

    @Autowired
    public EnviromentServiceImpl(EnviromentMapper enviromentMapper) {
        this.enviromentMapper = enviromentMapper;
    }

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

    @Override
    public ServerResponse<List<EnviromentVo>> selectEnviromentSimple(Integer label){
        List<EnviromentVo> enviromentVoList = enviromentMapper.selectEnviromentSimple(label);
        if(enviromentVoList == null){
            return ServerResponse.createByErrorMessage("暂时还没有数据");
        }
        return ServerResponse.createBySuccess(enviromentVoList);
    }
}

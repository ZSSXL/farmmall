package com.fmall.service;

import com.fmall.common.ServerResponse;
import com.fmall.vo.EnviromentVo;

import java.util.Date;
import java.util.List;

/**
 * @author ZSS
 * @description enviroment service
 */
public interface IEnviromentService {

    /**
     * 获取温度
     *
     * @param annimalId 动物id
     * @return List<Double>
     */
    List<Double> getTemperature(Integer annimalId);

    /**
     * 获取时间
     *
     * @param annimalId 动物id
     * @return List<Date>
     */
    List<Date> getTime(Integer annimalId);

    /**
     * 获取湿度
     *
     * @param annimalId 动物id
     * @return List<Double>
     */
    List<Double> getHumidity(Integer annimalId);

    /**
     * 查询环境信息
     *
     * @param label 标签
     * @return ServerResponse
     */
    ServerResponse<List<EnviromentVo>> selectEnviromentSimple(Integer label);
}

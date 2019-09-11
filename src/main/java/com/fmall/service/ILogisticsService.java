package com.fmall.service;

import com.fmall.vo.LogisticsSimple;

import java.util.Date;
import java.util.List;

/**
 * @author ZSS
 * @description logistics service
 */
public interface ILogisticsService {

    /**
     * 获取温度
     *
     * @param boxId 盒子id
     * @return List<Double>
     */
    List<Double> getTemperature(Integer boxId);

    /**
     * 获取温度
     *
     * @param boxId 盒子id
     * @return List<Date>
     */
    List<Date> getTime(Integer boxId);

    /**
     * 获取温度
     *
     * @param boxId 盒子id
     * @return List<Double>
     */
    List<Double> getHumidity(Integer boxId);

    /**
     * 获取温度
     *
     * @param boxId 盒子id
     * @return List<LogisticsSimple>
     */
    List<LogisticsSimple> selectOne(Integer boxId);
}

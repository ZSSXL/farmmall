package com.fmall.service;

import com.fmall.vo.LogisticsSimple;

import java.util.Date;
import java.util.List;

public interface ILogisticsService {
    List<Double> getTemperature(Integer boxId);

    List<Date> getTime(Integer boxId);

    List<Double> getHumidity(Integer boxId);

    List<LogisticsSimple> selectOne(Integer boxId);
}

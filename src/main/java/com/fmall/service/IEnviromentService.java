package com.fmall.service;

import com.fmall.common.ServerResponse;
import com.fmall.vo.EnviromentVo;

import java.util.Date;
import java.util.List;

public interface IEnviromentService {

    List<Double> getTemperature(Integer annimalId);

    List<Date> getTime(Integer annimalId);

    List<Double> getHumidity(Integer annimalId);

    ServerResponse<List<EnviromentVo>> selectEnviromentSimple(Integer label);
}

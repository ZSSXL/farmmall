package com.fmall.service;

import java.util.Date;
import java.util.List;

public interface IEnviromentService {

    List<Double> getTemperature(Integer annimalId);

    List<Date> getTime(Integer annimalId);

    List<Double> getHumidity(Integer annimalId);
}

package com.fmall.dao;

import com.fmall.pojo.Logistics;
import com.fmall.vo.LogisticsSimple;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface LogisticsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Logistics record);

    int insertSelective(Logistics record);

    Logistics selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Logistics record);

    int updateByPrimaryKey(Logistics record);

    List<Double> getTemperature(@Param("boxId") Integer boxId);

    List<Date> getTime(@Param("boxId")Integer boxId);

    List<Double> getHumidity(@Param("boxId")Integer boxId);

    void insertById(@Param("id") Double id);

    LogisticsSimple selectOne(Integer boxId);
}
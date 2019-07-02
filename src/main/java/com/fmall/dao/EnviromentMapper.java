package com.fmall.dao;

import com.fmall.pojo.Enviroment;
import com.fmall.vo.EnviromentVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface EnviromentMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Enviroment record);

    int insertSelective(Enviroment record);

    Enviroment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Enviroment record);

    int updateByPrimaryKey(Enviroment record);

    List<Double> getTemperature(@Param("annimalId") Integer annimalId);

    List<Date> getTime(@Param("annimalId")Integer annimalId);

    List<Double> getHumidity(@Param("annimalId")Integer annimalId);

    List<EnviromentVo> selectEnviromentSimple(Integer label);
}
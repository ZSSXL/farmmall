package com.fmall.dao;

import com.fmall.pojo.Livestock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LivestockMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Livestock record);

    int insertSelective(Livestock record);

    Livestock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Livestock record);

    int updateByPrimaryKey(Livestock record);

    List<Livestock> getAllLivestock();

    Livestock selectByLabel(Integer label);

    Livestock selectLivestockByLabelAndVarieties(@Param("label") Integer label,@Param("varieties") String varieties);
}
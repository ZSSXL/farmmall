package com.fmall.service;

import com.fmall.common.ServerResponse;
import com.fmall.pojo.Livestock;

import java.util.List;

public interface ILivestockService {

    /**
     * 查询所有牲畜信息
     * @return
     */
    ServerResponse<List<Livestock>> getAllLivestock();

    /**
     * 扫码查询出牲畜信息
     * @param label
     * @param varieties
     * @return
     */
    ServerResponse<Livestock> selectLivestockByLabelAndVarieties(Integer label, String varieties);
}

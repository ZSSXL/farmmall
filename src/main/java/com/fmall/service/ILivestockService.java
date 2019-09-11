package com.fmall.service;

import com.fmall.common.ServerResponse;
import com.fmall.pojo.Livestock;

import java.util.List;

/**
 * @author ZSS
 * @description livestock service
 */
public interface ILivestockService {

    /**
     * 查询所有牲畜信息
     *
     * @return ServerResponse
     */
    ServerResponse<List<Livestock>> getAllLivestock();

    /**
     * 扫码查询出牲畜信息
     *
     * @param label 标签
     * @return ServerResponse
     */
    ServerResponse<Livestock> selectLivestockByLabel(Integer label);
}

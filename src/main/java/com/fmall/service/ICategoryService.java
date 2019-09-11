package com.fmall.service;

import com.fmall.common.ServerResponse;
import com.fmall.pojo.Category;

import java.util.List;

/**
 * @author ZSS
 * @description category service
 */
public interface ICategoryService {

    /**
     * 查询所有的分类
     *
     * @return ServerResponse
     */
    ServerResponse<List<Category>> getAllCategory();
}

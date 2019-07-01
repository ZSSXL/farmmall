package com.fmall.service;

import com.fmall.common.ServerResponse;
import com.fmall.pojo.Category;

import java.util.List;

public interface ICategoryService {

    /**
     * 查询所有的分类
     * @return
     */
    ServerResponse<List<Category>> getAllCategory();
}

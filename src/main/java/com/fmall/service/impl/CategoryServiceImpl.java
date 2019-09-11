package com.fmall.service.impl;

import com.fmall.common.ServerResponse;
import com.fmall.dao.CategoryMapper;
import com.fmall.pojo.Category;
import com.fmall.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZSS
 * @description category service implement
 */
@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public ServerResponse<List<Category>> getAllCategory() {
        List<Category> categoryList = categoryMapper.selectAll();
        if (categoryList == null) {
            return ServerResponse.createByErrorMessage("没有分类信息");
        }
        return ServerResponse.createBySuccess("查询成功", categoryList);
    }

}

package com.fmall.controller.portal;


import com.fmall.common.ServerResponse;
import com.fmall.pojo.Category;
import com.fmall.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ZSS
 * @description category controller
 */
@Controller
@RequestMapping("/category/")
public class CategoryController {

    private final ICategoryService iCategoryService;

    @Autowired
    public CategoryController(ICategoryService iCategoryService) {
        this.iCategoryService = iCategoryService;
    }

    @RequestMapping(value = "get_all_category.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<List<Category>> getAllCategory(){
        return iCategoryService.getAllCategory();
    }
}

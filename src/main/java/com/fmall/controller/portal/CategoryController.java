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

@Controller
@RequestMapping("/category/")
public class CategoryController {

    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping(value = "get_all_category.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<List<Category>> getAllCategory(){
        ServerResponse<List<Category>> serverResponse = iCategoryService.getAllCategory();
        return serverResponse;
    }
}

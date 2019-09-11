package com.fmall.controller.portal;

import com.fmall.common.ServerResponse;
import com.fmall.pojo.Livestock;
import com.fmall.service.ILivestockService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ZSS
 * @description livestock controller
 */
@Controller
@RequestMapping("/livestock/")
public class LivestockController {

    private final ILivestockService iLivestockService;

    @Autowired
    public LivestockController(ILivestockService iLivestockService) {
        this.iLivestockService = iLivestockService;
    }

    @RequestMapping(value = "get_all_livestock.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<List<Livestock>> getAllLivestock() {
        return iLivestockService.getAllLivestock();
    }

    /**
     * 扫码查询牲畜信息
     *
     * @param label 标签
     * @param va    种类
     * @return ServerResponse
     */
    @RequestMapping(value = "scanning_query.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<Livestock> getLivestock(Integer label, Integer va) {
        String varieties = "";
        if (va == 1) {
            varieties = "猪";
            label = label * 100 + 1;
        } else if (va == 2) {
            varieties = "牛";
            label = label * 100 + 2;
        } else if (va == 3) {
            varieties = "羊";
            label = label * 100 + 3;
        }
        if (label == null || StringUtils.isBlank(varieties)) {
            return ServerResponse.createByErrorMessage("传了错误的参数");
        }
        // 1、查询出牲畜信息
        return iLivestockService.selectLivestockByLabel(label);
    }

}

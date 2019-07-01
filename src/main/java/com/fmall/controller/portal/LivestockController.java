package com.fmall.controller.portal;

import com.fmall.common.ServerResponse;
import com.fmall.pojo.Livestock;
import com.fmall.service.ILivestockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 牲畜
 */

@Controller
@RequestMapping("/livestock/")
public class LivestockController {

    @Autowired
    private ILivestockService iLivestockService;

    @RequestMapping(value = "get_all_livestock.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<List<Livestock>> getAllLivestock(){
        ServerResponse<List<Livestock>> serverResponse = iLivestockService.getAllLivestock();
        return serverResponse;
    }

    /**
     * 扫码查询牲畜信息
     * @param label
     * @param va
     * @return fmall/livastock/scanning_query.do
     */
    @RequestMapping(value = "scanning_query.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<Livestock> getLivestoce(Integer label,Integer va){

        String varieties = "";
        if(va == 1){
            varieties = "猪";
        }else if(va == 2){
            varieties = "牛";
        }else if(va == 3){
            varieties = "羊";
        }

        if(label == null || varieties == ""){
            return ServerResponse.createByErrorMessage("传了错误的参数");
        }

        // 1、查询出牲畜信息
        ServerResponse<Livestock> serverResponse = iLivestockService.selectLivestockByLabelAndVarieties(label,varieties);
        return serverResponse;
    }

}

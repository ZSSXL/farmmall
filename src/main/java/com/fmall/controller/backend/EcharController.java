package com.fmall.controller.backend;


import com.fmall.common.ServerResponse;
import com.fmall.service.IEnviromentService;
import com.fmall.service.ILogisticsService;
import com.fmall.util.DateFormat;
import com.fmall.vo.LogisticsSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class EcharController {
    @Autowired
    private ILogisticsService logisticsService;
    @Autowired
    private IEnviromentService enviromentService;


    @RequestMapping(value = "/echar.do",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> echarShow(@RequestParam(value = "boxId",defaultValue = "531651109") Integer boxId){
        Map<String,Object> map = new HashMap<>();
        List<Double> temperature = logisticsService.getTemperature(boxId);
        List<Date> time = logisticsService.getTime(boxId);
        List<Double> humidity = logisticsService.getHumidity(boxId);
        Collections.reverse(time);
        Collections.reverse(temperature);
        Collections.reverse(humidity);
        List<String> s_time = new ArrayList<>();
        for (Date date : time) {
            s_time.add(DateFormat.dataToString(date));
        }
        map.put("temperature",temperature);
        map.put("time",s_time);
        map.put("humidity",humidity);
        return map;
    }

    @RequestMapping(value = "/show_logistic.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<LogisticsSimple> mapShow(Integer boxId){
        LogisticsSimple logisticsSimple = logisticsService.selectOne(boxId);
        if(logisticsSimple == null){
            return ServerResponse.createByErrorMessage("查询失败");
        }
        return ServerResponse.createBySuccess(logisticsSimple);
    }

    @RequestMapping(value = "/enviroment.do",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> enviromentShow(@RequestParam(value = "annimalId") Integer annimalId){
        Map<String,Object> map = new HashMap<>();
        List<Double> temperature = enviromentService.getTemperature(annimalId);
        List<Date> time = enviromentService.getTime(annimalId);
        List<Double> humidity = enviromentService.getHumidity(annimalId);
        Collections.reverse(time);
        Collections.reverse(temperature);
        Collections.reverse(humidity);
        List<String> s_time = new ArrayList<>();
        for (Date date : time) {
            s_time.add(DateFormat.dataToString(date));
        }
        map.put("temperature",temperature);
        map.put("time",s_time);
        map.put("humidity",humidity);
        return map;
    }
}

package com.fmall.controller.backend;


import com.fmall.common.ServerResponse;
import com.fmall.service.IEnviromentService;
import com.fmall.service.ILogisticsService;
import com.fmall.util.DateFormat;
import com.fmall.vo.EnviromentVo;
import com.fmall.vo.LogisticsSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @author ZSS
 * @description 图标Controller
 */
@Controller
public class EcharController {

    private ILogisticsService logisticsService;
    private IEnviromentService iEnviromentService;

    @Autowired
    public EcharController(ILogisticsService logisticsService, IEnviromentService iEnviromentService) {
        this.logisticsService = logisticsService;
        this.iEnviromentService = iEnviromentService;
    }

    @RequestMapping(value = "/echar.do", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> echarShow(@RequestParam(value = "boxId", defaultValue = "531651109") Integer boxId) {
        Map<String, Object> map = new HashMap<>();
        List<Double> temperature = logisticsService.getTemperature(boxId);
        List<Date> time = logisticsService.getTime(boxId);
        List<Double> humidity = logisticsService.getHumidity(boxId);
        Collections.reverse(time);
        Collections.reverse(temperature);
        Collections.reverse(humidity);
        List<String> sTime = new ArrayList<>();
        for (Date date : time) {
            sTime.add(DateFormat.dateToString(date));
        }
        map.put("temperature", temperature);
        map.put("time", sTime);
        map.put("humidity", humidity);
        return map;
    }

    @RequestMapping(value = "/show_logistic.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<List<LogisticsSimple>> mapShow(Integer boxId) {
        List<LogisticsSimple> logisticsSimpleList = logisticsService.selectOne(boxId);
        if (logisticsSimpleList == null) {
            return ServerResponse.createByErrorMessage("查询失败");
        }
        return ServerResponse.createBySuccess(logisticsSimpleList);
    }


    @RequestMapping(value = "/enviroment.do", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> enviromentShow(@RequestParam(value = "annimalId") Integer annimalId) {
        Map<String, Object> map = new HashMap<>();
        List<Double> temperature = iEnviromentService.getTemperature(annimalId);
        List<Date> time = iEnviromentService.getTime(annimalId);
        List<Double> humidity = iEnviromentService.getHumidity(annimalId);

        Collections.reverse(time);
        Collections.reverse(temperature);
        Collections.reverse(humidity);
        List<String> sTime = new ArrayList<>();
        for (Date date : time) {
            sTime.add(DateFormat.dateToString(date));
        }
        map.put("temperature", temperature);
        map.put("time", sTime);
        map.put("humidity", humidity);
        return map;
    }

    @RequestMapping(value = "show_enviroment.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<List<EnviromentVo>> showEnvironment(Integer label) {
        return iEnviromentService.selectEnviromentSimple(label);
    }
}

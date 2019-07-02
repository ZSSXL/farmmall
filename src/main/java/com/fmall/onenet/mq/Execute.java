package com.fmall.onenet.mq;

import com.alibaba.fastjson.JSONObject;
import com.fmall.pojo.Enviroment;
import com.fmall.pojo.Livestock;
import com.fmall.pojo.Logistics;
import com.fmall.service.OnenetService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;


public class Execute extends Thread {


    @Autowired
    private OnenetService onenetService;

    public void run(){
        System.out.println("started");
        while(true){
            if(Queue.isEmpty()){
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                JSONObject obj = (JSONObject) Queue.take();
                JSONObject json = obj.getJSONObject("appProperty");
                String jsonStr = json.get("deviceId").toString();
                if(jsonStr.equals("525475090")){
                    String str =  obj.get("body").toString();
                    String varieties = str.substring(0, 2);
                    String label = str.substring(2, 7);
                    String staple_food = str.substring(7, 9);
                    String weight = str.substring(9, 13);
                    String age = str.substring(13, 17);
                    String vaccine = str.substring(17, 19);
                    String medical_record = str.substring(19, 21);
                    String health = str.substring(21, 23);
                    String o_varieties = "";
                    String o_staple_food = "";
                    String o_vaccine = "";
                    String o_medical_record = "";
                    String o_health = "";
                    if (varieties.equals("01")) {
                        o_varieties = "猪";
                    } else if (varieties.equals("02")) {
                        o_varieties = "牛";
                    } else {
                        o_varieties = "羊";
                    }
                    int o_label = Integer.parseInt(label);
                    if (staple_food.equals("01")) {
                        o_staple_food = "青饲料";
                    } else if (staple_food.equals("02")) {
                        o_staple_food = "粗饲料";
                    } else if (staple_food.equals("03")) {
                        o_staple_food = "蛋白质饲料";
                    } else {
                        o_staple_food = "青贮饲料";
                    }
                    double o_weight = Double.parseDouble(weight);
                    int o_age = Integer.parseInt(age);

                    if (vaccine.equals("01")) {
                        o_vaccine = "口蹄疫";
                    } else if (vaccine.equals("02")) {
                        o_vaccine = "伪狂犬";
                    } else if (vaccine.equals("03")) {
                        o_vaccine = "乙脑";
                    } else {
                        o_vaccine = "猪瘟";
                    }
                    if (medical_record.equals("01")) {
                        o_medical_record = "猪丹毒";
                    } else if (medical_record.equals("02")) {
                        o_medical_record = "猪肺疫";
                    } else if (medical_record.equals("03")) {
                        o_medical_record = "猪副伤寒";
                    } else {
                        o_medical_record = "猪蛔虫病";
                    }

                    if (health.equals("01")) {
                        o_health = "优";
                    } else if (health.equals("02")) {
                        o_health = "良";
                    } else {
                        o_health = "差";
                    }

                    Livestock liveStock = new Livestock();
                    if ( o_varieties.equals("猪"))
                    {
                        String a =String.valueOf(o_label)+"01";
                        liveStock.setLabel(Integer.valueOf(a));
                    }else if (o_varieties.equals("牛"))
                    {
                        String a = String.valueOf(o_label)+"02";
                        liveStock.setLabel(Integer.valueOf(a));
                    }else {
                        String a = String.valueOf(o_label)+"03";
                        liveStock.setLabel(Integer.valueOf(a));
                    }

                    liveStock.setVarieties(o_varieties);
                    liveStock.setStapleFood(o_staple_food);
                    liveStock.setWeight(new BigDecimal(o_weight));
                    liveStock.setAge(o_age);
                    liveStock.setVaccine(o_vaccine);
                    liveStock.setMedicalRecord(o_medical_record);
                    liveStock.setHealth(o_health);
                    liveStock.setOrigin("赣州");
                    System.out.println(liveStock);
                    onenetService.sava(liveStock);
                    System.out.println("管理系统ok");
                    //System.out.println(s);
                    //String a=OneNetUtil.pig(s);
                   // double d = Double.parseDouble(s);
                   // onenetService.insertById(d);
                   // System.out.println(a);

                }
             if(jsonStr.equals("532134988")){
                 String str =  obj.get("body").toString();
                 //JSONObject jsonjson = (JSONObject) obj.get("appProperty");
                // String time = jsonjson.get("dataTimestamp").toString();
                // Long dataTimestamp = Long.parseLong(time);
                 String[] strings = str.split("&");

                 double temperature = Double.parseDouble(strings[0]);
                 double humidity = Double.parseDouble(strings[1]);
                 double longitude = Double.parseDouble(strings[2]);
                 double latitude = Double.parseDouble(strings[3]);
                 Logistics logistics = new Logistics();
                 logistics.setBoxId(531651109);
                 logistics.setTemperature(temperature);
                 logistics.setHumidity(humidity);
                 logistics.setLongitude(new BigDecimal(longitude));
                 logistics.setLatitude(new BigDecimal(latitude));
                 onenetService.save_box(logistics);
                 System.out.println("冷链保鲜箱ok");
             }

             if(jsonStr.equals("532135131")){
                 String str =  obj.get("body").toString();
                 String[] strings = str.split("&");
                 double temperature = Double.parseDouble(strings[0]);
                 double humidity = Double.parseDouble(strings[1]);
                 double longitude = Double.parseDouble(strings[2]);
                 double latitude = Double.parseDouble(strings[3]);
                 Enviroment enviroment = new Enviroment();
                 enviroment.setAnnimalId(532135131);
                 enviroment.setTemperature(temperature);
                 enviroment.setHumidity(humidity);
                 enviroment.setLongitude(new BigDecimal(longitude));
                 enviroment.setLatitude(new BigDecimal(latitude));
                 enviroment.setLabel(1000101);
                 onenetService.save_environment(enviroment);
                System.out.println("牲畜跟踪系统ok");
                }

            }
        }
    }
}

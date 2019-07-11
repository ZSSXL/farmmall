package com.fmall.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 *  时间类工具
 */

public class DateFormat {

    public static final String STANARD_FORMAT = "yyyy-MM-dd";

    public static final String STANARD_RORMAT_DETAIL = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当天日日期，并转换为yyyy-mm-dd的格式
     * @return
     */


    public static String whichDay(){
        Date date = new Date();
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANARD_FORMAT);
    }

    public static String whichHour(){
        Date date = new Date();
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANARD_RORMAT_DETAIL);
    }

    public static String dateToString(Date date){
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANARD_RORMAT_DETAIL);
    }

    // 字符串类型转为Date类型
    public static Date strToDate(String dateTimeStr){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANARD_RORMAT_DETAIL);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    public static void main(String[] args) {
        String date = whichDay();
        System.out.println(date);
        String s = whichHour();
        System.out.println(s);
    }

}

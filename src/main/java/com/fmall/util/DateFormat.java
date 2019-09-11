package com.fmall.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * @author ZSS
 * @description 时间类工具
 */
public class DateFormat {

    private static final String STANARD_FORMAT = "yyyy-MM-dd";

    private static final String STANARD_FORMAT_DETAIL = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当天日日期，并转换为yyyy-mm-dd的格式
     *
     * @return String
     */
    private static String whichDay() {
        Date date = new Date();
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANARD_FORMAT);
    }

    private static String whichHour() {
        Date date = new Date();
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANARD_FORMAT_DETAIL);
    }

    public static String dateToString(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANARD_FORMAT_DETAIL);
    }

    public static Date strToDate(String dateTimeStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANARD_FORMAT_DETAIL);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }
}

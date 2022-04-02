package com.lhw.quartz.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ：linhw
 * @date ：22.4.2 16:04
 * @description：时间工具类
 * @modified By：
 */
public class DateUtil {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd: hh:mm:ss");

    public static String formatToString(Date date) {
        return SIMPLE_DATE_FORMAT.format(date);
    }

    /**
     * 得到指定length跨度的时间，
     *      如lenght=3，calendarType=Calendar.HOUR_OF_DAY时
     *      表示三小时后
     * @param calendarType
     * @param length
     * @return
     */
    public static Date next(int calendarType, int length) {
        return next(new Date(), calendarType, length);
    }

    public static Date next(Date date, int calendarType, int length){
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(calendarType, now.get(calendarType) + length);

        return now.getTime();
    }

}

package com.tenpay.wxwork.salary.util;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    /**
     * 获取格式为yyyyMM的前几个月日期
     */
    public static String getMonth(int offset) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        c.setTime(new Date());
        c.add(Calendar.MONTH, offset);

        return format.format(c.getTime());
    }


    /**
     * 获取格式为yyyyMMdd的日期
     */
    public static String getDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        c.setTime(new Date());
        return format.format(c.getTime());
    }

    /**
     * 获取格式为yyMM的日期
     */
    public static String getSubMonth() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyMM");
        c.setTime(new Date());
        return format.format(c.getTime());
    }

    /**
     * 获取格式为yyyy-MM-dd HH:mm:ss的日期
     */
    public static String getDateTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        c.setTime(new Date());
        return format.format(c.getTime());
    }

    /**
     * 获取格式为yyyyMMdd的前几个月日期
     */
    public static String getMonthDate(int offset) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        c.setTime(new Date());
        c.add(Calendar.MONTH, offset);

        return format.format(c.getTime());
    }

    /**
     * 获取格式为yyyyMMdd的前几年日期
     */
    public static String getYearDate(int offset) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        c.setTime(new Date());
        c.add(Calendar.YEAR, offset);

        return format.format(c.getTime());
    }

    public static String getYear(int offset) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        c.setTime(new Date());
        c.add(Calendar.YEAR, offset);

        return format.format(c.getTime());
    }

    public static String dateToString(Date date) {
        return dateToString(date, "yyyy-MM-dd", Locale.CHINESE);
    }
    public static String dateToString(Date date, String format, Locale locale) {
        if (date == null)
            return null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, locale);
        return dateFormat.format(date);
    }
}
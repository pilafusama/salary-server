package com.tenpay.wxwork.wxworklib.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static int getTimestamp(long millis) {
        return (int)(millis / 1000);
    }

    public static int getTimestamp() {
        return getTimestamp(System.currentTimeMillis());
    }

    public static int getTimestamp(Calendar calendar) {
       return getTimestamp(calendar.getTimeInMillis());
    }

    public static String formatTimestamp(int time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date((long) time * 1000);
        return simpleDateFormat.format(date);
    }
}


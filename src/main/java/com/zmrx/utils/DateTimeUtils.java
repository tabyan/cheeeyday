package com.zmrx.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tabyan on 16-8-27.
 */
public class DateTimeUtils {

    /**
     * 日期转换时间
     * @param date
     * @return
     */
    public static String dateToString(Date date){
        SimpleDateFormat sf =
                new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(date);
    }

    /**
     * 字符转时间
     * @param dateString
     * @return
     */
    public static Date stringToDate(String dateString){
        SimpleDateFormat sf =
                new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    /**
     * date1是否大于date2
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isBigger(Date date1,Date date2){
        long dateTime1 = date1.getTime();
        long dateTime2 = date2.getTime();
        if (dateTime1 > dateTime2){
            return true;
        }else {
            return false;
        }
    }
}

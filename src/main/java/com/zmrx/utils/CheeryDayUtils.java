package com.zmrx.utils;

import java.util.Calendar;
import java.util.List;

/**
 * Created by tabyan on 7/24/16.
 */
public class CheeryDayUtils {

    /**
     *
     * <p>Title: checkHoliday </P>
     * <p>Description: TODO 验证日期是否是节假日</P>
     * @param calendar  传入需要验证的日期
     * @return
     * return boolean    返回类型  返回true是节假日，返回false不是节假日
     * throws
     * date 2014-11-24 上午10:13:07
     */
    public static boolean checkHoliday(Calendar calendar) throws Exception{
//        //判断日期是否是节假日
//        for (Calendar ca : holidayList) {
//            if(ca.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) &&
//                    ca.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)&&
//                    ca.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)){
//                return true;
//            }
//        }

        //判断日期是否是周六周日
        if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
                calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
            return true;
        }

        return false;
    }
}

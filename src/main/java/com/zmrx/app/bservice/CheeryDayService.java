package com.zmrx.app.bservice;

import com.zmrx.app.domain.GeneralCheeryDay;
import com.zmrx.app.domain.UserSpecilCheeryDay;
import com.zmrx.app.service.GeneralCheeryDayService;
import com.zmrx.app.service.TraditionalChineseDayService;
import com.zmrx.app.service.UserSpecilCheeryDayService;
import com.zmrx.utils.CheeryDayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by tabyan on 16-8-26.
 */
@Service("cheeryDayService")
@Transactional
public class CheeryDayService{

    @Autowired
    GeneralCheeryDayService generalCheeryDayService;

    @Autowired
    UserSpecilCheeryDayService userSpecilCheeryDayService;

    @Autowired
    TraditionalChineseDayService traditionalChineseDayService;

    /**
     *是否国家法定节假日
     * @param date
     * @return
     */
    public boolean isCheeryDay(Date date, String username){
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
        String dateTime = time.format(date);
        String sql = "select * from cheeryday_general_cheeryday where begin_time <= '"+ dateTime+"' and end_time >= '"+dateTime+"'";
        List<GeneralCheeryDay> generalList = generalCheeryDayService.findByDataSQL(sql);
        String sql2 = "select * from cheeryday_user_specil_cheeryday where begin_time <= '"+ dateTime+"' and end_time >= '"+dateTime+"' and userid = '"+username+"'";
        List<UserSpecilCheeryDay> specilList = userSpecilCheeryDayService.findByDataSQL(sql2);
        int flag = 0;
        if (generalList != null){
            if (0 < generalList.size()) {
                flag = 1;
            }
        }
        if (flag != 1) {
            if (specilList != null) {
                if (0 < specilList.size()) {
                    flag = 1;
                }
            }
        }
        if (flag != 1) {
            Calendar calender = Calendar.getInstance();
            calender.setTime(date);
            try {
                if (CheeryDayUtils.checkHoliday(calender)) {
                    flag = 1;
                }
            } catch (Exception e) {
                e.printStackTrace();
                flag = 0;
            }
        }

        if (flag == 1){
            return true;
        }else {
            return false;
        }

    }
    /**
     *  是否双休日
     */
    private boolean checkHoliday(Calendar calendar) throws Exception{
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

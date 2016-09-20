package com.zmrx.app.service.impl;

import com.zmrx.app.domain.GeneralCheeryDay;
import com.zmrx.app.service.GeneralCheeryDayService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by tabyan on 16-8-16.
 */
@Service
@Transactional
public class GeneralCheeryDayServiceImpl extends BaseServiceImpl<GeneralCheeryDay> implements GeneralCheeryDayService {


    public boolean isOwn(String beginDateString, String endDateString,int objectid) {
        String sql = "";
        if(objectid > 0){
            sql = "select * from cheeryday_general_cheeryday where "
                    +"((begin_time <= '"+beginDateString+"' and end_time >= '"+endDateString+"')"
                    +"or ('"+beginDateString+"'<=begin_time and end_time <= '"+endDateString+"')"
                    +"or (begin_time <= '"+beginDateString+"' and '"+beginDateString+"' <= end_time and end_time <='"+endDateString+"')"
                    +"or ('"+beginDateString+"' <= begin_time and '"+endDateString+"' <= end_time)) and objectid != "+objectid;
        }else {
            sql = "select * from cheeryday_general_cheeryday where "
                    +"(begin_time <= '"+beginDateString+"' and end_time >= '"+endDateString+"')"
                    +"or ('"+beginDateString+"'<=begin_time and end_time <= '"+endDateString+"')"
                    +"or (begin_time <= '"+beginDateString+"' and '"+beginDateString+"' <= end_time and end_time <='"+endDateString+"')"
                    +"or ('"+beginDateString+"' <= begin_time and '"+endDateString+"' <= end_time)";
        }
        System.out.println(sql);
        List<GeneralCheeryDay> list = baseDao.findByDataSQL(sql,GeneralCheeryDay.class);
        if (list == null) {
            return false;
        }else if (0 < list.size()){
            return true;
        }else {
            return false;
        }
    }
}

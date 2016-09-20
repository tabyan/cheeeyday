package com.zmrx.app.service.impl;

import com.zmrx.app.domain.UserSpecilCheeryDay;
import com.zmrx.app.service.UserSpecilCheeryDayService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by tabyan on 16-8-21.
 */
@Service
@Transactional
public class UserSpecilCheeryDayServiceImpl extends BaseServiceImpl<UserSpecilCheeryDay> implements UserSpecilCheeryDayService {

    public boolean isOwn(String beginDateString, String endDateString, Integer objectid) {
        String sql = "";
        if(objectid > 0){
            sql = "select * from cheeryday_user_specil_cheeryday where "
                    +"((begin_time <= '"+beginDateString+"' and end_time >= '"+endDateString+"')"
                    +"or ('"+beginDateString+"'<=begin_time and end_time <= '"+endDateString+"')"
                    +"or (begin_time <= '"+beginDateString+"' and '"+beginDateString+"' <= end_time and end_time <='"+endDateString+"')"
                    +"or ('"+beginDateString+"' <= begin_time and '"+endDateString+"' <= end_time)) and objectid != "+objectid;
        }else {
            sql = "select * from cheeryday_user_specil_cheeryday where "
                    +"(begin_time <= '"+beginDateString+"' and end_time >= '"+endDateString+"')"
                    +"or ('"+beginDateString+"'<=begin_time and end_time <= '"+endDateString+"')"
                    +"or (begin_time <= '"+beginDateString+"' and '"+beginDateString+"' <= end_time and end_time <='"+endDateString+"')"
                    +"or ('"+beginDateString+"' <= begin_time and '"+endDateString+"' <= end_time)";
        }
        List<UserSpecilCheeryDay> list = baseDao.findByDataSQL(sql,UserSpecilCheeryDay.class);
        if (list == null) {
            return false;
        }else if (0 < list.size()){
            return true;
        }else {
            return false;
        }
    }
}

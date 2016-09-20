package com.zmrx.app.service;


import com.zmrx.app.domain.UserSpecilCheeryDay;
import com.zmrx.utils.PageResult;

import java.util.List;

/**
 * Created by tabyan on 16-8-21.
 */
public interface UserSpecilCheeryDayService extends BaseService<UserSpecilCheeryDay>{

    boolean isOwn(String beginDateString, String endDateString, Integer objectid);
}

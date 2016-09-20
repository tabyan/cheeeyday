package com.zmrx.app.service;

import com.zmrx.app.domain.GeneralCheeryDay;
import com.zmrx.utils.PageResult;

import java.util.List;

/**
 * Created by tabyan on 16-8-16.
 */
public interface GeneralCheeryDayService extends BaseService<GeneralCheeryDay>{
    boolean isOwn(String beginDateString, String endDateString,int objectid);
}

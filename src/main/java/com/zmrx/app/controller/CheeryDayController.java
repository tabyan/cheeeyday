package com.zmrx.app.controller;

import com.zmrx.app.bservice.CheeryDayService;
import com.zmrx.app.domain.TraditionalChineseDay;
import com.zmrx.app.service.TraditionalChineseDayService;
import com.zmrx.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by tabyan on 16-8-26.
 */
@Controller
@RequestMapping("/cheeryDay")
public class CheeryDayController extends BaseController{

    @Autowired
    CheeryDayService cheeryDayService;

    @Autowired
    TraditionalChineseDayService traditionalChineseDayService;

    @RequestMapping("isCheeryDay")
    @ResponseBody
    public ResultMessageWithInfo isCheeryDay(String dateString, HttpServletRequest request){
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");

        Date date = DateTimeUtils.stringToDate(dateString);
        String sql = "select * from cheeryday_traditional_chineseday where chineseday_time = '"+dateString+"'";
        List<TraditionalChineseDay> list = traditionalChineseDayService.findByDataSQL(sql);
        String msg = "";
        if (list != null){
            if (0 < list.size()){
                msg = list.get(0).getName();
            }
        }
        boolean ret = cheeryDayService.isCheeryDay(date,username);
        if (ret){
            return MsgI(true,"节假日",msg);
        }else {
            return MsgI(false, "正常上班",msg);
        }
    }

    @RequestMapping("chineseDay")
    @ResponseBody
    public ResultMessage chineseDay(){
        for(int year=1901;year<2050;year++){
            List<TraditionalChineseDay> list = Chinese_24SolarTerms.solarTermToString(year);
            System.out.println("-----------------------------------------------------");
            for (TraditionalChineseDay traditionalChineseDay:list){
                System.out.println("name:"+traditionalChineseDay.getName()+"====date:"+traditionalChineseDay.getChinesedayTime());
                traditionalChineseDayService.save(traditionalChineseDay);
            }
        }
        return new ResultMessage();
    }
}

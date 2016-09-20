package com.zmrx.app.controller;

import com.zmrx.app.domain.UserSpecilCheeryDay;
import com.zmrx.app.service.UserSpecilCheeryDayService;
import com.zmrx.utils.DateTimeUtils;
import com.zmrx.utils.PageResult;
import com.zmrx.utils.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tabyan on 16-8-21.
 */
@Controller
@RequestMapping("/specil")
public class UserSpecilCheeryDayController extends BaseController{

    @Autowired
    UserSpecilCheeryDayService userSpecilCheeryDayService;

    @RequestMapping(value = "/index/{page}/{pageSize}",method = RequestMethod.GET)
    public String index(@PathVariable int page,@PathVariable int pageSize, Map<String, Object> model, UserSpecilCheeryDay Model){
        PageResult<UserSpecilCheeryDay> pageResult = userSpecilCheeryDayService.findAll(page,pageSize,Model);
        model.put("pageResult",pageResult);
        return "specil/index";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage update(@RequestBody UserSpecilCheeryDay Model, HttpServletRequest request){
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        Model.setUserid(username);
        Date beginDate = Model.getBeginTime();
        Date endDate = Model.getEndTime();
        String beginDateString = DateTimeUtils.dateToString(beginDate);
        String endDateString = DateTimeUtils.dateToString(endDate);
        if(!DateTimeUtils.isBigger(beginDate,endDate)) {
            boolean isOwen = userSpecilCheeryDayService.isOwn(beginDateString, endDateString, Model.getObjectid());
//            if (isOwen) {
//                return Msg(false, "您所添加节日已存在！");
//            } else {
                boolean rst = userSpecilCheeryDayService.update(Model);
                if (rst) {
                    return Msg(true, "保存成功");
                } else {
                    return Msg(false, "保存失败");
                }
//            }
        }else {
            return Msg(false, "您所添加节日开始时间大于结束时间！");
        }
    }

    @RequestMapping(value = "/edit/{objectid}",method = RequestMethod.GET)
    @ResponseBody
    public UserSpecilCheeryDay edit(@PathVariable int objectid){
        UserSpecilCheeryDay userSpecilCheeryDay = userSpecilCheeryDayService.getById(objectid);
        System.out.println(userSpecilCheeryDay.getBeginTime());
        return userSpecilCheeryDay;
    }

    @RequestMapping(value = "/delete/{objectid}",method = RequestMethod.GET)
    @ResponseBody
    public ResultMessage delete(@PathVariable int objectid){
        boolean rst = userSpecilCheeryDayService.delete(objectid);
        if (rst){
            return Msg(true,"删除成功");
        }else {
            return Msg(false,"删除失败");
        }
    }
}

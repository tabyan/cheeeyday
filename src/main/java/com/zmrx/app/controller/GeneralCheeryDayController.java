package com.zmrx.app.controller;

import com.zmrx.app.domain.GeneralCheeryDay;
import com.zmrx.app.service.GeneralCheeryDayService;
import com.zmrx.utils.DateTimeUtils;
import com.zmrx.utils.PageResult;
import com.zmrx.utils.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * Created by tabyan on 16-8-26.
 */
@Controller
@RequestMapping("/general")
public class GeneralCheeryDayController extends BaseController{
    @Autowired
    GeneralCheeryDayService generalCheeryDayService;

    @RequestMapping(value = "/index/{page}/{pageSize}",method = RequestMethod.GET)
    public String index(@PathVariable int page, @PathVariable int pageSize, Map<String, Object> model, GeneralCheeryDay Model){
        PageResult<GeneralCheeryDay> pageResult = generalCheeryDayService.findAll(page,pageSize,Model);
        model.put("pageResult",pageResult);
        return "general/index";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage update(@RequestBody GeneralCheeryDay Model){
        Date beginDate = Model.getBeginTime();
        Date endDate = Model.getEndTime();
        String beginDateString = DateTimeUtils.dateToString(beginDate);
        String endDateString = DateTimeUtils.dateToString(endDate);
        if(!DateTimeUtils.isBigger(beginDate,endDate)) {
            boolean isOwen = generalCheeryDayService.isOwn(beginDateString, endDateString, Model.getObjectid());

            /*if (isOwen) {
                return "{success:false, msg:您所添加节日已存在！}";
            } else {*/
                boolean rst = generalCheeryDayService.update(Model);
                if (rst) {
                    return Msg(true, "保存成功");
                } else {
                    return Msg(false, "保存失败！");
                }
            /*}*/
        }else {
            return Msg(false, "您所添加节日开始时间大于结束时间！");
        }
    }

    @RequestMapping(value = "/edit/{objectid}",method = RequestMethod.GET)
    @ResponseBody
    public GeneralCheeryDay edit(@PathVariable int objectid){
        GeneralCheeryDay userSpecilCheeryDay = generalCheeryDayService.getById(objectid);
        System.out.println(userSpecilCheeryDay.getBeginTime());
        return userSpecilCheeryDay;
    }

    @RequestMapping(value = "/delete/{objectid}",method = RequestMethod.GET)
    @ResponseBody
    public ResultMessage delete(@PathVariable int objectid){
        boolean rst = generalCheeryDayService.delete(objectid);
        if (rst){
            return Msg(true,"删除成功");
        }else {
            return Msg(false,"删除失败");
        }
    }
}

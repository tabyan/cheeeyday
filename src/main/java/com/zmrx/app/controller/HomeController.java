package com.zmrx.app.controller;


import com.zmrx.app.domain.UserSpecilCheeryDay;
import com.zmrx.app.service.UserSpecilCheeryDayService;
import com.zmrx.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tabyan on 16-8-21.
 */
@Controller
@RequestMapping("/")
public class HomeController extends BaseController{



    @Autowired
    private UserService userService;

    @Autowired
    UserSpecilCheeryDayService userSpecilCheeryDayService;


    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    @RequestMapping("/j")
    @ResponseBody
    public UserSpecilCheeryDay jsons(){
        return userSpecilCheeryDayService.findAll().get(0);
    }

    @RequestMapping("/json")
    @ResponseBody
    public UserSpecilCheeryDay json(){
        return userSpecilCheeryDayService.findAll().get(0);
    }

    @RequestMapping("/admin")
    @ResponseBody
    public List<String> admin(){
        return Arrays.asList("zhangsan", "lisi", "wangwu");
    }
}

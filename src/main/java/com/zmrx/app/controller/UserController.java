package com.zmrx.app.controller;

import com.zmrx.oauth.domain.Role;
import com.zmrx.oauth.domain.User;
import com.zmrx.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tabyan on 16-9-12.
 */
@Controller
@RequestMapping("/")
public class UserController extends BaseController{
    @Autowired
    UserService userService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/registerAction", method = RequestMethod.POST)
    public String registerAction(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        Role role = new Role();
        role.setRole("SCOPE_READ");
        role.setId(2);
        user.setRole(role);
        user.setUsername(username);
        user.setPassword(password);
        userService.save(user);
        HttpSession session = request.getSession();
        session.setAttribute("username",user.getUsername());
        return "home";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(String username, String password, HttpServletRequest request){
        String sql = "select * from user where username = '"+username+"' and password = '"+password+"'";
        List<User[]> list = userService.findByCustomerSQL(sql);
        if (list != null){
            if (0 < list.size()){
                HttpSession session = request.getSession();
                session.setAttribute("username",username);
                return "home";
            }else {
                return "login";
            }
        }else {
            return "login";
        }
    }
}

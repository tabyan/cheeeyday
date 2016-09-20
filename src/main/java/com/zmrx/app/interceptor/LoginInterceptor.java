package com.zmrx.app.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by tabyan on 16-9-12.
 */
public class LoginInterceptor implements HandlerInterceptor {

    public String[] allowUrls;//配置不拦截的utrl

    public void setAllowUrls(String[] allowUrls) {
        this.allowUrls = allowUrls;
    }

    /**
     * Handler执行完成之后调用这个方法
     */
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exc)
            throws Exception {
        System.out.println("after~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    /**
     * Handler执行之后，ModelAndView返回之前调用这个方法
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("post~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    /**
     * Handler执行之前调用这个方法
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        System.out.println("per~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        //获取请求的URL
        String url = request.getRequestURI();
        String requestUrl = url.replace(request.getContextPath(),"");
        if (null != allowUrls && allowUrls.length >=1){
            for (String u:allowUrls){
                if (requestUrl.contains(u)){
                    System.out.println(requestUrl);
                    System.out.println(u);
                    return true;
                }
            }
        }
        //获取Session
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");

        if(username != null){
            return true;
        }
        //不符合条件的，跳转到登录界面
        //request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        //request.getRequestDispatcher("/login.html").forward(request,response);
        response.sendRedirect("/index.html");
        return false;
    }
}

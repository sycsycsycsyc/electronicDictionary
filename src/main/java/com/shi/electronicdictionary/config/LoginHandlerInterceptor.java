package com.shi.electronicdictionary.config;

import com.shi.electronicdictionary.pojo.User;
import com.shi.electronicdictionary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        //登录成功之后，应该有用户的session
        Object loginuser = request.getSession().getAttribute("loginuser");
        if (loginuser != null) {
            return true;
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    //遍历cookie如果找到登录状态则返回true
                    String token = cookie.getValue();
                    //是否存在对应的token对象
                    User user = userService.getByUUID(token);
                    //存在表示cookie未失效 不拦截 否则拦截
                    if(user!=null){
                        request.getSession().setAttribute("loginuser", user.getMail());
                        request.getSession().setAttribute("user", user);
                        return true;
                    }else{
                        request.setAttribute("msg", "没有权限，请先登录");
                        request.getRequestDispatcher("/login").forward(request, response);
                        return false;
                    }

                }
            }
        }
        //没有登录，而是直接进入的首页，肯定是不让进的
        request.setAttribute("msg", "没有权限，请先登录");
        request.getRequestDispatcher("/login").forward(request, response);
        return false;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)  {//throws Exception

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)  {

    }
}

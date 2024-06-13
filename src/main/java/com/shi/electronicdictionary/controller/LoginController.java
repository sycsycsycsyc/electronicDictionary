package com.shi.electronicdictionary.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shi.electronicdictionary.pojo.Dictionary;
import com.shi.electronicdictionary.pojo.User;
import com.shi.electronicdictionary.service.DictionaryService;
import com.shi.electronicdictionary.service.MailService;
import com.shi.electronicdictionary.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class LoginController {
    @Resource
    UserService userService;
    @Resource
    DictionaryService dictionaryService;
    @Resource
    MailService mailService;

    @RequestMapping("/user/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam(value = "remember", required = false) String remember,
            Model model,
            HttpSession session,
            HttpServletResponse response
    ) {
        //具体的业务
        User user = userService.getByMail(username);
        //List<Dictionary> dictionary;
        PageInfo<Dictionary> pageInfo;
        if (user != null) {
            String library = user.getLibrary();
            //添加资料库
            switch (library) {
                case "common":
                    PageHelper.startPage(1, 3);
                    pageInfo = new PageInfo<>(dictionaryService.getAll(username));
                    //dictionary = dictionaryService.getAll(username);
                    break;
                case "四级单词":
                    PageHelper.startPage(1, 3);
                    pageInfo = new PageInfo<>(dictionaryService.getByFlag4(username));
                    //dictionary = dictionaryService.getByFlag4(username);
                    break;
                case "六级单词":
                    PageHelper.startPage(1, 3);
                    pageInfo = new PageInfo<>(dictionaryService.getByFlag6(username));
                    //dictionary = dictionaryService.getByFlag6(username);
                    break;
                default:
                    PageHelper.startPage(1, 3);
                    pageInfo = new PageInfo<>(dictionaryService.getByFlagyan(username));
                    //dictionary = dictionaryService.getByFlagyan(username);
                    break;
            }

            if (user.getPassword().equals(password)) {
                if (remember == null) {
                    Cookie cookie = new Cookie("token", "");
                    cookie.setMaxAge(0);   //Cookie从服务器端发过来的时候就已经是一个已过时的Cookie
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
                if (remember != null) {
                    //更新token
                    String tokenUUID = UUID.randomUUID().toString();
                    userService.updateUUID(tokenUUID, username);
                    //添加刚才生成的token到网页Cookie中
                    Cookie cookie = new Cookie("token", tokenUUID);
                    cookie.setMaxAge(7 * 24 * 60 * 60);    //设置7天的过期时间
                    cookie.setPath("/");//cookie在该路径下的网页起作用

                    //添加cookie 到响应头,真正回到浏览器的时候才会被添加到浏览器的cookie
                    response.addCookie(cookie);
                }
                session.setAttribute("loginuser", username);
                session.setAttribute("user", user);
                session.setAttribute("dictionary", pageInfo);
                return "redirect:/main";
            } else {
                //告诉用户你登录失败了
                model.addAttribute("msg", "密码错误");
                return "login";
            }
        } else {
            //告诉用户你登录失败了
            model.addAttribute("msg", "用户名错误");
            return "login";
        }
    }

    @RequestMapping("/user/register")
    public String register(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            Model model
    ) {
        User user = userService.getByMail(email);
        if (user == null) {
            userService.insert(username, password, email);
            model.addAttribute("msg", "注册成功,请登录");
        } else {
            model.addAttribute("msg", "邮箱已注册");
        }
        return "register";
    }

    @RequestMapping("/user/forget")
    public String forget(
            @RequestParam("email") String email,
            Model model
    ) {
        User user = userService.getByMail(email);
        if (user != null) {
            mailService.forget(user);
            model.addAttribute("msg", "密码已发送到您的邮箱");
        } else {
            model.addAttribute("msg", "邮箱未注册");
        }
        return "forget";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpSession session
    ) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                //遍历cookie如果找到登录状态
                String token = cookie.getValue();
                //是否存在对应的token对象
                User user = userService.getByUUID(token);
                userService.updateUUID("0", user.getMail());
            }
        }
        session.invalidate();
        return "redirect:login";
    }
}

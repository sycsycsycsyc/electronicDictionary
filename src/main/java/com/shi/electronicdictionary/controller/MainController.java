package com.shi.electronicdictionary.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shi.electronicdictionary.pojo.*;
import com.shi.electronicdictionary.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.List;

//@RestController
@Controller

public class MainController {
    @Resource
    UserService userService;
    @Resource
    DictionaryService dictionaryService;
    @Resource
    MyWordService myWordService;
    @Resource
    LibraryService libraryService;
    @Resource
    ScheduleService scheduleService;
    @Resource
    HistoryService historyService;


    @RequestMapping(value = {"/", "/login"})
    //@ResponseBody
    public String handle01(HttpServletRequest request,
                           HttpSession session) {
        //session.setAttribute("loginuser", "1010707431@qq.com");
        //User user = userService.getByMail("1010707431@qq.com");
        Object loginuser = session.getAttribute("loginuser");
        if (loginuser != null)
            return "redirect:/main";
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    User user = userService.getByUUID(token);
                    if (user != null)
                        return "redirect:/main";
                }
            }
        }
        return "login";
    }

    @RequestMapping("/main")
    public String handle02(HttpServletRequest request,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                           HttpSession session) {

        String mail = (String) request.getSession().getAttribute("loginuser");
        User user = userService.getByMail(mail);
        //List<Dictionary> dictionary;
        String library = user.getLibrary();
        //添加资料库
        PageInfo<Dictionary> pageInfo;
        switch (library) {
            case "common":
                //dictionary = dictionaryService.getAll(mail);
                PageHelper.startPage(pageNum, pageSize);
                pageInfo = new PageInfo<>(dictionaryService.getAll(mail));
                break;
            case "四级单词":
                PageHelper.startPage(pageNum, pageSize);
                pageInfo = new PageInfo<>(dictionaryService.getByFlag4(mail));
                //dictionary = dictionaryService.getByFlag4(mail);
                break;
            case "六级单词":
                PageHelper.startPage(pageNum, pageSize);
                pageInfo = new PageInfo<>(dictionaryService.getByFlag6(mail));
                //dictionary = dictionaryService.getByFlag6(mail);
                break;
            default:
                //dictionary = dictionaryService.getByFlagyan(mail);
                PageHelper.startPage(pageNum, pageSize);
                pageInfo = new PageInfo<>(dictionaryService.getByFlagyan(mail));
                break;
        }
        session.setAttribute("dictionary", pageInfo);
        return "main";
    }

    @RequestMapping("/my/history")
    public String handle03(HttpServletRequest request,
                           HttpSession session) {
        String mail = (String) request.getSession().getAttribute("loginuser");
        List<String> dateHistory = historyService.getByDate(mail);
        List<History> allHistory = historyService.getByMail(mail);
        session.setAttribute("allHistory", allHistory);
        session.setAttribute("dateHistory", dateHistory);

        return "history";
    }

    @RequestMapping("/libraries")
    public String handle04(HttpSession session) {
        List<Library> libraryList = libraryService.getAll();
        session.setAttribute("libraries", libraryList);
        return "libraries";
    }

    @RequestMapping("/my")
    public String handle05(HttpSession session) {
        String mail = (String) session.getAttribute("loginuser");
        User user = userService.getByMail(mail);
        session.setAttribute("user", user);
        return "my";
    }

    @RequestMapping("/mywords")
    public String handle06(HttpServletRequest request,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                           HttpSession session) {
        //List<MyWord> myWords;
        session.setAttribute("msg", "");
        String mail = (String) request.getSession().getAttribute("loginuser");
        PageInfo<MyWord> pageInfo;
        PageHelper.startPage(pageNum, pageSize);
        pageInfo = new PageInfo<>(myWordService.getByMail(mail));
        //myWords = myWordService.getByMail(mail);
        session.setAttribute("myWords", pageInfo);
        if (myWordService.getByMail(mail).isEmpty())
            session.setAttribute("msg", "没有这个词");
        return "mywords";
    }

    @RequestMapping("/register")
    public String handle07() {

        return "register";
    }

    @RequestMapping("/forget")
    public String handleforget() {

        return "forget";
    }

    @RequestMapping("/schedule")
    public String handle08(HttpServletRequest request,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageNum2", required = false, defaultValue = "1") Integer pageNum2,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                           HttpSession session) {
        scheduleService.deleteCount();
        String mail = (String) request.getSession().getAttribute("loginuser");
        User user = userService.getByMail(mail);
        //List<Dictionary> dictionary;
        String library = user.getLibrary();
        //添加资料库
        PageInfo<Dictionary> pageInfo2;
        switch (library) {
            case "common":
                //dictionary = dictionaryService.getAll(mail);
                PageHelper.startPage(pageNum2, pageSize);
                pageInfo2 = new PageInfo<>(dictionaryService.getAll(mail));
                break;
            case "四级单词":
                PageHelper.startPage(pageNum2, pageSize);
                pageInfo2 = new PageInfo<>(dictionaryService.getByFlag4(mail));
                //dictionary = dictionaryService.getByFlag4(mail);
                break;
            case "六级单词":
                PageHelper.startPage(pageNum2, pageSize);
                pageInfo2 = new PageInfo<>(dictionaryService.getByFlag6(mail));
                //dictionary = dictionaryService.getByFlag6(mail);
                break;
            default:
                //dictionary = dictionaryService.getByFlagyan(mail);
                PageHelper.startPage(pageNum2, pageSize);
                pageInfo2 = new PageInfo<>(dictionaryService.getByFlagyan(mail));
                break;
        }
        session.setAttribute("dictionary", pageInfo2);

        //背诵的单词
        String flag = user.getLibrary();
        //List<Schedule> scheduleList = scheduleService.getByFlag(mail, flag);
        PageInfo<Schedule> pageInfo;
        PageHelper.startPage(pageNum, pageSize);
        pageInfo = new PageInfo<>(scheduleService.getByFlag(mail, flag));
        session.setAttribute("scheduleList", pageInfo);

        //进度
        int scheduleCount = scheduleService.getCount(mail, flag);
        int allCount;
        //添加资料库
        switch (flag) {
            case "common":
                allCount = scheduleService.getCountAll();
                break;
            case "四级单词":
                allCount = scheduleService.getCount4();
                break;
            case "六级单词":
                allCount = scheduleService.getCount6();
                break;
            default:
                allCount = scheduleService.getCountyan();
                break;
        }
        float a = (float) scheduleCount / (float) allCount;
        DecimalFormat df = new DecimalFormat("0.0000");//格式化小数
        String a1 = df.format(a * 100);
        String scale = a1.substring(0, 5);
        session.setAttribute("scheduleCount", scheduleCount);
        session.setAttribute("allCount", allCount);
        session.setAttribute("scale", scale);
        return "schedule";
    }

    @RequestMapping("/select")
    public String select() {

        return "select";
    }


    @RequestMapping("/updateUser")
    public String updateUser(HttpSession session,
                             HttpServletRequest request) {
        String mail = (String) request.getSession().getAttribute("loginuser");
        User user = userService.getByMail(mail);
        session.setAttribute("user", user);
        List<Library> libraryList = libraryService.getAll();
        session.setAttribute("libraries", libraryList);

        return "updateUser";
    }

    @RequestMapping("/update/user")
    public String updateuser(@RequestParam("username") String username,
                             @RequestParam("library") String library,
                             @RequestParam("switch") String switch1,
                             @RequestParam("time") String time,
                             @RequestParam("count") int count,
                             HttpSession session) {
        String mail = (String) session.getAttribute("loginuser");
        userService.updateLi(library, mail);
        userService.updateFlag2(switch1, mail);
/*        String hourse= time.substring(0, 2);
        String min=time.substring(2);
        time=hourse+":"+min;*/
        if (!time.equals("")) {
            LocalTime localTime = LocalTime.parse(time);
            userService.updateTime(localTime, mail);
        }
        if (!username.equals("")) {
            userService.updateName(username, mail);
        }
        if (count != 0) {
            userService.updateCount(count, mail);
        }

        //ku
        return "redirect:/my";
    }


}


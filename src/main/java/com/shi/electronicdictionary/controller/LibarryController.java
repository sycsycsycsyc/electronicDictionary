package com.shi.electronicdictionary.controller;

import com.shi.electronicdictionary.service.HistoryService;
import com.shi.electronicdictionary.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class LibarryController {
    @Resource
    UserService userService;
    @Resource
    HistoryService historyService;

    @RequestMapping("/chooselibrary/{name}")
    public String insert(@PathVariable String name,
                         HttpServletRequest request,
                         HttpSession session) {
        String mail = (String) request.getSession().getAttribute("loginuser");
        userService.updateLi(name, mail);
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now().withNano(0);
        historyService.insert(mail, localDate, localTime, "修改单词库", name);
        return "redirect:/main";
    }
}

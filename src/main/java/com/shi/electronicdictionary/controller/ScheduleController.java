package com.shi.electronicdictionary.controller;

import com.shi.electronicdictionary.pojo.Schedule;
import com.shi.electronicdictionary.pojo.User;
import com.shi.electronicdictionary.service.DictionaryService;
import com.shi.electronicdictionary.service.HistoryService;
import com.shi.electronicdictionary.service.ScheduleService;
import com.shi.electronicdictionary.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class ScheduleController {
    @Resource
    ScheduleService scheduleService;
    @Resource
    DictionaryService dictionaryService;
    @Resource
    UserService userService;
    @Resource
    HistoryService historyService;

    @RequestMapping("/add2/{english}")
    public String insert(@PathVariable String english,
                         HttpServletRequest request) {
        String mail = (String) request.getSession().getAttribute("loginuser");
        String chinese = dictionaryService.getByEnglish(english).getChinese();
        String voice = dictionaryService.getByEnglish(english).getVoice();
        User user = userService.getByMail(mail);

        Schedule schedule = scheduleService.getByenglish(mail, user.getLibrary(), english);
        if (schedule == null) {
            scheduleService.insert(mail, english, chinese, voice, user.getLibrary());
            LocalDate localDate = LocalDate.now();
            LocalTime localTime = LocalTime.now().withNano(0);
            userService.updateFlag(userService.getByMail(mail).getFlag() + 1, mail);
            historyService.insert(mail, localDate, localTime, "背诵", english);
        } else {
            //session.setAttribute("msg", "单词本已有");

        }

        return "redirect:/main";
    }

    @RequestMapping("/recite")
    public String recite(HttpServletRequest request,
                         Model model) {
        scheduleService.deleteCount();
        String mail = (String) request.getSession().getAttribute("loginuser");
        User user = userService.getByMail(mail);
        String flag = user.getLibrary();
        List<Schedule> reciteList = scheduleService.getByCount(mail, flag);
        model.addAttribute("reciteList", reciteList);
        return "recite";
    }

    @PostMapping("/notRecite")
    @ResponseBody
    public void notRecite(HttpServletRequest request,
                          @RequestParam("english") String english) {

        String mail = (String) request.getSession().getAttribute("loginuser");
        User user = userService.getByMail(mail);
        int count = scheduleService.count(mail, english, user.getLibrary());
        scheduleService.update(count + 1, mail, english, user.getLibrary());
    }

    @PostMapping("/Recite")
    @ResponseBody
    public void Recite(HttpServletRequest request,
                       @RequestParam("english") String english) {

        String mail = (String) request.getSession().getAttribute("loginuser");
        User user = userService.getByMail(mail);
        int count = scheduleService.count(mail, english, user.getLibrary());
        scheduleService.update(count -1, mail, english, user.getLibrary());
    }
}

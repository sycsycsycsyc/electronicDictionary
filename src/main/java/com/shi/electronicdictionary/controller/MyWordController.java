package com.shi.electronicdictionary.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shi.electronicdictionary.pojo.MyWord;
import com.shi.electronicdictionary.service.DictionaryService;
import com.shi.electronicdictionary.service.HistoryService;
import com.shi.electronicdictionary.service.MyWordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class MyWordController {
    @Resource
    MyWordService myWordService;
    @Resource
    DictionaryService dictionaryService;
    @Resource
    HistoryService historyService;

    @RequestMapping("/add/{english}")
    public String add(@PathVariable String english,
                      HttpServletRequest request,
                      HttpSession session) {
        session.setAttribute("msg2", null);
        String mail = (String) request.getSession().getAttribute("loginuser");
        String chinese = dictionaryService.getByEnglish(english).getChinese();
        String voice = dictionaryService.getByEnglish(english).getVoice();
        MyWord words = myWordService.getWord(mail, english);
        if (words == null) {
            myWordService.insert(mail, english, chinese, voice);
            LocalDate localDate = LocalDate.now();
            LocalTime localTime = LocalTime.now().withNano(0);
            historyService.insert(mail, localDate, localTime, "添加单词本", english);
        } else {
            session.setAttribute("msg2", "单词本已有");
        }

        return "redirect:/main";
    }

    @RequestMapping("/delete/{english}")
    public String delete(@PathVariable String english,
                         HttpServletRequest request) {
        String mail = (String) request.getSession().getAttribute("loginuser");
        myWordService.delete(mail, english);
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now().withNano(0);
        historyService.insert(mail, localDate, localTime, "删除单词本记录", english);
        return "redirect:/mywords";
    }

    @RequestMapping("/update")
    public String update(
    ) {

        return "update";
    }

    @RequestMapping("/update/{english}")
    public String updateenglish(@PathVariable String english,
                                HttpServletRequest request,
                                HttpSession session) {
        String mail = (String) request.getSession().getAttribute("loginuser");
        MyWord myWord = myWordService.getWord(mail, english);
        session.setAttribute("myWord", myWord);

        return "redirect:/update";
    }

    @RequestMapping("/updateWord")
    public String updateWord(HttpServletRequest request,
                             @RequestParam("chinese") String chinese,
                             @RequestParam("description") String description
    ) {
        String mail = (String) request.getSession().getAttribute("loginuser");
        MyWord myWord = (MyWord) request.getSession().getAttribute("myWord");
        myWordService.update(chinese, description, mail, myWord.getEnglish());
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now().withNano(0);
        historyService.insert(mail, localDate, localTime, "更新单词本记录", myWord.getEnglish());
        //session.setAttribute("myWord", null);
        return "redirect:/mywords";
    }

    @RequestMapping("/addSelect/{english}")
    public String addSelect(@PathVariable String english,
                            HttpServletRequest request,
                            HttpSession session) {
        session.setAttribute("msg", null);
        String mail = (String) request.getSession().getAttribute("loginuser");
        String chinese = dictionaryService.getByEnglish(english).getChinese();
        String voice = dictionaryService.getByEnglish(english).getVoice();
        MyWord words = myWordService.getWord(mail, english);
        if (words == null) {
            myWordService.insert(mail, english, chinese, voice);
            LocalDate localDate = LocalDate.now();
            LocalTime localTime = LocalTime.now().withNano(0);
            historyService.insert(mail, localDate, localTime, "添加单词本", english);
        } else {
            session.setAttribute("msg", "单词本已有");
        }

        return "redirect:/select";
    }

    @RequestMapping("/selectMyWord")
    public String select(@RequestParam("english") String english,
                         HttpSession session
    ) {
        String mail = session.getAttribute("loginuser").toString();
        PageInfo<MyWord> pageInfo;
        PageHelper.startPage(1, 5);
        pageInfo = new PageInfo<>(myWordService.get(mail, english));
        //myWords = myWordService.getByMail(mail);
        session.setAttribute("myWords", pageInfo);
        if (myWordService.get(mail, english).isEmpty())
            session.setAttribute("msg", "没有这个词");
        return "mywords";
    }
}

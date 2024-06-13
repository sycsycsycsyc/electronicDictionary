package com.shi.electronicdictionary.controller;

import com.shi.electronicdictionary.pojo.Dictionary;
import com.shi.electronicdictionary.service.DictionaryService;
import com.shi.electronicdictionary.service.HistoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class DictionaryController {
    @Resource
    DictionaryService dictionaryService;

    @Resource
    HistoryService historyService;

    @RequestMapping("/select/result")
    public String select(@RequestParam("word") String word,
                         HttpSession session) {
        String mail = (String) session.getAttribute("loginuser");
        if (word.equals("")) {
            session.setAttribute("msg", null);
            session.setAttribute("dictionarySelect", null);
        } else {
            Dictionary dictionarySelect;
            int a = word.charAt(0);
            if ((a >= 65 && a <= 90) || (a >= 97 && a <= 122)) {
                dictionarySelect = dictionaryService.getByEnglish(word);
            } else
                dictionarySelect = dictionaryService.getByChinese(word);
            if (dictionarySelect == null) {
                session.setAttribute("msg", "没有这个词");
                session.setAttribute("dictionarySelect", null);
            } else {
                session.setAttribute("msg", null);
                session.setAttribute("dictionarySelect", dictionarySelect);
                LocalDate localDate = LocalDate.now();
                LocalTime localTime = LocalTime.now().withNano(0);
                historyService.insert(mail, localDate, localTime, "查询单词", word);
            }

        }
        return "redirect:/select";
    }
}

/*    @RequestMapping("/page")
    public String insert(@RequestParam(value = "pageNum",required = false,defaultValue = "1")Integer pageNum,
                         @RequestParam(value = "pageSize",required = false,defaultValue = "1")Integer pageSize,
                         HttpServletRequest request,
                         Model model,
                         HttpSession session) {
        String mail = (String) session.getAttribute("loginuser");

        //List<Dictionary> all = dictionaryService.getAll(mail);
        //PageInfo<Dictionary> pageInfo = new PageInfo<>(all);
        PageInfo<Dictionary> studentPageInfo = dictionaryService.getAllPage(pageNum, pageSize,mail);
//        System.out.println(studentPageInfo);
        //System.out.println(studentPageInfo.getList());
//        System.out.println(studentPageInfo.getList().indexOf(1)+1);
        model.addAttribute("studentPageInfo",studentPageInfo);
        return "3";
    }
}*/

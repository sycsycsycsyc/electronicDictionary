package com.shi.electronicdictionary.service;

import com.shi.electronicdictionary.mapper.HistoryMapper;
import com.shi.electronicdictionary.pojo.History;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Service
public class HistoryServiceImpl  implements HistoryService{
    @Resource
    HistoryMapper historyMapper;
    @Override
    public List<History> getByMail(String mail) {
        return historyMapper.getByMail(mail);
    }

    @Override
    public List<String> getByDate(String mail) {
        return historyMapper.getByDate(mail);
    }

    @Override
    //@DataSource(name ="master")
    public int insert(String mail, LocalDate date, LocalTime time, String option, String project) {
        return historyMapper.insert(mail,date,time, option,project);
    }
}


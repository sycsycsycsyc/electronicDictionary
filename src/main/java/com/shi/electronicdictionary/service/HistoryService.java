package com.shi.electronicdictionary.service;

import com.shi.electronicdictionary.pojo.History;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface HistoryService {
    List<History> getByMail(String mail);


    List<String> getByDate(String mail);

    int insert(String mail, LocalDate date,LocalTime time ,String option ,String project);
}

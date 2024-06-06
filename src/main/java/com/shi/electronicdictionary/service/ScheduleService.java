package com.shi.electronicdictionary.service;

import com.shi.electronicdictionary.pojo.Schedule;

import java.util.List;

public interface ScheduleService {

    List<Schedule> getByFlag(String mail, String flag);

    Schedule getByenglish(String mail, String flag, String english);

    int insert(String mail, String english, String chinese, String voice, String flag);

    int delete(String mail, String flag, String english);

    int getCount(String mail, String library);

    int getCountAll();

    //添加资料库
    int getCount4();

    int getCount6();

    int getCountyan();

    int update(int count, String mail, String english, String flag);

    int count(String mail, String english, String flag);

    int deleteCount();

    List<Schedule> getByCount(String mail, String flag);
}

package com.shi.electronicdictionary.service;

import com.shi.electronicdictionary.mapper.ScheduleMapper;
import com.shi.electronicdictionary.pojo.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    ScheduleMapper scheduleMapper;

    @Override
    public List<Schedule> getByFlag(String mail, String flag) {
        return scheduleMapper.getByFlag(mail, flag);
    }

    @Override
    public int insert(String mail, String english, String chinese, String voice, String flag) {
        return scheduleMapper.insert(mail, english, chinese, voice, flag);
    }

    @Override
    public int delete(String mail, String flag, String english) {
        return scheduleMapper.delete(mail, flag, english);
    }

    @Override
    public Schedule getByenglish(String mail, String flag, String english) {
        return scheduleMapper.getByenglish(mail, flag, english);
    }

    @Override
    public int getCount(String mail, String library) {
        return scheduleMapper.getCount(mail, library);
    }

    @Override
    public int getCountAll() {
        return scheduleMapper.getCountAll();
    }

    @Override
    public int getCount4() {
        return scheduleMapper.getCount4();
    }


    @Override
    public int getCount6() {
        return scheduleMapper.getCount6();
    }

    @Override
    public int getCountyan() {
        return scheduleMapper.getCountyan();
    }


    @Override
    public int update(int count, String mail, String english, String flag) {
        return scheduleMapper.update(count, mail, english, flag);
    }

    @Override
    public int count(String mail, String english, String flag) {
        return scheduleMapper.count(mail, english, flag);
    }

    @Override
    public int deleteCount() {
        return scheduleMapper.deleteCount();
    }

    @Override
    public List<Schedule> getByCount(String mail, String flag) {
        return scheduleMapper.getByCount(mail,flag);
    }
}

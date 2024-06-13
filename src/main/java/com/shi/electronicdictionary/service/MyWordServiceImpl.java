package com.shi.electronicdictionary.service;

import com.shi.electronicdictionary.mapper.MyWordMapper;
import com.shi.electronicdictionary.pojo.MyWord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MyWordServiceImpl implements MyWordService {
    @Resource
    MyWordMapper myWordMapper;

    @Override
    public MyWord getWord(String mail, String english) {
        return myWordMapper.getWord(mail,english);
    }

    @Override
    public List<MyWord> get(String mail, String english) {
        return myWordMapper.get(mail,english);
    }

    @Override
    public List<MyWord> getByMail(String mail) {
        return myWordMapper.getByMail(mail);
    }

    @Override
    public int insert(String mail, String english, String chinese, String voice) {
        return myWordMapper.insert(mail,english,chinese,voice);
    }

    @Override
    public int update(String chinese,String description,String mail, String english ) {
        return myWordMapper.update(chinese,description,mail,english);
    }

    @Override
    public int delete(String mail, String english) {
        return myWordMapper.delete(mail,english);
    }
}

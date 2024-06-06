package com.shi.electronicdictionary.service;

import com.shi.electronicdictionary.pojo.MyWord;

import java.util.List;

public interface MyWordService {
    List<MyWord> getByMail(String mail);
    List<MyWord> get(String mail, String english);
    MyWord getWord(String mail, String english);

    int insert(String mail, String english, String chinese, String voice);

    int update(String chinese, String description, String mail, String english);

    int delete(String mail, String english);
}

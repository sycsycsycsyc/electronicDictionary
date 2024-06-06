package com.shi.electronicdictionary.service;

import com.shi.electronicdictionary.pojo.User;

import java.time.LocalTime;
import java.util.List;

public interface UserService {
    User getByMail(String mail);
    int insert(String name,String password,String mail);
    int updatePw(String password,String mail);
    int updateLi(String library,String mail);
    int updateTime(LocalTime localTime, String mail);
    int updateName(String name,String mail);
    int updateFlag(int flag, String mail);
    int updateFlag2(String flag2, String mail);
    int updateCount(int count , String mail);
    List<User> getAll();
    int updateUUID(String UUID, String mail);
    User getByUUID(String UUID);
}

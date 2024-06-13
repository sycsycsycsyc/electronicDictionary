package com.shi.electronicdictionary.service;

import com.shi.electronicdictionary.mapper.UserMapper;
import com.shi.electronicdictionary.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public User getByMail(String mail) {
        return userMapper.getByMail(mail);
    }

    @Override
    public int insert(String name, String password, String mail) {
        return userMapper.insert(name,password,mail);
    }

    @Override
    public int updatePw(String password,String mail) {
        return userMapper.updatePw(mail,password);
    }

    @Override
    public int updateLi(String library,String mail) {
        return userMapper.updateLi(library,mail);
    }

    @Override
    public int updateName(String name, String mail) {
        return userMapper.updateName(name,mail);
    }

    @Override
    public int updateFlag(int flag, String mail) {
        return userMapper.updateFlag(flag,mail);
    }

    @Override
    public int updateCount(int count, String mail) {
        return  userMapper.updateCount(count,mail);
    }

    @Override
    public int updateUUID(String UUID, String mail) {
        return userMapper.updateUUID(UUID,mail);
    }

    @Override
    public int updateFlag2(String flag2, String mail) {
        return userMapper.updateFlag2(flag2,mail);
    }

    @Override
    public User getByUUID(String UUID) {
        return userMapper.getByUUID(UUID);
    }

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }

    @Override
    public int updateTime(LocalTime localTime, String mail ) {
        return userMapper.updateTime(localTime,mail);
    }
}


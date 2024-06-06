package com.shi.electronicdictionary.mapper;

import com.shi.electronicdictionary.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    User getByMail(String mail);

    @Select("SELECT * from user")
    List<User> getAll();

    int insert(String name, String password, String mail);

    int updatePw(String password, String mail);

    @Update("update user set name=#{name} where mail=#{mail}")
    int updateName(String name, String mail);

    int updateLi(String library, String mail);

    int updateTime(LocalTime localTime, String mail);
    @Update("update user set flag=#{flag} where mail=#{mail}")
    int updateFlag(int flag, String mail);
    @Update("update user set count=#{count} where mail=#{mail}")
    int updateCount(int count , String mail);
    @Update("update user set flag2=#{flag2} where mail=#{mail}")
    int updateFlag2(String flag2, String mail);
    @Update("update user set UUID=#{UUID} where mail=#{mail}")
    int updateUUID(String UUID, String mail);
    @Select(" select * from user where UUID=#{UUID}")
    User getByUUID(String UUID);
}

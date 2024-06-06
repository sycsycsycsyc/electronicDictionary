package com.shi.electronicdictionary.mapper;

import com.shi.electronicdictionary.pojo.Schedule;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ScheduleMapper {
    List<Schedule> getByFlag(String mail, String flag);

    Schedule getByenglish(String mail, String flag, String english);

    @Insert("insert into schedule (mail, english, chinese, voice, flag) VALUES (#{mail},#{english}, #{chinese}, #{voice}, #{flag})")
    int insert(String mail, String english, String chinese, String voice, String flag);

    @Delete("delete from schedule where mail=#{mail} and flag=#{flag}")
    int delete(String mail, String flag, String english);

    @Select("SELECT  count(*) FROM schedule where mail=#{mail} and flag=#{library}")
    int getCount(String mail, String library);

    @Select("SELECT  count(*) FROM dictionary")
    int getCountAll();

    //添加资料库
    @Select("SELECT  count(*) FROM dictionary where flag4='1'")
    int getCount4();

    @Select("SELECT  count(*) FROM dictionary where flag6='1'")
    int getCount6();

    @Select("SELECT  count(*) FROM dictionary where flagyan='1'")
    int getCountyan();

    @Update("update schedule set count=#{count} where mail=#{mail} and english=#{english} and flag=#{flag}")
    int update(int count, String mail, String english, String flag);

    @Select("SELECT count FROM schedule where mail=#{mail} and english=#{english} and flag=#{flag}")
    int count(String mail, String english, String flag);

    @Delete("delete from schedule where count>=3")
    int deleteCount();

    @Select("select * from schedule where mail=#{mail} and flag=#{flag} and count>-3 ")
    List<Schedule> getByCount(String mail, String flag);
}
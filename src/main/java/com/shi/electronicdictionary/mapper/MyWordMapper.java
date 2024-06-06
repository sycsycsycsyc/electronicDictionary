package com.shi.electronicdictionary.mapper;

import com.shi.electronicdictionary.pojo.MyWord;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MyWordMapper {

    List<MyWord> getByMail(String mail);

    @Select("select * from myword where mail=#{mail} and english=#{english}")
    MyWord getWord(String mail, String english);

    @Select("select * from myword where mail=#{mail} and english=#{english}")
    List<MyWord> get(String mail, String english);

    @Insert("insert into myword (mail,english,chinese,voice) values (#{mail},#{english},#{chinese},#{voice})")
    int insert(String mail, String english, String chinese, String voice);

    @Update("update myword set chinese=#{chinese},description=#{description} where mail=#{mail} and english=#{english}")
    int update(String chinese, String description, String mail, String english);

    @Delete("delete from myword where mail=#{mail} and english=#{english}")
    int delete(String mail, String english);
}

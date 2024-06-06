package com.shi.electronicdictionary.mapper;

import com.shi.electronicdictionary.pojo.History;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
@Mapper
public interface HistoryMapper {

    List<History> getByMail(String mail);

    @Select("select date from history where mail=#{mail} group by date order by date desc")
    List<String> getByDate(String mail);

    //@Insert("insert into history(mail, date,  time,`option`) values (#{mail},#{localDate},#{time},#{option})")
    int insert(String mail, LocalDate date,LocalTime time, String option ,String project);
}

package com.shi.electronicdictionary.mapper;

import com.shi.electronicdictionary.pojo.Library;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LibraryMapper {
    @Select("select * from library")
    List<Library> getAll();


}

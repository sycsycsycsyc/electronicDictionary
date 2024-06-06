package com.shi.electronicdictionary.mapper;

import com.github.pagehelper.PageInfo;
import com.shi.electronicdictionary.pojo.Dictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DictionaryMapper {
    List<Dictionary> getAll(String mail);

    PageInfo<Dictionary> getAllPage(String mail);

    List<Dictionary> getByFlag(String flag);

    @Select("select * from dictionary where english=#{english}")
    Dictionary getByEnglish(String english);

    @Select("select * from dictionary where chinese=#{chinese}")
    Dictionary getByChinese(String chinese);

    //添加资料库

    List<Dictionary> getByFlag4(String mail);

    List<Dictionary> getByFlag6(String mail);

    List<Dictionary> getByFlagyan(String mail);
}

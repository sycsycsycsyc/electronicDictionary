package com.shi.electronicdictionary.service;

import com.github.pagehelper.PageInfo;
import com.shi.electronicdictionary.pojo.Dictionary;

import java.util.List;

//声明方法
public interface DictionaryService {
    List<Dictionary> getAll(String mail);
    List<Dictionary> getByFlag(String flag);
    Dictionary getByEnglish(String english);
    Dictionary getByChinese(String chinese);
    int insert(Dictionary dictionary);
    //添加资料库
    List<Dictionary> getByFlag4(String mail);
    List<Dictionary> getByFlag6(String mail);
    List<Dictionary> getByFlagyan(String mail);
    PageInfo<Dictionary> getAllPage(Integer pageNum, Integer pageSize, String mail);
}

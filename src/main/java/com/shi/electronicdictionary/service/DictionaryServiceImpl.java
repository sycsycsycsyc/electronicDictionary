package com.shi.electronicdictionary.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shi.electronicdictionary.mapper.DictionaryMapper;
import com.shi.electronicdictionary.pojo.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {


    @Autowired
    DictionaryMapper dictionaryMapper;

    @Override
    public List<Dictionary> getByFlag(String flag) {
        return dictionaryMapper.getByFlag(flag);
    }

    @Override
    public List<Dictionary> getAll(String mail) {
        return dictionaryMapper.getAll(mail);
    }

    @Override
    public PageInfo<Dictionary> getAllPage(Integer pageNum, Integer pageSize,String mail) {
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<Dictionary> pageInfo = new PageInfo<>(dictionaryMapper.getAll( mail));
        return pageInfo;
    }

    @Override
    public Dictionary getByEnglish(String english) {
        return dictionaryMapper.getByEnglish(english);
    }

    @Override
    public Dictionary getByChinese(String chinese) {
        return dictionaryMapper.getByChinese(chinese);
    }

    @Override
    public List<Dictionary> getByFlag4(String mail) {
        return dictionaryMapper.getByFlag4(mail);
    }

    @Override
    public List<Dictionary> getByFlag6(String mail) {
        return dictionaryMapper.getByFlag6(mail);
    }

    @Override
    public List<Dictionary> getByFlagyan(String mail) {
        return dictionaryMapper.getByFlagyan(mail);
    }

    @Override
    public int insert(Dictionary dictionary) {
        return 0;
    }
}

package com.shi.electronicdictionary.service;

import com.shi.electronicdictionary.mapper.LibraryMapper;
import com.shi.electronicdictionary.pojo.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LibraryServiceImpl implements LibraryService{
    @Autowired
    LibraryMapper libraryMapper;
    @Override
    public List<Library> getAll() {
        return libraryMapper.getAll();
    }
}

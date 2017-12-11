package com.aguang.jinjuback.services;

import com.aguang.jinjuback.dao.JinjuDao;
import com.aguang.jinjuback.pojo.Jinju;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JinjuService {

    @Autowired
    JinjuDao jinjuDao;

    public void createJinju(Jinju jinju){
        jinjuDao.createJinju(jinju);
    }

    public Jinju getJinju(Integer id){
        return jinjuDao.getJinju(id);
    }
}

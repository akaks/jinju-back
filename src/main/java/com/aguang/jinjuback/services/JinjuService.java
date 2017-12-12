package com.aguang.jinjuback.services;

import com.aguang.jinjuback.dao.JinjuDao;
import com.aguang.jinjuback.pojo.Jinju;
import com.aguang.jinjuback.pojo.Result;
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

    public Result getJinjuList(Integer pageIndex, Integer pageSize){
        Result result = new Result();
        Integer m = (pageIndex -1 )* pageSize;
        result.setSuccess(jinjuDao.getJinjuList(m, pageSize), "金句列表数据成功");
        return result;
    }
}

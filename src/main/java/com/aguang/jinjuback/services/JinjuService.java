package com.aguang.jinjuback.services;

import com.aguang.jinjuback.dao.JinjuDao;
import com.aguang.jinjuback.dto.JinjuListDto;
import com.aguang.jinjuback.pojo.Jinju;
import com.aguang.jinjuback.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JinjuService {

    @Autowired
    JinjuDao jinjuDao;

    public Result createJinju(Jinju jinju) {
        jinjuDao.createJinju(jinju);
        Result result = new Result();
        result.setSuccess(null, "创建成功");
        return result;
    }

    public Jinju getJinju(int id) {
        return jinjuDao.getJinju(id);
    }

    public Result getJinjuList(int pageIndex, int pageSize) {
        Integer m = (pageIndex - 1) * pageSize;
        ArrayList<Jinju> arrayList = jinjuDao.getJinjuList(m, pageSize);
        Integer total = jinjuDao.getListCount();

        JinjuListDto jinjuListDto = new JinjuListDto();
        jinjuListDto.setJinjuList(arrayList);
        jinjuListDto.setTotal(total);

        Result result = new Result();
        result.setSuccess(jinjuListDto, "金句列表数据成功");
        return result;
    }
}

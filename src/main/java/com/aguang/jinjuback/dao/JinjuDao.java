package com.aguang.jinjuback.dao;

import com.aguang.jinjuback.pojo.Jinju;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface JinjuDao {

    void createJinju(Jinju jinju);

    Jinju getJinju(Integer id);
}

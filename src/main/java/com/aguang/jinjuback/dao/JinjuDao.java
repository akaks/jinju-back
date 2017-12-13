package com.aguang.jinjuback.dao;

import com.aguang.jinjuback.pojo.Jinju;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface JinjuDao {

    void createJinju(Jinju jinju);

    Jinju getJinju(Integer id);

    ArrayList<Jinju> getJinjuList(@Param("m") Integer m, @Param("n") Integer n);

    Integer getListCount();
}

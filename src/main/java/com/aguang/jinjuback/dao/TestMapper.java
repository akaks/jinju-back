package com.aguang.jinjuback.dao;

import com.aguang.jinjuback.model.TestInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TestMapper extends tk.mybatis.mapper.common.Mapper<TestInfo> {

    @Select("select * from test")
    List<TestInfo> list();
}

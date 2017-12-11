package com.aguang.jinjuback.dao;

import com.aguang.jinjuback.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    void createUser(@Param("username") String username,@Param("password") String password,@Param("curTime") Long curTime);

    UserInfo getUser(@Param("id") Integer id);
}

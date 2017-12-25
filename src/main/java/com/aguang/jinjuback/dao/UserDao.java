package com.aguang.jinjuback.dao;

import com.aguang.jinjuback.model.User;
import com.aguang.jinjuback.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    void createUser(@Param("username") String username,@Param("password") String password,@Param("curTime") Long curTime);

    UserInfo getUser(@Param("id") int id);

//    UserInfo getUserByUsername(@Param("username") String username);

    void updateUser(UserInfo userInfo);

    @Select("select * from jj_user where username=#{username}")
    User getUserByUsername(String username);

    @Select("select * from jj_user where user_id=#{userId}")
    User getUserById(Integer userId);
}

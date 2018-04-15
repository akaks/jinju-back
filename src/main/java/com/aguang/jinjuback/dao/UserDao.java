package com.aguang.jinjuback.dao;

import com.aguang.jinjuback.model.po.User;
import com.aguang.jinjuback.model.pojo.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Mapper
@Repository
public interface UserDao {

    @Insert("INSERT INTO jinju_db.jj_user(username,password,photo_url,create_time,update_time) VALUES(#{username},#{password},#{photoUrl},#{curTime},#{curTime})")
    void createUser(@Param("username") String username,
                    @Param("password") String password,
                    @Param("photoUrl")String photoUrl,
                    @Param("curTime") Long curTime);

    @Select("SELECT * FROM jinju_db.jj_user WHERE user_id = #{id}")
    User getUser(@Param("id") int id);

    UserInfo getUserInfo(@Param("id") int id);

    void updateUser(User user);

    @Select("select * from jj_user where username=#{username}")
    User getUserByUsername(String username);

    @Update("update jinju_db.jj_user set last_login_time=#{curTime} where user_id=#{id}")
    void updateLastLoginTime(@Param("id") Integer id, @Param("curTime") Long curTime);

    @Select("select * from jj_user where user_id=#{userId}")
    User getUserById(Integer userId);

    /**
     * 更新用户头像
     * @param id
     * @param photoUrl
     */
    @Update("update jinju_db.jj_user set photo_url=#{photoUrl} where user_id=#{id}")
    void updatePhotoUrl(@Param("id") Integer id, @Param("photoUrl") String photoUrl);

    /**
     * 根据username获取权限
     * @param username
     * @return
     */
    @Select(value = "SELECT a.authority_name" +
            " FROM jj_user u" +
            " LEFT JOIN sys_user_role ur ON u.user_id = ur.user_id" +
            " LEFT JOIN sys_role_authority ra ON ur.role_id = ra.role_id" +
            " LEFT JOIN sys_authority a ON ra.authority_id = a.id" +
            " WHERE u.username = #{username}")
    Set<String> selectAuthorityByName(String username);

    @Select("select * from jj_user where username=#{username}")
    User loadUserByUsername(String username);
}

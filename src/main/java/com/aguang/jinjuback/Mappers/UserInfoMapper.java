package com.aguang.jinjuback.Mappers;

public interface UserInfoMapper {
    void createUser(String tel,String pwd);
    UserInfo getUser(Integer id);
    void updateUser(String user_id,String nickname);
    void deleteUserByUserId(Integer id);
}

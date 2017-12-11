package com.aguang.jinjuback.services;

import com.aguang.jinjuback.dao.UserDao;
import com.aguang.jinjuback.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public String createUser(String username,String password){
        UserInfo user = new UserInfo();
        user  = userDao.getUserByUsername(username);
        if(user ==null){
            Long curTime = new Date().getTime();
            userDao.createUser(username,password,curTime);
            return "success";
        }
        return "用户名已存在";
    }

    public UserInfo getUser(Integer id){
        return userDao.getUser(id);
    }

    public String login(String username,String password){
        UserInfo user = new UserInfo();
        user  = userDao.getUserByUsername(username);
        if(user == null){
            return "用户不存在";
        }else if(!password.equals(user.getPassword())){
            return "密码错误";
        }
        return "success";
    }
}

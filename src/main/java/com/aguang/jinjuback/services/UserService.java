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

    public void createUser(String username,String password){
        Long curTime = new Date().getTime();
        userDao.createUser(username,password,curTime);
    }

    public UserInfo getUser(Integer id){
        return userDao.getUser(id);
    }
}

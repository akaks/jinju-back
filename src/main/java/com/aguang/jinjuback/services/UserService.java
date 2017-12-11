package com.aguang.jinjuback.services;

import com.aguang.jinjuback.dao.UserDao;
import com.aguang.jinjuback.pojo.Result;
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

    public Result login(String username,String password){
        Result result = new Result();
        UserInfo user = new UserInfo();
        user  = userDao.getUserByUsername(username);
        if(user == null){
            result.setError(null,"用户不存在");
            return result;
        }else if(!password.equals(user.getPassword())){
            result.setError(null,"密码错误");
            return result;
        }
        user.setPassword(null);
        result.setError(user,"登录成功");
        return result;
    }
}

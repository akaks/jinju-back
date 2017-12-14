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

    public Result createUser(String username, String password) {
        Result result = new Result();
        UserInfo user = new UserInfo();
        user = userDao.getUserByUsername(username);
        if (user == null) {
            Long curTime = new Date().getTime();
            userDao.createUser(username, password, curTime);
            result.setSuccess(null, "注册成功");
            return result;
        }
        result.setError(null, "用户名已存在");
        return result;
    }

    public Result getUser(int id) {
        Result result = new Result();
        UserInfo user = userDao.getUser(id);
        if (user == null) {
            result.setError(null, "用户不存在");
            return result;
        }
        user.setPassword(null);
        result.setSuccess(user, "用户数据获取成功");
        return result;
    }

    public Result login(String username, String password) {
        Result result = new Result();
        UserInfo user = new UserInfo();
        user = userDao.getUserByUsername(username);
        if (user == null) {
            result.setError(null, "用户不存在");
            return result;
        } else if (!password.equals(user.getPassword())) {
            result.setError(null, "密码错误");
            return result;
        }
        user.setPassword(null);
        result.setSuccess(user, "登录成功");
        return result;
    }

    public Result updateUser(UserInfo userInfo){
        Result result = new Result();
        UserInfo user = userDao.getUser(userInfo.getUser_id());
        if (user == null) {
            result.setError(null, "用户不存在");
            return result;
        }
        user = userDao.getUserByUsername(userInfo.getUsername());
        if(user != null && (user.getUser_id() != userInfo.getUser_id())){
            result.setError(null, "该用户名已存在，不要与别人一样哦~");
            return result;
        }
        userDao.updateUser(userInfo);
        result.setSuccess(user, "更新成功");
        return result;
    }
}

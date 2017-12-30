package com.aguang.jinjuback.controllers;

import com.aguang.jinjuback.model.User;
import com.aguang.jinjuback.pojo.Result;
import com.aguang.jinjuback.pojo.UserInfo;
import com.aguang.jinjuback.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public Result createUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        return userService.createUser(username, password);
    }

    @GetMapping("/{id}")
    public Result getUser(@PathVariable("id") int id) {
        return userService.getUser(id);
    }

    @PostMapping("/login")
    public Result login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
        Result result = userService.login(username, password);
        request.getSession().setAttribute("userId", ((User)result.getData()).getUserId());
        return result;
    }

    @PostMapping("/update")
    public Result updateUser(@RequestBody UserInfo userInfo){
        return userService.updateUser(userInfo);
    }
}

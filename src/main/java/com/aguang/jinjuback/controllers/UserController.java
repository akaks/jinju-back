package com.aguang.jinjuback.controllers;

import com.aguang.jinjuback.pojo.Result;
import com.aguang.jinjuback.pojo.UserInfo;
import com.aguang.jinjuback.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public Result createUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        return userService.createUser(username, password);
    }

    @GetMapping("/getUser/{id}")
    public Result getUser(@PathVariable("id") Integer id) {
        return userService.getUser(id);
    }

    @PostMapping("/login")
    public Result login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return userService.login(username, password);
    }
}

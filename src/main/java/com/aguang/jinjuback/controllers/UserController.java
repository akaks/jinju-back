package com.aguang.jinjuback.controllers;

import com.aguang.jinjuback.pojo.UserInfo;
import com.aguang.jinjuback.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public String createUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        return userService.createUser(username, password);
    }

    @GetMapping("/getUser/{id}")
    public UserInfo getUser(@PathVariable("id") Integer id) {
        return userService.getUser(id);
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return userService.login(username, password);
    }
}

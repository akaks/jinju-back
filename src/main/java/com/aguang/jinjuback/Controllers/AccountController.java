package com.aguang.jinjuback.Controllers;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AccountController {

    @RequestMapping(value="/login",method = RequestMethod.POST)
    @ResponseBody
    public String login(Map<String,Object> reqMap){
        String name = reqMap.get("name").toString();
        String password = reqMap.get("password").toString();
        return name + password;
    }
}

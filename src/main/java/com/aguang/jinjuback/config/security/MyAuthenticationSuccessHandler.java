package com.aguang.jinjuback.config.security;

import com.aguang.jinjuback.model.po.User;
import com.aguang.jinjuback.model.pojo.Result;
import com.aguang.jinjuback.services.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        Result result = new Result();

//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        httpServletRequest.getSession().setAttribute("authentication", authentication);

        User user = userService.loadUserByUsername(authentication.getName());

        result.setSuccess(user, "login success!");

        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}

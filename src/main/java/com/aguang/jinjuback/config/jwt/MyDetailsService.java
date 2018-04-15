package com.aguang.jinjuback.config.jwt;

import com.aguang.jinjuback.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Set;

public class MyDetailsService implements MyUserDetailsService<Authentication> {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(Authentication authentication) throws UsernameNotFoundException {
        //接收权限的list
        ArrayList<SimpleGrantedAuthority> list = new ArrayList<>();
        //获取用户名
        String username = authentication.getName();
        //数据库中查询权限
        Set<String> authoritySet = userService.selectAuthorityByName(username);
        System.out.println(authoritySet.toString());
        for (String authority : authoritySet) {
            list.add(new SimpleGrantedAuthority(authority)); //数据库的权限赋值语句记得改回
        }

        list.add(new SimpleGrantedAuthority("app_auth"));

        User details = new User(username, "pwd", list);
        return details;
    }

}
package com.aguang.jinjuback.config.security;

import com.aguang.jinjuback.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Set;

public class MyDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //接收权限的list
        ArrayList<SimpleGrantedAuthority> list = new ArrayList<>();
        //获取用户名
//        String username = authentication.getName();

        com.aguang.jinjuback.model.po.User user = userService.loadUserByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("user: "+username +" not found.");
        }

        //数据库中查询权限
        Set<String> authoritySet = userService.selectAuthorityByName(username);
        System.out.println(authoritySet.toString());
        for (String authority : authoritySet) {
            if(authority != null) {
                list.add(new SimpleGrantedAuthority(authority));
            }
        }

        if(list.isEmpty()) {
            list.add(new SimpleGrantedAuthority("USER"));
        }

        User userDetails = new User(username, user.getPassword(), list);

        return userDetails;
    }
}

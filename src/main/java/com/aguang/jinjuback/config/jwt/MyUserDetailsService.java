package com.aguang.jinjuback.config.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface MyUserDetailsService<T extends Authentication> {
	UserDetails loadUserByUsername(T authentication) throws UsernameNotFoundException;
}

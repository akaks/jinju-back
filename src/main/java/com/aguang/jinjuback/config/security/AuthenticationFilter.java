package com.aguang.jinjuback.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证过滤器
 *
 */
public class AuthenticationFilter extends OncePerRequestFilter {
	private static Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        Authentication authentication = (Authentication) request.getSession().getAttribute("authentication");

        if(authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

//        if(StringUtils.isNotBlank(username)) {
//            ArrayList<SimpleGrantedAuthority> list = new ArrayList<>();
//            list.add(new SimpleGrantedAuthority("USER"));
//            User userDetails = new User(username, "11", list);
//
//            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            logger.info(String.format("Authenticated user %s, setting security context", username));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }

        chain.doFilter(request, response);
    }

}

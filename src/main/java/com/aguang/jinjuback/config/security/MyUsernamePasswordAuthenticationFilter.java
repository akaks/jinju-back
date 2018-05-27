package com.aguang.jinjuback.config.security;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

//        if (!request.getMethod().equals("POST")) {
//            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
//        }

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        if (username == null) {
            username = "akaks";
        }

        if (password == null) {
            password = "1";
        }

        username = username.trim();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

//      //登录验证码，如需要开启把下面注释去掉则可
//      String authCode = StringUtils.defaultString(request.getParameter("authCode"));
//      if(!AdwImageCaptchaServlet.validateResponse(request, authCode)){
//          throw new AuthenticationServiceException("validCode.auth.fail");
//      }

        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
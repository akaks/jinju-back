package com.aguang.jinjuback.controllers.base;

import com.aguang.jinjuback.model.po.User;
import com.aguang.jinjuback.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    @Autowired
    UserService userService;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取当前用户Id
     * @return
     */
    public Integer getUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Authentication authentication = (Authentication) request.getSession().getAttribute("authentication");
//        if(StringUtils.isBlank(authentication)) {
//            userId = 333;
//        }
        if(authentication == null) {
            return 333;
        }
        User user = userService.loadUserByUsername(authentication.getName());
        return user.getUserId();
    }

}

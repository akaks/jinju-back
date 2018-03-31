package com.aguang.jinjuback.configuration;

import com.aguang.jinjuback.services.UserService;
import com.aguang.jinjuback.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AccessTokenVerifyInterceptor extends HandlerInterceptorAdapter {

    public final static Logger LOG = LoggerFactory.getLogger(AccessTokenVerifyInterceptor.class);

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        LOG.info("AccessToken executing ...");

        boolean flag = true;

        Integer userId = (Integer) request.getSession().getAttribute("userId");

        String requestURL = request.getRequestURL().toString();

        boolean openFalg = true;

        if(!( requestURL.contains("/user")
                || requestURL.contains("/jinju/list")
                || requestURL.contains("/meiwen/list")
                || requestURL.contains("/get")
                || requestURL.contains("/comment/list")
                || requestURL.contains("/area/list")
                || requestURL.contains("/chat")
                || requestURL.contains("/meiwen/comment/list")
                || requestURL.contains("/admin")
                || requestURL.contains("/error")
                || requestURL.contains("/spider")
                || requestURL.contains("/product")
                || requestURL.contains("/shop")
        ) && openFalg) {
            if (StringUtils.isBlank(userId)) {
                flag = false;
            } else {
                if (!userService.hasUser(userId)) {
                    flag = false;
                }
            }

            if (!flag) {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.setContentType("application/json");
                response.getWriter().print("{\"code\":403, \"message\":\"please login\"}");
            }
        }

        return flag;
    }
}


// 验证

//        ValidationModel v = validationService.verifyAccessToken(accessToken);

// 时间过期

// 用户验证

//        if(v !=null) {
//
//        User user = userService.findById(v.getUid());
//
//        if(user !=null) {
//
//        request.setAttribute(CommonConst.PARAM_USER, user);
//
//        LOG.info("AccessToken SUCCESS ...  user:"+ user.getUserName() +" - "+ accessToken);
//
//        flag =true;
//
//        }
//
//        }
//
//        }
//

//
//        }


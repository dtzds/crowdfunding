package com.dtz.crowd.mvc.interceptor;

import com.dtz.crowd.constant.CrowdConstant;
import com.dtz.crowd.entity.Admin;
import com.dtz.crowd.exception.AccessForbiddenException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //1、从requet域中获取session对象
        HttpSession session = httpServletRequest.getSession();

        //2、从session域中获取admin对象
        Admin admin = (Admin) session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN);

        //3、判断admin对象是否为空
        if (admin == null) {
            //4、抛出异常
            throw new AccessForbiddenException(CrowdConstant.MESSAGE_ACCESS_FORBIDEN);
        }
        //5、如果不为空，则返回true
        return true;
    }


}

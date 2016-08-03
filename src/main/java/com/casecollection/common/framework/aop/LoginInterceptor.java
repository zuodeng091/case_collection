package com.casecollection.common.framework.aop;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Desc 用户登陆拦截器
 * @Create_by Ranger
 * @Create_Date 2015年5月21日下午4:03:41
 */
public class LoginInterceptor implements HandlerInterceptor {

    public static String contextPath;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            StringBuffer stringBuffer = new StringBuffer("http://");
            stringBuffer.append(request.getHeader("Host")).append("/index");
            response.sendRedirect(stringBuffer.toString());
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}

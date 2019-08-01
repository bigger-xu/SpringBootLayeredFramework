package com.cto.freemarker.filter;

import com.cto.freemarker.entity.AdminUser;
import com.cto.freemarker.entity.common.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Zhang Yongei
 * @version 1.0
 * @description
 * @date 2019-03-21
 */
public class MyInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyInterceptor.class);
    /**
     * 进入controller方法之前调用
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        LOGGER.info("====================验证用户是否登录开始=====================");
        boolean flag;
        //如果获取不到用户，跳转到登陆页面
        AdminUser user = SessionUtil.getUser(httpServletRequest);
        //if (user == null) {
        if (false) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/");
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 进入controller方法之后，渲染视图之前调用
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 完成整个请求后调用
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

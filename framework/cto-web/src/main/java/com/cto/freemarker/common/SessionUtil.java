package com.cto.freemarker.common;

import javax.servlet.http.HttpServletRequest;

import com.cto.freemarker.entity.AdminUser;
import lombok.extern.slf4j.Slf4j;

/**
 * session，cookie工具类
 * @author zhangyongwei
 */
@Slf4j
public class SessionUtil {
    
    private static final String SESSION_USER = "base_session_user";

    /**
     * 设置session的值
     * @param request
     * @param key
     * @param value
     */
    public static void setAttr(HttpServletRequest request, String key, Object value){
        request.getSession(true).setAttribute(key, value);
        // 默认存储半小时
        request.getSession(true).setMaxInactiveInterval(1800);
    }
    
    /**
     * 获取session的值
     * @param request
     * @param key
     */
    public static Object getAttr(HttpServletRequest request, String key){
        return request.getSession(true).getAttribute(key);
    }
    
    /**
     * 删除Session值
     * @param request
     * @param key
     */
    public static void removeAttr(HttpServletRequest request, String key){
        request.getSession(true).removeAttribute(key);
    }
    
    /**
     * 设置用户信息 到session
     * @param request
     * @param user
     */
    public static void setUser(HttpServletRequest request, AdminUser user){
        request.getSession(true).setAttribute(SESSION_USER, user);
    }
    
    /**
     * 从session中获取用户信息
     * @param request
     * @return SysUser
     */
    public static AdminUser getUser(HttpServletRequest request){
       return (AdminUser)request.getSession().getAttribute(SESSION_USER);
    }
    
    /**
     * 从session中获取用户信息
     * @param request
     * @return SysUser
     */
    public static Long getUserId(HttpServletRequest request){
        AdminUser user = getUser(request);
        if(user != null){
            return user.getId();
        }
       return null; 
    }
    
    /**
     * 从session中获取用户信息
     * @param request
     * @return SysUser
     */
    public static void removeUser(HttpServletRequest request){
       removeAttr(request, SESSION_USER);
    }
}

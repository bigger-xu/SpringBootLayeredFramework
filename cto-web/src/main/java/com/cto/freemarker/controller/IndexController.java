package com.cto.freemarker.controller;

import com.cto.freemarker.common.SessionUtil;
import com.cto.freemarker.controller.base.BaseController;
import com.cto.freemarker.entity.AdminUser;
import com.cto.freemarker.utils.Result;
import com.cto.freemarker.utils.vcode.Captcha;
import com.cto.freemarker.utils.vcode.GifCaptcha;
import com.cto.freemarker.utils.vcode.ValidateCodeProperties;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Zhangyongwei
 */
@Controller
@RequestMapping
public class IndexController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    private static final String CODE_KEY = "_code";

    /**
     * 登陆页
     * @return
     */
    @RequestMapping("/login")
    public String index(){
        return "login";
    }

    /**
     * 登录方法
     * @param userName
     * @param password
     * @param code
     * @return
     */
    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    @ResponseBody
    public Object login(String userName, String password, String code, HttpServletRequest request) {
        if (!StringUtils.isNotBlank(code)) {
            return Result.error("验证码不能为空");
        }
        Session session = super.getSession();
        String sessionCode = (String) session.getAttribute(CODE_KEY);
        if (!code.equalsIgnoreCase(sessionCode)) {
            return Result.error("验证码错误！");
        }
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        try {
            Subject subject = getSubject();
            if (subject != null){
                subject.logout();
                super.login(token);
                AdminUser adminUser = (AdminUser) subject.getPrincipal();
                SessionUtil.setUser(request, adminUser);
                return Result.ok();
            }else{
                return Result.error("登录失败");
            }
        } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
            return Result.error(e.getMessage());
        } catch (AuthenticationException e) {
            return Result.error("认证失败！");
        }
    }

    /**
     * 登录成功后默认跳转首页
     * @param model
     * @return
     */
    @RequestMapping("/index")
//    @RequiresPermissions("article:list")
    public String index(Model model) {
        // 登录成后，即可通过 Subject 获取登录的用户信息
        AdminUser user = super.getCurrentUser();
        model.addAttribute("user", user);
        return "index";
    }

    /**
     * 退出方法
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return "login";
    }

    @RequestMapping(value = "/code")
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");

            Captcha captcha = new GifCaptcha(
                    ValidateCodeProperties.getWidth(),
                    ValidateCodeProperties.getHeight(),
                    ValidateCodeProperties.getLength());
            HttpSession session = request.getSession(true);
            captcha.out(response.getOutputStream());
            session.removeAttribute(CODE_KEY);
            session.setAttribute(CODE_KEY, captcha.text().toLowerCase());
        } catch (Exception e) {
            logger.error("图形验证码生成失败:{}", e);
        }
    }

    @RequestMapping("/403")
    public String error() {
        return "error/403";
    }
}

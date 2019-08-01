package com.cto.freemarker.filter;

import com.cto.freemarker.entity.AdminUser;
import com.cto.freemarker.entity.Menu;
import com.cto.freemarker.entity.Role;
import com.cto.freemarker.entity.common.MenuTreeUtil;
import com.cto.freemarker.entity.vo.AdminUserVo;
import com.cto.freemarker.service.AdminUserService;
import com.cto.freemarker.service.MenuService;
import com.cto.freemarker.service.RoleService;
import com.cto.freemarker.utils.PasswordUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Zhang Yongei
 * @version 1.0
 * @date 2019-06-05
 */
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        AdminUser user = (AdminUser) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //添加菜单的权限标识
        List<Menu> list = menuService.getMenuListByUserId(user.getId());
        for (Menu menu : list) {
            if (StringUtils.isNotBlank(menu.getPermission())) {
                for (String permission : StringUtils.split(menu.getPermission(), ",")) {
                    simpleAuthorizationInfo.addStringPermission(permission);
                }
            }
        }
        // 添加用户权限
        List<Role> roleList = roleService.selectListByUserId(user.getId());
        Set<String> roleSet = roleList.stream().map(Role::getCode).collect(Collectors.toSet());
        simpleAuthorizationInfo.setRoles(roleSet);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户输入的用户名和密码
        String userName = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());

        // 通过用户名到数据库查询用户信息
        AdminUserVo user = adminUserService.getByUserName(userName);
        if (user == null){
            throw new UnknownAccountException("用户不存在！");
        }
        if (!PasswordUtils.validatePassword(password,user.getSalt(),user.getPassword())){
            throw new IncorrectCredentialsException("用户名或密码错误！");
        }
        if ("0".equals(user.getStatus())){
            throw new LockedAccountException("账号已被锁定,请联系管理员！");
        }
        //获取用户对应的菜单
        List<Menu> parentList = menuService.getParentMenuListByUserId(user.getId());
        List<Menu> childList = menuService.getChildMenuListByUserId(user.getId());
        user.setTreeNodeList(MenuTreeUtil.treeMenu(parentList,childList));
        //更新用户登录信息
        AdminUser tmpUser = new AdminUser();
        tmpUser.setLastLoginTime(new Date());
        tmpUser.setLoginCount(user.getLoginCount() + 1);
        tmpUser.setId(user.getId());
        adminUserService.updateBySelective(tmpUser);
        return new SimpleAuthenticationInfo(user, password, getName());
    }
}

/*
 * @(#)  AdminUserVo.java    2019-06-05 10:16:11
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.service.impl;

import com.cto.freemarker.dao.AdminUserMapper;
import com.cto.freemarker.entity.AdminUser;
import com.cto.freemarker.entity.Role;
import com.cto.freemarker.entity.RoleUser;
import com.cto.freemarker.entity.vo.AdminUserVo;
import com.cto.freemarker.service.AdminUserService;
import com.cto.freemarker.service.RoleService;
import com.cto.freemarker.service.RoleUserService;
import com.cto.freemarker.service.base.BaseServiceImpl;
import com.cto.freemarker.utils.Page;
import com.cto.freemarker.utils.PasswordUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文件名 AdminUserServiceImpl.java 
 * 
 * @author 1
 * @date 2019-06-05 10:16:11
 */
@Service
public class AdminUserServiceImpl extends BaseServiceImpl<AdminUser> implements AdminUserService {
    @Autowired
    private AdminUserMapper adminUserMapper;
    @Override
    public AdminUserMapper getNameSpace() {
        return adminUserMapper;
    }
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleUserService roleUserService;

    @Override
    public Page<AdminUser> selectPage(AdminUser adminUser) {
        Page<AdminUser> page = new Page<>(adminUserMapper.selectPageCount(adminUser), adminUser.getPageSize(), adminUser.getPageNum());
        List<AdminUser> result = adminUserMapper.selectPageList(adminUser);
        page.setRows(result == null ? new ArrayList<>() : result);
        return page;
    }

    @Override
    public AdminUserVo getByUserName(String userName) {
        return adminUserMapper.getByUserName(userName);
    }

    @Override
    @Transactional( rollbackFor = Exception.class)
    public void create(AdminUser adminUser) {
        adminUser.setDeleteFlag("0");
        adminUser.setLoginCount(0);
        adminUser = this.setPassword(adminUser);
        super.insert(adminUser);
        Role role = roleService.selectEntityByCode(adminUser.getUserType());
        RoleUser roleUser = new RoleUser();
        roleUser.setAddTime(new Date());
        roleUser.setRoleId(role.getId());
        roleUser.setUserId(adminUser.getId());
        roleUserService.insert(roleUser);
    }

    @Override
    @Transactional( rollbackFor = Exception.class)
    public void updateDefault(AdminUser adminUser) {
        if(StringUtils.isNotEmpty(adminUser.getPassword())){
            adminUser = this.setPassword(adminUser);
        }
        super.updateBySelective(adminUser);

        Role role = roleService.selectEntityByCode(adminUser.getUserType());
        roleUserService.deleteByUserId(adminUser.getId());
        RoleUser roleUser = new RoleUser();
        roleUser.setAddTime(new Date());
        roleUser.setRoleId(role.getId());
        roleUser.setUserId(adminUser.getId());
        roleUserService.insert(roleUser);
    }

    public AdminUser setPassword(AdminUser adminUser) {
        String salt = PasswordUtils.generateSalt();
        adminUser.setSalt(salt);
        adminUser.setPassword(encrypt(adminUser));
        return adminUser;
    }

    public String encrypt(AdminUser adminUser) {
        return PasswordUtils.entryptPassword(adminUser.getPassword(),adminUser.getSalt());
    }

}


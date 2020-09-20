package com.cto.freemarker.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cto.freemarker.entity.AdminUser;
import com.cto.freemarker.entity.Role;
import com.cto.freemarker.entity.RoleUser;
import com.cto.freemarker.entity.query.AdminUserQuery;
import com.cto.freemarker.mapper.AdminUserMapper;
import com.cto.freemarker.service.IAdminUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cto.freemarker.service.IRoleService;
import com.cto.freemarker.service.IRoleUserService;
import com.cto.freemarker.utils.PasswordUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author Bigger-Xu
 * @since 2020-09-19
 */
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements IAdminUserService {
    @Resource
    private AdminUserMapper adminUserMapper;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IRoleUserService roleUserService;

    @Override
    public AdminUserQuery getByUserName(String userName) {
        return adminUserMapper.getByUserName(userName);
    }

    @Override
    @Transactional( rollbackFor = Exception.class)
    public void create(AdminUser adminUser) {
        adminUser.setDeleteFlag("0");
        adminUser.setLoginCount(0);
        adminUser = this.setPassword(adminUser);
        super.save(adminUser);
        Role role = roleService.getOne(Wrappers.<Role>lambdaQuery().eq(Role::getCode,adminUser.getUserType()),false);
        RoleUser roleUser = new RoleUser();
        roleUser.setAddTime(new Date());
        roleUser.setRoleId(role.getId());
        roleUser.setUserId(adminUser.getId());
        roleUserService.save(roleUser);
    }

    @Override
    @Transactional( rollbackFor = Exception.class)
    public void updateDefault(AdminUser adminUser) {
        if(StringUtils.isNotEmpty(adminUser.getPassword())){
            adminUser = this.setPassword(adminUser);
        }
        super.updateById(adminUser);

        Role role = roleService.getOne(Wrappers.<Role>lambdaQuery().eq(Role::getCode,adminUser.getUserType()),false);
        roleUserService.remove(Wrappers.<RoleUser>lambdaQuery().eq(RoleUser::getUserId,adminUser.getId()));
        RoleUser roleUser = new RoleUser();
        roleUser.setAddTime(new Date());
        roleUser.setRoleId(role.getId());
        roleUser.setUserId(adminUser.getId());
        roleUserService.save(roleUser);
    }

    @Override
    public IPage<AdminUser> selectPage(AdminUserQuery search) {
        return adminUserMapper.selectPageVo(new Page<>(search.getPageNum(),search.getPageSize()),search);
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

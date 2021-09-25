package com.cto.freemarker.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cto.freemarker.entity.AdminUser;
import com.cto.freemarker.entity.query.AdminUserQuery;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author Bigger-Xu
 * @since 2020-09-19
 */
public interface IAdminUserService extends IService<AdminUser> {

    AdminUserQuery getByUserName(String userName);

    void create(AdminUser adminUser);

    void updateDefault(AdminUser adminUser);

    IPage<AdminUser> selectPage(AdminUserQuery search);

}

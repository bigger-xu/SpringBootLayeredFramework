/*
 * @(#)  AdminUserVo.java    2019-06-05 10:16:11
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.service;

import com.cto.freemarker.entity.AdminUser;
import com.cto.freemarker.entity.vo.AdminUserVo;
import com.cto.freemarker.service.base.BaseService;
import com.cto.freemarker.utils.Page;

/**
 * 文件名 AdminUserService.java
 * 
 * @author 1
 * @date 2019-06-05 10:16:11
 */
public interface AdminUserService extends BaseService<AdminUser> {
    /**
     * 查询分页
     * @param adminUser
     * @return
     */
    Page<AdminUser> selectPage(AdminUser adminUser);

    /**
     * 根据用户名获取用户
     * @param userName
     * @return
     */
    AdminUserVo getByUserName(String userName);

    /**
     * 创建用户
     * @param adminUser
     */
    void create(AdminUser adminUser);

    /**
     * 修改用户
     * @param adminUser
     */
    void updateDefault(AdminUser adminUser);
}

/*
 * @(#)  RoleVo.java    2019-06-05 10:16:11
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.service;

import com.cto.freemarker.entity.Role;
import com.cto.freemarker.service.base.BaseService;
import com.cto.freemarker.utils.Page;

import java.util.List;

/**
 * 文件名 RoleService.java
 * 
 * @author 1
 * @date 2019-06-05 10:16:11
 */
public interface RoleService extends BaseService<Role> {
    /**
     * 查询分页
     * @param role
     * @return
     */
    Page<Role> selectPage(Role role);

    /**
     * 根据用户ID获取列表
     * @param id
     * @return
     */
    List<Role> selectListByUserId(Long id);

    /**
     * 根据code获取权限
     * @param userType
     * @return
     */
    Role selectEntityByCode(String userType);
}

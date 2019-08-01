/*
 * @(#)  RoleUserVo.java    2019-06-05 10:16:11
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.service;

import com.cto.freemarker.entity.RoleUser;
import com.cto.freemarker.service.base.BaseService;
import com.cto.freemarker.utils.Page;

/**
 * 文件名 RoleUserService.java
 * 
 * @author 1
 * @date 2019-06-05 10:16:11
 */
public interface RoleUserService extends BaseService<RoleUser> {
    /**
     * 查询分页
     * @param roleUser
     * @return
     */
    Page<RoleUser> selectPage(RoleUser roleUser);

    /**
     * 根据用户删除
     * @param id
     */
    void deleteByUserId(Long id);
}

/*
 * @(#)  RoleVo.java    2019-06-05 10:16:11
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.dao;

import com.cto.freemarker.dao.base.BaseDao;
import com.cto.freemarker.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文件名 RoleMapper.java
 *
 * @author 1
 * @date 2019-06-05 10:16:11
 */
@Repository
public interface RoleMapper extends BaseDao<Role> {

    /**
     * 根据用户ID获取菜单列表
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
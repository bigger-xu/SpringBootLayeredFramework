/*
 * @(#)  RoleMenuVo.java    2019-06-05 10:16:11
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.dao;

import com.cto.freemarker.dao.base.BaseDao;
import com.cto.freemarker.entity.RoleMenu;
import org.springframework.stereotype.Repository;

/**
 * 文件名 RoleMenuMapper.java
 *
 * @author 1
 * @date 2019-06-05 10:16:11
 */
@Repository
public interface RoleMenuMapper extends BaseDao<RoleMenu> {

    /**
     * 根据权限ID删除
     * @param id
     */
    void deleteByRoleId(Long id);
}
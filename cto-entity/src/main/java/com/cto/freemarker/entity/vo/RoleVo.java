/*
 * @(#)  RoleVo.java    2019-06-05 10:16:11
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.entity.vo;

import com.cto.freemarker.entity.Role;

/**
 * 文件名 RoleVo.java
 * 
 * @author 1
 * @date 2019-06-05 10:16:11
 */
public class RoleVo extends Role {
    /**
     * 默认构造函数
     *
     * @author 1
     * @date 2019-06-05 10:16:11
     */
    public RoleVo() {

    }
    private String roleIds;

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
}

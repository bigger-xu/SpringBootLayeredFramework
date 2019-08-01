/*
 * @(#)  AdminUserVo.java    2019-06-05 10:16:11
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.entity.vo;

import com.cto.freemarker.entity.AdminUser;
import com.cto.freemarker.entity.common.TreeNode;

import java.util.List;

/**
 * 文件名 AdminUserVo.java
 * 
 * @author 1
 * @date 2019-06-05 10:16:11
 */
public class AdminUserVo extends AdminUser {
    /**
     * 默认构造函数
     *
     * @author 1
     * @date 2019-06-05 10:16:11
     */
    public AdminUserVo() {

    }

    private List<TreeNode> treeNodeList;

    public List<TreeNode> getTreeNodeList() {
        return treeNodeList;
    }

    public void setTreeNodeList(List<TreeNode> treeNodeList) {
        this.treeNodeList = treeNodeList;
    }
}

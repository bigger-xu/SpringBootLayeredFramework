package com.cto.freemarker.entity.dto;


import com.cto.freemarker.entity.AdminUser;
import com.cto.freemarker.entity.common.TreeNode;
import lombok.Data;

import java.util.List;

/**
 * @author Bigger-Xu
 * @version v1.0.1
 * @date 2020/9/20 14:36
 */
@Data
public class AdminUserDto extends AdminUser {
    private List<TreeNode> treeNodeList;
}

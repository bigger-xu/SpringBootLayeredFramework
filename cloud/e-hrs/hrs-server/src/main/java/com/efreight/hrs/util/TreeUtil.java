package com.efreight.hrs.util;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollectionUtil;
import com.efreight.hrs.entity.HrsOrgDept;
import com.efreight.hrs.entity.HrsPermission;
import com.efreight.hrs.model.web.vo.MenuTreeVO;
import com.efreight.hrs.model.web.vo.OrgDeptTreeVO;
import com.efreight.hrs.model.web.vo.TreeVO;

/**
 * @author 马玉龙
 * @since 2023/8/22
 */
public class TreeUtil {


    public static List<TreeVO> streamToTree(List<? extends TreeVO> treeList, Long parentId) {
        return treeList.stream()
                // 过滤父节点
                .filter(parent -> parent.getParentId().equals(parentId))
                // 把父节点children递归赋值成为子节点
                .peek(child -> child.setChildren(streamToTree(treeList, child.getId()))).collect(Collectors.toList());
    }

    public static void nodeProcess(List<TreeVO> treeList, BiConsumer<Long, TreeVO> todo) {
        treeList.forEach(treeVO -> {
            todo.accept(treeVO.getId(), treeVO);
            if (CollectionUtil.isNotEmpty(treeVO.getChildren())) {
                nodeProcess(treeVO.getChildren(), todo);
            }
        });
    }

    public static List<TreeVO> getMenuTreeVOS(List<HrsPermission> userMenuPermission) {
        List<MenuTreeVO> meta = userMenuPermission.stream().map(permission -> {
            MenuTreeVO menuTreeVO = new MenuTreeVO();
            menuTreeVO.setCode(permission.getPermissionCode());
            menuTreeVO.setSort(permission.getSort());
            menuTreeVO.setIcon(permission.getIcon());
            menuTreeVO.setPath(permission.getPath());
            menuTreeVO.setName(permission.getPermissionName());
            menuTreeVO.setUrl(permission.getUrl());
            menuTreeVO.setId(permission.getId());
            menuTreeVO.setParentId(permission.getParentId());
            menuTreeVO.setPermission(permission.getPermission());
            menuTreeVO.setPageModule(permission.getPageModule());
            menuTreeVO.setPageCode(permission.getPageCode());
            menuTreeVO.setPageName(permission.getPageName());
            menuTreeVO.setPermissionType(permission.getPermissionType());
            return menuTreeVO;
        }).collect(Collectors.toList());
        return TreeUtil.streamToTree(meta, -1L);
    }

    public static List<TreeVO> getOrgDeptTreeVO(List<HrsOrgDept> hrsOrgDeptList) {
        List<OrgDeptTreeVO> meta = hrsOrgDeptList.stream().map(hrsOrgDept -> {
            OrgDeptTreeVO record = new OrgDeptTreeVO();
            record.setName(hrsOrgDept.getDeptName());
            record.setDeptName(hrsOrgDept.getDeptName());
            record.setId(hrsOrgDept.getId());
            record.setParentId(hrsOrgDept.getParentId());
            return record;
        }).collect(Collectors.toList());
        return TreeUtil.streamToTree(meta, -1L);
    }

}

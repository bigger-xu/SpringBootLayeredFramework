package com.efreight.hrs.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.efreight.hrs.entity.HrsPermission;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * HRS 权限管理 Mapper 接口
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
public interface HrsPermissionMapper extends BaseMapper<HrsPermission> {

    List<HrsPermission> getUserPermission(@Param("userId") Long userId, @Param("orgId") Long orgId, @Param("permissionType") String permissionType, @Param("isAdmin") String isAdmin, @Param("parentModule") String parentModule);

}

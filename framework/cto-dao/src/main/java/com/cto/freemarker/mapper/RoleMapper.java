package com.cto.freemarker.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cto.freemarker.entity.Role;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author Bigger-Xu
 * @since 2020-09-19
 */
public interface RoleMapper extends BaseMapper<Role> {
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

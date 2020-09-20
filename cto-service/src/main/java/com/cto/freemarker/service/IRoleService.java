package com.cto.freemarker.service;

import com.cto.freemarker.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author Bigger-Xu
 * @since 2020-09-19
 */
public interface IRoleService extends IService<Role> {

    /**
     * 根据用户ID获取菜单列表
     * @param id
     * @return
     */
    List<Role> selectListByUserId(Long id);
}

package com.cto.freemarker.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cto.freemarker.entity.Role;

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

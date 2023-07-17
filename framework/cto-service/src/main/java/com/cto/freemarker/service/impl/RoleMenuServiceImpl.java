package com.cto.freemarker.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cto.freemarker.entity.RoleMenu;
import com.cto.freemarker.mapper.RoleMenuMapper;
import com.cto.freemarker.service.IRoleMenuService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单角色对应关系 服务实现类
 * </p>
 *
 * @author Bigger-Xu
 * @since 2020-09-19
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

}

package com.cto.freemarker.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cto.freemarker.entity.Role;
import com.cto.freemarker.mapper.RoleMapper;
import com.cto.freemarker.service.IRoleService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Bigger-Xu
 * @since 2020-09-19
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    RoleMapper roleMapper;
    @Override
    public List<Role> selectListByUserId(Long id) {
        return roleMapper.selectListByUserId(id);
    }
}

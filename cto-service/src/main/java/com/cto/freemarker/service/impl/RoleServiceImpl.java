package com.cto.freemarker.service.impl;

import com.cto.freemarker.entity.Role;
import com.cto.freemarker.mapper.RoleMapper;
import com.cto.freemarker.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Role> selectListByUserId(Long id) {
        return null;
    }
}

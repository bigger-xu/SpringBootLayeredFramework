package com.cto.freemarker.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cto.freemarker.entity.RoleUser;
import com.cto.freemarker.mapper.RoleUserMapper;
import com.cto.freemarker.service.IRoleUserService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色对应关系 服务实现类
 * </p>
 *
 * @author Bigger-Xu
 * @since 2020-09-19
 */
@Service
public class RoleUserServiceImpl extends ServiceImpl<RoleUserMapper, RoleUser> implements IRoleUserService {

}

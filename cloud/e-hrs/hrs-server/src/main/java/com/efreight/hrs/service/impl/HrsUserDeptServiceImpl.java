package com.efreight.hrs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.efreight.common.global.login.LoginDeptVO;
import com.efreight.hrs.dao.HrsUserDeptMapper;
import com.efreight.hrs.entity.HrsUserDept;
import com.efreight.hrs.service.IHrsUserDeptService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * HRS 用户部门表 服务实现类
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
@Service
public class HrsUserDeptServiceImpl extends ServiceImpl<HrsUserDeptMapper, HrsUserDept> implements IHrsUserDeptService {

    @Override
    public LoginDeptVO getUserDeptInfo(Long userId, Long orgId) {
        return baseMapper.getUserDeptInfo(userId, orgId);
    }
}

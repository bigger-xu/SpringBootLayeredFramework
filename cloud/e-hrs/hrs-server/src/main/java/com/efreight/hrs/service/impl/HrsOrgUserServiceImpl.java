package com.efreight.hrs.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.efreight.hrs.constants.HrsConstants;
import com.efreight.hrs.dao.HrsOrgUserMapper;
import com.efreight.hrs.entity.HrsOrgUser;
import com.efreight.hrs.service.IHrsOrgUserService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * HRS 签约公司：用户关联表 服务实现类
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
@Service
public class HrsOrgUserServiceImpl extends ServiceImpl<HrsOrgUserMapper, HrsOrgUser> implements IHrsOrgUserService {
    @Override
    public HrsOrgUser queryOrgIdByUserId(Long userId) {
        return baseMapper.selectOne(Wrappers.<HrsOrgUser>lambdaQuery().eq(HrsOrgUser::getUserId, userId).eq(HrsOrgUser::getIsDefaultOrg, HrsConstants.Y).eq(HrsOrgUser::getUsed, HrsConstants.Y));
    }

    @Override
    public HrsOrgUser queryOrgIdByUidOid(Long userId, Long orgId) {
        return baseMapper.selectOne(Wrappers.<HrsOrgUser>lambdaQuery().eq(HrsOrgUser::getUserId, userId).eq(HrsOrgUser::getOrgId, orgId).eq(HrsOrgUser::getUsed, HrsConstants.Y));
    }
}

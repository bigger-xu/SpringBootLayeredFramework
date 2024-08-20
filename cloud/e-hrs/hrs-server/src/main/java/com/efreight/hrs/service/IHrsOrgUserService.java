package com.efreight.hrs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.efreight.hrs.entity.HrsOrgUser;

/**
 * <p>
 * HRS 签约公司：用户关联表 服务类
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
public interface IHrsOrgUserService extends IService<HrsOrgUser> {

    HrsOrgUser queryOrgIdByUserId(Long userId);

    HrsOrgUser queryOrgIdByUidOid(Long userId, Long orgId);

}

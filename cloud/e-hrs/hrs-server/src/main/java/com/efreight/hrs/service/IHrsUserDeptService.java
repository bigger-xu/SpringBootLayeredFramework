package com.efreight.hrs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.efreight.common.global.login.LoginDeptVO;
import com.efreight.hrs.entity.HrsUserDept;

/**
 * <p>
 * HRS 用户部门表 服务类
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
public interface IHrsUserDeptService extends IService<HrsUserDept> {

    LoginDeptVO getUserDeptInfo(Long userId, Long orgId);

}

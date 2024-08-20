package com.efreight.hrs.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.efreight.common.global.login.LoginDeptVO;
import com.efreight.hrs.entity.HrsUserDept;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * HRS 用户部门表 Mapper 接口
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
public interface HrsUserDeptMapper extends BaseMapper<HrsUserDept> {
    LoginDeptVO getUserDeptInfo(@Param("userId") Long userId, @Param("orgId") Long orgId);

}

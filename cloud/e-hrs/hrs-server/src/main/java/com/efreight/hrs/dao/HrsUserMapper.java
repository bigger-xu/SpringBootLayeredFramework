package com.efreight.hrs.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.efreight.hrs.entity.HrsUser;
import com.efreight.hrs.model.web.req.HrsGlobalUserQueryReq;
import com.efreight.hrs.model.web.req.HrsManagerUserQueryReq;
import com.efreight.hrs.model.web.vo.HrsGlobalUserPageVO;
import com.efreight.hrs.model.web.vo.HrsManagerUserPageVO;
import com.efreight.hrs.model.web.vo.OrgDeptVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * HRS 用户表 Mapper 接口
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
public interface HrsUserMapper extends BaseMapper<HrsUser> {

    IPage<HrsGlobalUserPageVO> getGlobalUserPage(Page<HrsGlobalUserPageVO> page, @Param("query") HrsGlobalUserQueryReq query);
    List<OrgDeptVO> getGlobalUserOrgDeptList(@Param("userId") Long userId);
    IPage<HrsManagerUserPageVO> getManagerUserPage(Page<HrsManagerUserPageVO> page, @Param("query") HrsManagerUserQueryReq query);

}

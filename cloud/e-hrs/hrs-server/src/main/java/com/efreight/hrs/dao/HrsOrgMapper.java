package com.efreight.hrs.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.efreight.common.global.login.LoginOrgVO;
import com.efreight.hrs.entity.HrsOrg;
import com.efreight.hrs.model.web.req.HrsOrgPageReq;
import com.efreight.hrs.model.web.vo.HrsOrgVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * HRS 签约公司 Mapper 接口
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
public interface HrsOrgMapper extends BaseMapper<HrsOrg> {
    IPage<HrsOrgVO> getOrgPage(Page<HrsOrgVO> page, @Param("query") HrsOrgPageReq query);
    LoginOrgVO getOrgAndOrgConfig(@Param("orgId") Long orgId);
}


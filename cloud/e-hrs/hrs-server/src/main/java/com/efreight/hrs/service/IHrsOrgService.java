package com.efreight.hrs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.efreight.common.global.login.LoginOrgVO;
import com.efreight.hrs.entity.HrsOrg;
import com.efreight.hrs.model.web.req.HrsOrgPageReq;
import com.efreight.hrs.model.web.req.HrsOrgReq;
import com.efreight.hrs.model.web.vo.HrsOrgVO;

/**
 * <p>
 * HRS 签约公司 服务类
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
public interface IHrsOrgService extends IService<HrsOrg> {

    public void addOrModifyOrg(HrsOrgReq req);

    IPage<HrsOrgVO> getPage(HrsOrgPageReq req);
    /**
     * 获取公司及公司的配置信息
     *
     * @param orgId 签约公司ID
     * @return HrsOrg
     * @author ZhangYongWei
     * @since 2023/11/29
     */
    LoginOrgVO getOrgAndOrgConfig(Long orgId);

}

package com.efreight.hrs.service.impl;

import java.util.Optional;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.efreight.common.global.login.LoginOrgVO;
import com.efreight.hrs.dao.HrsOrgMapper;
import com.efreight.hrs.entity.HrsAreaOrg;
import com.efreight.hrs.entity.HrsOrg;
import com.efreight.hrs.entity.HrsOrgAeOrderConfig;
import com.efreight.hrs.entity.HrsOrgAiOrderConfig;
import com.efreight.hrs.model.web.req.HrsOrgAeOrderConfigReq;
import com.efreight.hrs.model.web.req.HrsOrgAiOrderConfigReq;
import com.efreight.hrs.model.web.req.HrsOrgPageReq;
import com.efreight.hrs.model.web.req.HrsOrgReq;
import com.efreight.hrs.model.web.vo.HrsOrgVO;
import com.efreight.hrs.service.IHrsAreaOrgService;
import com.efreight.hrs.service.IHrsOrgAeOrderConfigService;
import com.efreight.hrs.service.IHrsOrgAiOrderConfigService;
import com.efreight.hrs.service.IHrsOrgService;
import jakarta.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * <p>
 * HRS 签约公司 服务实现类
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
@Service
public class HrsOrgServiceImpl extends ServiceImpl<HrsOrgMapper, HrsOrg> implements IHrsOrgService {

    @Resource
    private IHrsOrgAeOrderConfigService hrsOrgAeOrderConfigService;
    @Resource
    private IHrsOrgAiOrderConfigService hrsOrgAiOrderConfigService;
    @Resource
    private IHrsAreaOrgService hrsAreaOrgService;

    @Override
    public void addOrModifyOrg(HrsOrgReq req) {
        HrsOrg org;
        if(req.getId()!=null){
            //编辑
            org = baseMapper.selectById(req.getId());
        }else{
            //新增
            org = new HrsOrg();
        }
        BeanUtil.copyProperties(req, org);
        saveOrUpdate(org);
        HrsOrgAeOrderConfig aeOrderConfig = hrsOrgAeOrderConfigService.getById(req.getAeOrderConfigReq().getId());
        HrsOrgAeOrderConfigReq aeConfig = req.getAeOrderConfigReq();
        aeOrderConfig = Optional.ofNullable(aeOrderConfig).orElseGet(HrsOrgAeOrderConfig::new);
        BeanUtil.copyProperties(aeConfig, aeOrderConfig);
        aeOrderConfig.setOrgId(org.getId());
        hrsOrgAeOrderConfigService.saveOrUpdate(aeOrderConfig);
        HrsOrgAiOrderConfig aiOrderConfig = hrsOrgAiOrderConfigService.getById(req.getAiOrderConfigReq().getId());
        aiOrderConfig = Optional.ofNullable(aiOrderConfig).orElseGet(HrsOrgAiOrderConfig::new);
        HrsOrgAiOrderConfigReq aiConfig = req.getAiOrderConfigReq();
        BeanUtil.copyProperties(aiConfig, aiOrderConfig);
        aiOrderConfig.setOrgId(org.getId());
        hrsOrgAiOrderConfigService.saveOrUpdate(aiOrderConfig);
        HrsAreaOrg hrsAreaOrg = hrsAreaOrgService.getBaseMapper().selectOne(Wrappers.<HrsAreaOrg>lambdaQuery().eq(HrsAreaOrg::getOrgId, req.getId()));
        hrsAreaOrg = Optional.ofNullable(hrsAreaOrg).orElseGet(HrsAreaOrg::new);
        hrsAreaOrg.setAreaId(req.getAreaId());
        hrsAreaOrg.setOrgId(org.getId());
        hrsAreaOrgService.saveOrUpdate(hrsAreaOrg);
    }

    @Override
    public IPage<HrsOrgVO> getPage(HrsOrgPageReq req) {
        return baseMapper.getOrgPage(new Page<>(req.getCurrent(), req.getSize()), req);
    }
    @Override
    public LoginOrgVO getOrgAndOrgConfig(Long orgId) {
        return this.baseMapper.getOrgAndOrgConfig(orgId);
    }
}

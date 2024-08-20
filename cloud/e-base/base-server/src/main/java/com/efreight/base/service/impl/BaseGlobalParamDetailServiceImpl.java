package com.efreight.base.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.efreight.base.dao.BaseGlobalParamDetailMapper;
import com.efreight.base.entity.BaseGlobalParamDetail;
import com.efreight.base.model.web.req.BaseGlobalParamDetailReq;
import com.efreight.base.model.web.vo.BaseGlobalParamDetailVO;
import com.efreight.base.model.web.vo.GlobalParamDetailToValueVO;
import com.efreight.base.service.IBaseGlobalParamDetailService;
import com.efreight.common.constants.BaseResultCode;
import com.efreight.common.enums.OrderTypeEnum;
import com.efreight.common.utils.BizExceptionCheckUtils;
import com.efreight.common.utils.ObjectConvertUtils;
import com.efreight.common.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 全局参数详情表 服务实现类
 * </p>
 *
 * @author Zhang Yongwei
 * @since 2024-08-13
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BaseGlobalParamDetailServiceImpl extends ServiceImpl<BaseGlobalParamDetailMapper, BaseGlobalParamDetail> implements IBaseGlobalParamDetailService {
    
    @Override
    public void saveParamDetail(BaseGlobalParamDetailReq req) {
        req.setParamName(StringUtil.DEFAULT_TAGS_START + req.getParamName() + StringUtil.DEFAULT_TAGS_END);
        //查询是否重名
        BaseGlobalParamDetail old = this.getOne(Wrappers.<BaseGlobalParamDetail>lambdaQuery()
                                                        .eq(BaseGlobalParamDetail::getParamName, req.getParamName())
                                                        .eq(BaseGlobalParamDetail::getOrderType, req.getOrderType()), false);
        BizExceptionCheckUtils.isTrue(old != null, BaseResultCode.BASE_GLOBAL_PARAMS_IS_EXIST);
        BaseGlobalParamDetail entity = new BaseGlobalParamDetail();
        ObjectConvertUtils.beanCopyProperties(req, entity);
        this.save(entity);
    }
    
    @Override
    public void updateParamDetail(BaseGlobalParamDetailReq req) {
        req.setParamName(StringUtil.DEFAULT_TAGS_START + req.getParamName() + StringUtil.DEFAULT_TAGS_END);
        //查询是否重名
        BaseGlobalParamDetail old = this.getOne(Wrappers.<BaseGlobalParamDetail>lambdaQuery().ne(BaseGlobalParamDetail::getId, req.getId())
                                                        .eq(BaseGlobalParamDetail::getParamName, req.getParamName())
                                                        .eq(BaseGlobalParamDetail::getOrderType, req.getOrderType()), false);
        BizExceptionCheckUtils.isTrue(old != null, BaseResultCode.BASE_GLOBAL_PARAMS_IS_EXIST);
        BaseGlobalParamDetail entity = new BaseGlobalParamDetail();
        ObjectConvertUtils.beanCopyProperties(req, entity);
        this.updateById(entity);
    }
    
    @Override
    public List<BaseGlobalParamDetailVO> customList(Long categoryId, String keywords) {
        return this.baseMapper.customList(categoryId, keywords);
    }
    
    @Override
    public List<GlobalParamDetailToValueVO> getFieldNameJoinParamDetail(List<String> tagsList, OrderTypeEnum orderType) {
        return this.baseMapper.getFieldNameJoinParamDetail(tagsList, orderType);
    }
}

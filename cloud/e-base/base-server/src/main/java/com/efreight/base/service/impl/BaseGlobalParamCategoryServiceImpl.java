package com.efreight.base.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.efreight.base.dao.BaseGlobalParamCategoryMapper;
import com.efreight.base.entity.BaseGlobalParamCategory;
import com.efreight.base.model.web.req.BaseGlobalParamCategoryReq;
import com.efreight.base.service.IBaseGlobalParamCategoryService;
import com.efreight.common.constants.BaseResultCode;
import com.efreight.common.utils.BizExceptionCheckUtils;
import com.efreight.common.utils.ObjectConvertUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 全局参数分类表 服务实现类
 * </p>
 *
 * @author Zhang Yongwei
 * @since 2024-08-13
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BaseGlobalParamCategoryServiceImpl extends ServiceImpl<BaseGlobalParamCategoryMapper, BaseGlobalParamCategory> implements IBaseGlobalParamCategoryService {
    
    @Override
    public void saveCategory(BaseGlobalParamCategoryReq req) {
        //查询是否重名
        BaseGlobalParamCategory old = this.getOne(Wrappers.<BaseGlobalParamCategory>lambdaQuery()
                                                          .eq(BaseGlobalParamCategory::getCategoryName, req.getCategoryName())
                                                          .eq(BaseGlobalParamCategory::getOrderType, req.getOrderType()), false);
        BizExceptionCheckUtils.isTrue(old != null, BaseResultCode.BASE_GLOBAL_CATEGORY_IS_EXIST);
        BaseGlobalParamCategory entity = new BaseGlobalParamCategory();
        ObjectConvertUtils.beanCopyProperties(req, entity);
        this.save(entity);
    }
    
    @Override
    public void updateCategory(BaseGlobalParamCategoryReq req) {
        //查询是否重名
        BaseGlobalParamCategory old = this.getOne(Wrappers.<BaseGlobalParamCategory>lambdaQuery()
                                                          .ne(BaseGlobalParamCategory::getId, req.getId())
                                                          .eq(BaseGlobalParamCategory::getCategoryName, req.getCategoryName())
                                                          .eq(BaseGlobalParamCategory::getOrderType, req.getOrderType()), false);
        BizExceptionCheckUtils.isTrue(old != null, BaseResultCode.BASE_GLOBAL_CATEGORY_IS_EXIST);
        BaseGlobalParamCategory entity = new BaseGlobalParamCategory();
        ObjectConvertUtils.beanCopyProperties(req, entity);
        this.updateById(entity);
    }
}

package com.efreight.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.efreight.base.entity.BaseGlobalParamCategory;
import com.efreight.base.model.web.req.BaseGlobalParamCategoryReq;

/**
 * <p>
 * 全局参数分类表 服务类
 * </p>
 *
 * @author Zhang Yongwei
 * @since 2024-08-13
 */
public interface IBaseGlobalParamCategoryService extends IService<BaseGlobalParamCategory> {
    /**
     * 添加
     * @param req  请求参数
     * @author 张永伟
     * @since 2023/8/29
     */
    void saveCategory(BaseGlobalParamCategoryReq req);
    
    /**
     * 修改
     * @param req  请求参数
     * @author 张永伟
     * @since 2023/8/29
     */
    void updateCategory(BaseGlobalParamCategoryReq req);
}

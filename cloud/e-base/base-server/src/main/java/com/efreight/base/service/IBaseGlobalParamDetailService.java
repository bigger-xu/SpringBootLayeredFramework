package com.efreight.base.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.efreight.base.entity.BaseGlobalParamDetail;
import com.efreight.base.model.web.req.BaseGlobalParamDetailReq;
import com.efreight.base.model.web.vo.BaseGlobalParamDetailVO;
import com.efreight.base.model.web.vo.GlobalParamDetailToValueVO;
import com.efreight.common.enums.OrderTypeEnum;

/**
 * <p>
 * 全局参数详情表 服务类
 * </p>
 *
 * @author Zhang Yongwei
 * @since 2024-08-13
 */
public interface IBaseGlobalParamDetailService extends IService<BaseGlobalParamDetail> {
    /**
     * 新增
     *
     * @param req   请求参数
     * @param orgId 签约公司ID
     * @since 2023/8/29
     */
    void saveParamDetail(BaseGlobalParamDetailReq req);
    
    /**
     * 修改
     *
     * @param req   请求参数
     * @param orgId 签约公司ID
     * @since 2023/8/29
     */
    void updateParamDetail(BaseGlobalParamDetailReq req);
    
    /**
     * 根据关键词和分类ID查询列表
     *
     * @param categoryId 分类ID
     * @param keywords   关键词
     * @return List<BaseGlobalParamDetailVO>
     * @since 2023/8/29
     */
    List<BaseGlobalParamDetailVO> customList(Long categoryId, String keywords);
    
    /**
     * 获取自定义标签对应的字段
     *
     * @param tagsList  标签类别
     * @param orderType 订单类型
     * @return List<GlobalParamDetailToValueVO>
     * @since 2023/9/18
     */
    List<GlobalParamDetailToValueVO> getFieldNameJoinParamDetail(List<String> tagsList, OrderTypeEnum orderType);

}

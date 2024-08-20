package com.efreight.base.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.efreight.base.entity.BaseGlobalParamDetail;
import com.efreight.base.model.web.vo.BaseGlobalParamDetailVO;
import com.efreight.base.model.web.vo.GlobalParamDetailToValueVO;
import com.efreight.common.enums.OrderTypeEnum;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 全局参数详情表 Mapper 接口
 * </p>
 *
 * @author Zhang Yongwei
 * @since 2024-08-13
 */
public interface BaseGlobalParamDetailMapper extends BaseMapper<BaseGlobalParamDetail> {
    /**
     * 根据关键词和分类ID查询列表
     *
     * @param categoryId 分类ID
     * @param keywords   关键词
     * @return List<BaseGlobalParamDetailVO>
     * @since 2023/8/29
     */
    List<BaseGlobalParamDetailVO> customList(@Param("categoryId") Long categoryId, @Param("keywords") String keywords);
    
    /**
     * 获取自定义标签对应的字段
     *
     * @param tagsList 标签类别
     * @return List<GlobalParamDetailToValueVO>
     * @since 2023/9/18
     */
    List<GlobalParamDetailToValueVO> getFieldNameJoinParamDetail(@Param("tagsList") List<String> tagsList, @Param("orderType") OrderTypeEnum orderType);
    
    /**
     * 根据分类ID获取视图列表
     *
     * @param categoryId 全局参数分类ID
     * @return List<Map<String, String>>
     * @since 2023/9/22
     */
    List<Map<String, String>> getViewNameListByCategoryId(@Param("categoryId") Long categoryId);
}

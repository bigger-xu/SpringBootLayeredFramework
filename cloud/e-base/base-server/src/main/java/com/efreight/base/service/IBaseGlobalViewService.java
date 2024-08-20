package com.efreight.base.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.efreight.base.entity.BaseGlobalView;
import com.efreight.common.enums.OrderTypeEnum;

/**
 * <p>
 * 视图表 服务类
 * </p>
 *
 * @author Zhang Yongwei
 * @since 2024-08-13
 */
public interface IBaseGlobalViewService extends IService<BaseGlobalView> {
    
    /**
     * 获取指定的标签对应值单条
     *
     * @param tagsList 标签列表
     * @param params   参数信息
     * @param tagFlag  类型  true的时候返回field_Code  否则返回全局参数标签名
     * @return Map<String, Object> 返回每个标签的内容
     * @since 2023/9/18
     */
    Map<String, Object> getCustomTagsValueForObject(List<String> tagsList, List<String> params, Boolean tagFlag, OrderTypeEnum orderType);
    
    /**
     * 获取指定的标签对应值列表
     *
     * @param tagsList 标签列表
     * @param params   参数信息
     * @param tagFlag  类型  true的时候返回field_Code  否则返回全局参数标签名
     * @return List<Map < String, Object>>
     * @since 2023/9/19
     */
    List<Map<String, Object>> getCustomTagsValueForList(List<String> tagsList, List<String> params, Boolean tagFlag, OrderTypeEnum orderType);
    
    /**
     * 替换字符串中的全局参数标签
     *
     * @param sourceStr 源字符串
     * @param params    查询参数
     * @param tagFlag   类型  true的时候返回field_Code  否则返回全局参数标签名
     * @return String   返回替换后的字符串
     * @since 2023/9/19
     */
    String getCustomTagsValueToString(String sourceStr, List<String> params, Boolean tagFlag, OrderTypeEnum orderType);
    
    /**
     * 获取某个分类下所有标签对应值的列表
     *
     * @param globalCategoryId 全局参数分类ID
     * @param params           查询参数
     * @param tagFlag          类型  true的时候返回field_Code  否则返回全局参数标签名
     * @return List<Map < String, Object>>
     * @since 2023/9/19
     */
    List<Map<String, Object>> getCustomTagsValueListForCategoryId(Long globalCategoryId, List<String> params, Boolean tagFlag, OrderTypeEnum orderType);
    
    /**
     * 获取某个分类下所有标签对应值的对象
     *
     * @param globalCategoryId 全局参数分类ID
     * @param params           查询参数
     * @param tagFlag          类型  true的时候返回field_Code  否则返回全局参数标签名
     * @return Map<String, Object>
     * @since 2023/9/22
     */
    Map<String, Object> getCustomTagsValueObjectForCategoryId(Long globalCategoryId, List<String> params, Boolean tagFlag, OrderTypeEnum orderType);
}

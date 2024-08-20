package com.efreight.base.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.efreight.base.config.ViewsDruidDataSource;
import com.efreight.base.dao.BaseGlobalViewMapper;
import com.efreight.base.entity.BaseGlobalParamDetail;
import com.efreight.base.entity.BaseGlobalView;
import com.efreight.base.model.web.vo.GlobalParamDetailToValueVO;
import com.efreight.base.service.IBaseGlobalParamDetailService;
import com.efreight.base.service.IBaseGlobalViewService;
import com.efreight.common.constants.BaseResultCode;
import com.efreight.common.enums.OrderTypeEnum;
import com.efreight.common.exception.EfreightBizException;
import com.efreight.common.utils.BizExceptionCheckUtils;
import com.efreight.common.utils.StringUtil;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 视图表 服务实现类
 * </p>
 *
 * @author Zhang Yongwei
 * @since 2024-08-13
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BaseGlobalViewServiceImpl extends ServiceImpl<BaseGlobalViewMapper, BaseGlobalView> implements IBaseGlobalViewService {
    
    private static final String GLOBAL_REPLACE_PARAMS = "EF_REPLACE_PARAMS";
    
    private static final String GLOBAL_TAGS_REPLACE_PARAMS = "TAGS_REPLACE_PARAMS";
    
    private final IBaseGlobalParamDetailService iBaseGlobalParamDetailService;
    
    private final JdbcTemplate jdbcTemplate;
    
    @Resource
    private ViewsDruidDataSource viewsDruidDataSource;
    
    @Override
    public Map<String, Object> getCustomTagsValueForObject(List<String> tagsList, List<String> params, Boolean tagsFlag, OrderTypeEnum orderType) {
        Map<String, Object> resultMap = new HashMap<>();
        List<GlobalParamDetailToValueVO> detailList = validateParamsAndGetFieldNameDetail(tagsList, orderType);
        if (detailList == null || detailList.isEmpty()) {
            return resultMap;
        }
        Map<Long, List<GlobalParamDetailToValueVO>> groupMap = detailList.stream().collect(Collectors.groupingBy(GlobalParamDetailToValueVO::getViewId));
        for (Entry<Long, List<GlobalParamDetailToValueVO>> entry : groupMap.entrySet()) {
            BaseGlobalView globalView = this.getById(entry.getKey());
            Optional<String> first = params.stream().filter(s -> s.startsWith(globalView.getViewName())).collect(Collectors.toList()).stream().findFirst();
            if (first.isEmpty()) {
                continue;
            }
            Map<String, Object> searchMap = createConnectAndDoSearchObject(globalView.getDatabaseName(), globalView.getConnectUrl(),
                                                                           globalView.getConnectUser(), globalView.getConnectPassword(),
                                                                           globalView.getViewName(), globalView.getSqlDetail(),
                                                                           first.get().split("&")[1]);
            for (GlobalParamDetailToValueVO valueDTO : entry.getValue()) {
                if (tagsFlag) {
                    resultMap.put(valueDTO.getFieldCodeHump(), searchMap.get(valueDTO.getFieldCode()));
                } else {
                    resultMap.put(valueDTO.getParamName(), searchMap.get(valueDTO.getFieldCode()));
                }
            }
        }
        return resultMap;
    }
    
    @Override
    public List<Map<String, Object>> getCustomTagsValueForList(List<String> tagsList, List<String> params, Boolean tagsFlag, OrderTypeEnum orderType) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<GlobalParamDetailToValueVO> detailList = validateParamsAndGetFieldNameDetail(tagsList, orderType);
        if (detailList == null || detailList.isEmpty()) {
            return resultList;
        }
        Map<Long, List<GlobalParamDetailToValueVO>> groupMap = detailList.stream().collect(Collectors.groupingBy(GlobalParamDetailToValueVO::getViewId));
        //如果条数大于1，代表选项包含多种视图字段，无法聚合list,返回第一条
        if (groupMap.entrySet().size() > 1) {
            Map<String, Object> valueForObject = this.getCustomTagsValueForObject(tagsList, params, tagsFlag, orderType);
            resultList.add(valueForObject);
            return resultList;
        }
        for (Entry<Long, List<GlobalParamDetailToValueVO>> entry : groupMap.entrySet()) {
            BaseGlobalView globalView = this.getById(entry.getKey());
            Optional<String> first = params.stream().filter(s -> s.startsWith(globalView.getViewName())).collect(Collectors.toList()).stream().findFirst();
            if (first.isEmpty()) {
                continue;
            }
            List<Map<String, Object>> searchList = createConnectAndDoSearchList(globalView.getDatabaseName(), globalView.getConnectUrl(),
                                                                                globalView.getConnectUser(), globalView.getConnectPassword(),
                                                                                globalView.getViewName(), globalView.getSqlDetail(),
                                                                                first.get().split("&")[1]);
            if (!searchList.isEmpty()) {
                for (Map<String, Object> map : searchList) {
                    Map<String, Object> tempMap = new HashMap<>();
                    for (GlobalParamDetailToValueVO valueDTO : entry.getValue()) {
                        if (tagsFlag) {
                            tempMap.put(valueDTO.getFieldCodeHump(), map.get(valueDTO.getFieldCode()));
                        } else {
                            tempMap.put(valueDTO.getParamName(), map.get(valueDTO.getFieldCode()));
                        }
                    }
                    resultList.add(tempMap);
                }
            }
        }
        return resultList;
    }
    
    @Override
    public String getCustomTagsValueToString(String sourceStr, List<String> params, Boolean tagsFlag, OrderTypeEnum orderType) {
        if (StringUtils.isNotBlank(sourceStr)) {
            List<String> stringList = StringUtil.getStrContainData(sourceStr);
            Map<String, Object> valueForObject = this.getCustomTagsValueForObject(stringList, params, tagsFlag, orderType);
            if (CollectionUtils.isNotEmpty(valueForObject)) {
                for (Entry<String, Object> entry : valueForObject.entrySet()) {
                    if (entry.getValue() != null) {
                        Object value = entry.getValue();
                        if (value instanceof Timestamp) {
                            value = String.valueOf(value).substring(0, String.valueOf(value).length() - 2);
                        }
                        sourceStr = sourceStr.replace(entry.getKey(), String.valueOf(value));
                    } else {
                        sourceStr = sourceStr.replace(entry.getKey(), "");
                    }
                }
            }
        }
        return sourceStr;
    }
    
    @Override
    public List<Map<String, Object>> getCustomTagsValueListForCategoryId(Long globalCategoryId, List<String> params, Boolean tagsFlag,
            OrderTypeEnum orderType) {
        List<BaseGlobalParamDetail> paramDetailList = iBaseGlobalParamDetailService.list(
                Wrappers.<BaseGlobalParamDetail>lambdaQuery().eq(BaseGlobalParamDetail::getCategoryId, globalCategoryId));
        if (!BizExceptionCheckUtils.isNull(paramDetailList)) {
            List<String> stringList = paramDetailList.stream().map(BaseGlobalParamDetail::getParamName).collect(Collectors.toList());
            return this.getCustomTagsValueForList(stringList, params, tagsFlag, orderType);
        }
        return null;
    }
    
    @Override
    public Map<String, Object> getCustomTagsValueObjectForCategoryId(Long globalCategoryId, List<String> params, Boolean tagsFlag,
            OrderTypeEnum orderType) {
        List<BaseGlobalParamDetail> paramDetailList = iBaseGlobalParamDetailService.list(
                Wrappers.<BaseGlobalParamDetail>lambdaQuery().eq(BaseGlobalParamDetail::getCategoryId, globalCategoryId));
        if (!BizExceptionCheckUtils.isNull(paramDetailList)) {
            List<String> stringList = paramDetailList.stream().map(BaseGlobalParamDetail::getParamName).collect(Collectors.toList());
            return this.getCustomTagsValueForObject(stringList, params, tagsFlag, orderType);
        }
        return null;
    }
    
    
    /**
     * 校验参数
     *
     * @param tagsList 参数列表
     * @return List<GlobalParamDetailToValueVO>
     * @since 2023/9/19
     */
    private List<GlobalParamDetailToValueVO> validateParamsAndGetFieldNameDetail(List<String> tagsList, OrderTypeEnum orderType) {
        if (BizExceptionCheckUtils.isNull(tagsList)) {
            return null;
        }
        return iBaseGlobalParamDetailService.getFieldNameJoinParamDetail(tagsList, orderType);
    }
    
    /**
     * 创建连接池并进行单条查询
     *
     * @param connectUrl      连接池地址
     * @param connectUser     用户名
     * @param connectPassword 密码
     * @param params          参数
     * @return Map<String, Object>
     * @since 2023/9/18
     */
    private Map<String, Object> createConnectAndDoSearchObject(String database, String connectUrl, String connectUser, String connectPassword,
            String viewName, String sqlDetail, String params) {
        log.info("创建连接池并进行单条查询 --> 方法名:【createConnectAndDoSearchObject】--> 参数: params = {}", params);
        String formatSql = getFormatSql(viewName, sqlDetail, params);
        //连接池对象
        try {
            //获取连接
            DruidDataSource druidDataSource = viewsDruidDataSource.getDruidDataSource(database, connectUrl, connectUser, connectPassword);
            jdbcTemplate.setDataSource(druidDataSource);
            List<Map<String, Object>> listMap = jdbcTemplate.queryForList(formatSql);
            if (CollectionUtils.isNotEmpty(listMap)) {
                return listMap.get(0);
            }
            return new HashMap<>();
        } catch (Exception e) {
            throw new EfreightBizException(BaseResultCode.BASE_GLOBAL_MATE_DATA_ERROR);
        }
    }
    
    /**
     * 创建连接池并进行列表查询
     *
     * @param connectUrl      连接池地址
     * @param connectUser     用户名
     * @param connectPassword 密码
     * @param params          参数
     * @return Map<String, Object>
     * @since 2023/9/18
     */
    private List<Map<String, Object>> createConnectAndDoSearchList(String database, String connectUrl, String connectUser, String connectPassword,
            String viewName, String sqlDetail, String params) {
        log.info("创建连接池并进行列表查询 --> 方法名:【createConnectAndDoSearchList】--> 参数: params = {}", params);
        String formatSql = getFormatSql(viewName, sqlDetail, params);
        try {
            DruidDataSource druidDataSource = viewsDruidDataSource.getDruidDataSource(database, connectUrl, connectUser, connectPassword);
            //获取连接
            jdbcTemplate.setDataSource(druidDataSource);
            return jdbcTemplate.queryForList(formatSql);
        } catch (Exception e) {
            throw new EfreightBizException(BaseResultCode.BASE_GLOBAL_MATE_DATA_ERROR);
        }
    }
    
    /**
     * 创建可用的SQL语句
     *
     * @param viewName  视图名
     * @param sqlDetail 执行SQL语句
     * @param params    where条件
     * @return String
     * @since 2024/4/16
     */
    private static String getFormatSql(String viewName, String sqlDetail, String params) {
        String sql;
        String formatSql;
        if (StringUtils.isNotEmpty(sqlDetail)) {
            formatSql = sqlDetail.replace(GLOBAL_REPLACE_PARAMS, params);
        } else {
            sql = "SELECT * FROM %s WHERE %s";
            formatSql = String.format(sql, viewName, params);
        }
        log.info("JDBC链接SQL查询，SQL语句 --> = {}", formatSql);
        return formatSql;
    }
    
}

package com.efreight.base.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.efreight.base.entity.BaseGlobalParamCategory;
import com.efreight.base.model.web.req.BaseGlobalParamCategoryReq;
import com.efreight.base.model.web.vo.BaseGlobalParamCategoryVO;
import com.efreight.base.service.IBaseGlobalParamCategoryService;
import com.efreight.common.constants.BaseResultCode;
import com.efreight.common.enums.OrderTypeEnum;
import com.efreight.common.response.MessageInfo;
import com.efreight.common.utils.BizExceptionCheckUtils;
import com.efreight.common.utils.ObjectConvertUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 全局参数分类表 前端控制器
 * </p>
 *
 * @author Zhang Yongwei
 * @since 2024-08-13
 */
@Tag(name =  "全局参数分类" )
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/baseGlobalParamCategory")
public class BaseGlobalParamCategoryController {
    
    private final IBaseGlobalParamCategoryService iBaseGlobalParamCategoryService;
    
    @GetMapping("/listByOrderType")
    @Operation(summary = "根据订单类型获取分类列表")
    @Parameters({
            @Parameter(name = "orderType", description = "订单类型", required = true, in= ParameterIn.QUERY),
            @Parameter(name = "categoryName", description = "分类名称", required = true, in= ParameterIn.QUERY)
    })
    public MessageInfo<List<BaseGlobalParamCategoryVO>> listByOrderType(OrderTypeEnum orderType, String categoryName) {
        BizExceptionCheckUtils.isTrue(orderType == null, BaseResultCode.BASE_ORDER_TYPE_IS_NULL);
        List<BaseGlobalParamCategory> list =
                iBaseGlobalParamCategoryService.list(Wrappers.<BaseGlobalParamCategory>lambdaQuery()
                                                             .eq(BaseGlobalParamCategory::getOrderType, orderType)
                                                             .eq(StringUtils.isNotBlank(categoryName), BaseGlobalParamCategory::getCategoryName, categoryName)
                                                             .orderByAsc(BaseGlobalParamCategory::getSort));
        List<BaseGlobalParamCategoryVO> voList = ObjectConvertUtils.listCopyProperties(list, BaseGlobalParamCategoryVO.class);
        if (CollectionUtils.isNotEmpty(voList)) {
            for (BaseGlobalParamCategoryVO vo : voList) {
                if (StringUtils.isNotBlank(vo.getChildTable())) {
                    String[] split = vo.getChildTable().split(",");
                    List<String> idList = Arrays.asList(split);
                    List<BaseGlobalParamCategory> collect = list.stream().filter(o -> idList.contains(String.valueOf(o.getId()))).collect(Collectors.toList());
                    if (CollectionUtils.isNotEmpty(collect)) {
                        vo.setChild(ObjectConvertUtils.listCopyProperties(collect, BaseGlobalParamCategoryVO.class));
                    }
                }
            }
        }
        return MessageInfo.ok(voList);
    }
    
    @PostMapping("/save")
    @Operation(summary = "新增")
    public void save(@RequestBody BaseGlobalParamCategoryReq req) {
        iBaseGlobalParamCategoryService.saveCategory(req);
    }
    
    @PostMapping("/update")
    @Operation(summary = "修改")
    public void update(@RequestBody BaseGlobalParamCategoryReq req) {
        iBaseGlobalParamCategoryService.updateCategory(req);
    }
    
    @GetMapping("/getById")
    @Operation(summary = "获取单个详情")
    public MessageInfo<BaseGlobalParamCategoryVO> getById(Long id) {
        BaseGlobalParamCategory category = iBaseGlobalParamCategoryService.getById(id);
        BizExceptionCheckUtils.isNull(category, BaseResultCode.BASE_DATA_IS_NULL);
        BaseGlobalParamCategoryVO categoryVO = new BaseGlobalParamCategoryVO();
        ObjectConvertUtils.beanCopyProperties(category, categoryVO);
        return MessageInfo.ok(categoryVO);
    }
    
    @DeleteMapping("/delete")
    @Operation(summary = "删除")
    public void delete(Long id) {
        BaseGlobalParamCategory category = iBaseGlobalParamCategoryService.getById(id);
        BizExceptionCheckUtils.isNull(category, BaseResultCode.BASE_DATA_IS_NULL);
        iBaseGlobalParamCategoryService.removeById(id);
    }
}

package com.efreight.base.controller;

import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.efreight.base.entity.BaseGlobalView;
import com.efreight.base.model.web.req.BaseGlobalViewReq;
import com.efreight.base.model.web.req.GlobalViewValuesWithStringReq;
import com.efreight.base.model.web.vo.BaseGlobalViewVO;
import com.efreight.base.service.IBaseGlobalViewService;
import com.efreight.common.enums.OrderTypeEnum;
import com.efreight.common.response.MessageInfo;
import com.efreight.common.utils.ObjectConvertUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 视图表 前端控制器
 * </p>
 *
 * @author Zhang Yongwei
 * @since 2024-08-13
 */
@Tag(name =  "视图" )
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/baseGlobalView")
public class BaseGlobalViewController {
    
    private final IBaseGlobalViewService iBaseGlobalViewService;
    
    @GetMapping("/listByOrderType")
    @Operation(summary = "根据订单类型获取视图列表")
    public MessageInfo<List<BaseGlobalViewVO>> listByOrderType(OrderTypeEnum orderTypeEnum) {
        log.info("根据订单类型获取视图列表 --> 方法名:【list】--> 参数:orderTypeEnum = {}", orderTypeEnum);
        List<BaseGlobalView> list = iBaseGlobalViewService.list(
                Wrappers.<BaseGlobalView>lambdaQuery().eq(BaseGlobalView::getOrderType, orderTypeEnum));
        return MessageInfo.ok(ObjectConvertUtils.listCopyProperties(list, BaseGlobalViewVO.class));
    }
    
    /** Feign接口调用 @com.efreight.base.feign.BaseGlobalParamsFeignService */
    @PostMapping("/getCustomTagsValueToString")
    @Operation(summary = "替换全局标签为字符串")
    public MessageInfo<String> getCustomTagsValueToString(@RequestBody GlobalViewValuesWithStringReq req) {
        String targetStr = iBaseGlobalViewService.getCustomTagsValueToString(req.getSourceStr(), req.getParams(), req.getTagsFlag(), req.getOrderType());
        return MessageInfo.ok(targetStr);
    }
    
    @PostMapping("/save")
    @Operation(summary = "新增")
    public void save(@RequestBody BaseGlobalViewReq req) {
        BaseGlobalView entity = new BaseGlobalView();
        ObjectConvertUtils.beanCopyProperties(req, entity);
        iBaseGlobalViewService.save(entity);
    }
}

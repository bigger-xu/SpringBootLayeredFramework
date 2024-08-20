package com.efreight.base.controller;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.efreight.base.entity.BaseGlobalViewField;
import com.efreight.base.model.web.req.BaseGlobalViewFieldReq;
import com.efreight.base.model.web.vo.BaseGlobalViewFieldVO;
import com.efreight.base.service.IBaseGlobalViewFieldService;
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
 * 视图字段表 前端控制器
 * </p>
 *
 * @author Zhang Yongwei
 * @since 2024-08-13
 */
@Tag(name ="视图字段")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/baseGlobalViewField")
public class BaseGlobalViewFieldController {
    
    private final IBaseGlobalViewFieldService iBaseGlobalViewFieldService;
    
    @GetMapping("/listByViewId")
    @Operation(summary = "根据视图ID获取字段列表")
    public MessageInfo<List<BaseGlobalViewFieldVO>> listByViewId(Long viewId) {
        List<BaseGlobalViewField> list = iBaseGlobalViewFieldService.list(
                Wrappers.<BaseGlobalViewField>lambdaQuery().eq(BaseGlobalViewField::getViewId, viewId));
        return MessageInfo.ok(ObjectConvertUtils.listCopyProperties(list, BaseGlobalViewFieldVO.class));
    }
    
    @PostMapping("/save")
    @Operation(summary = "批量新增")
    public void save(@RequestBody List<BaseGlobalViewFieldReq> req) {
        List<BaseGlobalViewField> fieldList = new ArrayList<>();
        for (BaseGlobalViewFieldReq fieldReq : req) {
            BaseGlobalViewField entity = new BaseGlobalViewField();
            ObjectConvertUtils.beanCopyProperties(fieldReq, entity);
            fieldList.add(entity);
        }
        iBaseGlobalViewFieldService.saveBatch(fieldList);
    }
}

package com.efreight.base.controller;

import java.util.List;

import com.efreight.base.entity.BaseGlobalParamDetail;
import com.efreight.base.model.web.req.BaseGlobalParamDetailReq;
import com.efreight.base.model.web.vo.BaseGlobalParamDetailVO;
import com.efreight.base.service.IBaseGlobalParamDetailService;
import com.efreight.common.constants.BaseResultCode;
import com.efreight.common.response.MessageInfo;
import com.efreight.common.utils.BizExceptionCheckUtils;
import com.efreight.common.utils.ObjectConvertUtils;
import com.efreight.common.utils.StringUtil;
import io.swagger.v3.oas.annotations.Operation;
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
 * 全局参数详情表 前端控制器
 * </p>
 *
 * @author Zhang Yongwei
 * @since 2024-08-13
 */
@Tag(name = "全局参数详情")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/baseGlobalParamDetail")
public class BaseGlobalParamDetailController {
    
    private final IBaseGlobalParamDetailService iBaseGlobalParamDetailService;
    
    @GetMapping("/listByOrderType")
    @Operation(summary = "根据分类ID获取全局参数列表")
    public MessageInfo<List<BaseGlobalParamDetailVO>> listByOrderType(Long categoryId, String keywords) {
        List<BaseGlobalParamDetailVO> list = iBaseGlobalParamDetailService.customList(categoryId, keywords);
        return MessageInfo.ok(list);
    }
    
    @PostMapping("/save")
    @Operation(summary = "新增")
    public void save(@RequestBody BaseGlobalParamDetailReq req) {
        iBaseGlobalParamDetailService.saveParamDetail(req);
    }
    
    @PostMapping("/update")
    @Operation(summary = "修改")
    public void update(@RequestBody BaseGlobalParamDetailReq req) {
        iBaseGlobalParamDetailService.updateParamDetail(req);
    }
    
    @GetMapping("/getById")
    @Operation(summary = "获取单个详情")
    public MessageInfo<BaseGlobalParamDetailVO> getById(Long id) {
        BaseGlobalParamDetail detail = iBaseGlobalParamDetailService.getById(id);
        BizExceptionCheckUtils.isNull(detail, BaseResultCode.BASE_DATA_IS_NULL);
        BaseGlobalParamDetailVO detailVO = new BaseGlobalParamDetailVO();
        ObjectConvertUtils.beanCopyProperties(detail, detailVO);
        detailVO.setParamName(detailVO.getParamName().replace(StringUtil.DEFAULT_TAGS_START, "")
                                      .replace(StringUtil.DEFAULT_TAGS_END, ""));
        return MessageInfo.ok(detailVO);
    }
    
    @DeleteMapping("/delete")
    @Operation(summary = "删除")
    public void delete(Long id) {
        BaseGlobalParamDetail detail = iBaseGlobalParamDetailService.getById(id);
        BizExceptionCheckUtils.isNull(detail, BaseResultCode.BASE_DATA_IS_NULL);
        iBaseGlobalParamDetailService.removeById(id);
    }
}

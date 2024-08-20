package com.efreight.base.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.efreight.base.entity.BaseAirline;
import com.efreight.base.model.web.req.BaseAirlineReq;
import com.efreight.base.model.web.vo.BaseAirlineVO;
import com.efreight.base.service.IBaseAirlineService;
import com.efreight.common.global.BaseController;
import com.efreight.common.global.PageParam;
import com.efreight.common.response.MessageInfo;
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
 * 基础信息-航司
 * </p>
*
* @author Zhang YongWei
* @since 2024-08-15
*/
@Tag(name = "基础信息-航司")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/baseAirline")
public class BaseAirlineController extends BaseController {

    private final IBaseAirlineService iBaseAirlineService;

    @PostMapping("/page")
    @Operation(summary = "分页")
    public MessageInfo<Page<BaseAirlineVO>> page(@RequestBody PageParam pageReq) {
        Page<BaseAirline> page = iBaseAirlineService.page(new Page<>(pageReq.getCurrent(), pageReq.getSize()));
        Page<BaseAirlineVO> pageVO = new Page<>();
        ObjectConvertUtils.pageCopyProperties(page, pageVO, BaseAirlineVO.class);
        return MessageInfo.ok(pageVO);
    }

    @PostMapping("/save")
    @Operation(summary = "新增")
    public void save(@RequestBody BaseAirlineReq req) {
        BaseAirline entity = new BaseAirline();
        ObjectConvertUtils.beanCopyProperties(req, entity);
        iBaseAirlineService.save(entity);
    }

    @PostMapping("/update")
    @Operation(summary = "修改")
    public void update(@RequestBody BaseAirlineReq req) {
        BaseAirline entity = new BaseAirline();
        ObjectConvertUtils.beanCopyProperties(req, entity);
        iBaseAirlineService.updateById(entity);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除")
    @Parameters({@Parameter(name = "id", description = "主键ID", required = true, in= ParameterIn.QUERY)})
    public void delete(Long id) {
        iBaseAirlineService.removeById(id);
    }

    @GetMapping("/detail")
    @Operation(summary = "详情")
    @Parameters({@Parameter(name = "id", description = "主键ID", required = true, in= ParameterIn.QUERY)})
    public MessageInfo<BaseAirlineVO> detail(Long id) {
        BaseAirline entity = iBaseAirlineService.getById(id);
        BaseAirlineVO vo = ObjectConvertUtils.beanCopyProperties(entity, BaseAirlineVO.class);
        return MessageInfo.ok(vo);
    }
}
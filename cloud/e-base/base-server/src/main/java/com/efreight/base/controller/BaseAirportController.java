package com.efreight.base.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.efreight.base.entity.BaseAirport;
import com.efreight.base.model.web.req.BaseAirportReq;
import com.efreight.base.model.web.vo.BaseAirportVO;
import com.efreight.base.service.IBaseAirportService;
import com.efreight.common.global.BaseController;
import com.efreight.common.global.PageParam;
import com.efreight.common.response.MessageInfo;
import com.efreight.common.utils.ObjectConvertUtils;
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
 * 基础信息-机场信息
 * </p>
 *
 * @author caiwd
 * @since 2024-08-14
 */
@Tag(name =  "基础信息-机场信息" )
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/baseAirport")
public class BaseAirportController extends BaseController {
	
	private final IBaseAirportService iBaseAirportService;
	
	@PostMapping("/page")
	@Operation(summary = "分页")
	public MessageInfo<Page<BaseAirportVO>> page(@RequestBody PageParam pageReq) {
		Page<BaseAirport> page = iBaseAirportService.page(new Page<>(pageReq.getCurrent(), pageReq.getSize()));
		Page<BaseAirportVO> pageVO = new Page<>();
		ObjectConvertUtils.pageCopyProperties(page, pageVO, BaseAirportVO.class);
		return MessageInfo.ok(pageVO);
	}
	
	@PostMapping("/save")
	@Operation(summary = "新增")
	public void save(@RequestBody BaseAirportReq baseAirportReq) {
		BaseAirport baseAirport = new BaseAirport();
		ObjectConvertUtils.beanCopyProperties(baseAirportReq, baseAirport);
		iBaseAirportService.save(baseAirport);
	}
	
	@PostMapping("/update")
	@Operation(summary = "修改")
	public void update(@RequestBody BaseAirportReq baseAirportReq) {
		BaseAirport baseAirport = new BaseAirport();
		ObjectConvertUtils.beanCopyProperties(baseAirportReq, baseAirport);
		iBaseAirportService.updateById(baseAirport);
	}
	
	@DeleteMapping("/delete")
	@Operation(summary = "删除")
	public void delete(Long id) {
		iBaseAirportService.removeById(id);
	}
	
	@GetMapping("/detail")
	@Operation(summary = "详情")
	public MessageInfo<BaseAirportVO> detail(Long id) {
		BaseAirport baseAirport = iBaseAirportService.getById(id);
		BaseAirportVO vo = ObjectConvertUtils.beanCopyProperties(baseAirport, BaseAirportVO.class);
		return MessageInfo.ok(vo);
	}

}

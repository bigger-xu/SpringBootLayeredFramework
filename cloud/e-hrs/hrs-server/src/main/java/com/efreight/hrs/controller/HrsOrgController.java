package com.efreight.hrs.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.efreight.common.global.BaseController;
import com.efreight.common.response.MessageInfo;
import com.efreight.hrs.model.web.req.HrsOrgPageReq;
import com.efreight.hrs.model.web.req.HrsOrgReq;
import com.efreight.hrs.model.web.vo.HrsOrgVO;
import com.efreight.hrs.service.IHrsOrgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * HRS 签约公司 前端控制器
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
@Tag(name =  "分公司接口" )
@RestController
@RequestMapping("/hrsOrg")
public class HrsOrgController extends BaseController {

    @Autowired
    private IHrsOrgService hrsOrgService;

    @Operation(summary = "分公司新增或修改")
    @PostMapping("/saveOrUpdate")
    public void saveOrg(@RequestBody HrsOrgReq req) {
        hrsOrgService.addOrModifyOrg(req);
    }

    @Operation(summary ="分公司新增或修改")
    @PostMapping("/getPage")
    public MessageInfo<IPage<HrsOrgVO>> getPage(@RequestBody HrsOrgPageReq queryReq) {
        Long id = super.currentLoginUser().getId();
        IPage<HrsOrgVO> HrsOrgPageVO = hrsOrgService.getPage(queryReq);
        return MessageInfo.ok(HrsOrgPageVO);
    }

}

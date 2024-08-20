package com.efreight.hrs.controller;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.efreight.common.global.BaseController;
import com.efreight.common.response.MessageInfo;
import com.efreight.hrs.constants.HrsConstants;
import com.efreight.hrs.model.web.req.HrsGlobalUserQueryReq;
import com.efreight.hrs.model.web.req.HrsManagerUserQueryReq;
import com.efreight.hrs.model.web.vo.HrsGlobalUserDetailVO;
import com.efreight.hrs.model.web.vo.HrsGlobalUserPageVO;
import com.efreight.hrs.model.web.vo.HrsManagerUserDetailVO;
import com.efreight.hrs.model.web.vo.HrsManagerUserPageVO;
import com.efreight.hrs.model.web.vo.HrsUserDataPermissonVO;
import com.efreight.hrs.model.web.vo.TreeVO;
import com.efreight.hrs.service.IHrsUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * className:HrsUserController
 * Description:
 *
 * @Date:2024-08-12 17:05
 * @Author:Rocky
 */
@Tag(name = "全局用户和用户管理接口")
@RestController
@RequestMapping("/hrsUser")
public class HrsUserController extends BaseController {

    @Autowired
    private IHrsUserService iHrsUserService;

    @Operation(summary ="获取用户PC菜单权限（树形结构）")
    @PostMapping("/getUserMenuPermission")
    public MessageInfo<List<TreeVO>> getUserMenuPermission() {
        List<TreeVO> userMenuPermission = iHrsUserService.getUserMenuPermission(currentLoginUserId(), currentLoginUserOrgId(), currentLoginUser().getLoginWay(), HrsConstants.MENU, HrsConstants.MODULE);
        return MessageInfo.ok(userMenuPermission);

    }

    @Operation(summary ="获取用户PDA菜单权限（树形结构）")
    @PostMapping("/getUserPdaMenuPermission")
    public MessageInfo<List<TreeVO>> getUserPdaMenuPermission() {
        List<TreeVO> userMenuPermission = iHrsUserService.getUserMenuPermission(currentLoginUserId(), currentLoginUserOrgId(), currentLoginUser().getLoginWay(), HrsConstants.PAD_MENU, HrsConstants.PDA_MODULE);
        return MessageInfo.ok(userMenuPermission);

    }

    @Operation(summary ="获取用户button权限")
    @PostMapping("/getUserButtonPermission")
    public MessageInfo<List<String>> getUserButtonPermission() {
        List<String> userMenuPermission = iHrsUserService.getUserButtonPermission(currentLoginUserId(), currentLoginUserOrgId(), currentLoginUser().getLoginWay());
        return MessageInfo.ok(userMenuPermission);

    }

    @Operation(summary ="全局用户管理分页列表")
    @PostMapping("/getGlobalPage")
    public MessageInfo<IPage<HrsGlobalUserPageVO>> getGlobalPage(@RequestBody HrsGlobalUserQueryReq queryReq) {
        IPage<HrsGlobalUserPageVO> HrsOrgPageVO = iHrsUserService.getGlobalUserPage(queryReq);
        return MessageInfo.ok(HrsOrgPageVO);
    }
    @Operation(summary="全局用户管理用户详情")
    @GetMapping("/getGlobalUserDetail")
    public MessageInfo<HrsGlobalUserDetailVO> getGlobalUserDetail(@RequestParam("userId") Long userId) {
        HrsGlobalUserDetailVO hrsUserVO = iHrsUserService.getGlobalUserDetail(userId);
        return MessageInfo.ok(hrsUserVO);
    }

    @Operation(summary="全局用户管理更新用户")
    @PostMapping("/modifyGlobalUser")
    public MessageInfo<String> modifyGlobalUser(@RequestBody HrsGlobalUserDetailVO req) {
        iHrsUserService.modifyGlobalUser(req);
        return MessageInfo.ok();
    }

    @Operation(summary="管理用户管理列表")
    @PostMapping("/getManagerUserPage")
    public MessageInfo<IPage<HrsManagerUserPageVO>> getManagerUserPage(@RequestBody HrsManagerUserQueryReq queryReq) {
        queryReq.setOrgId(currentLoginUserOrgId());
        IPage<HrsManagerUserPageVO> HrsOrgPageVO = iHrsUserService.getManagerUserPage(queryReq);
        return MessageInfo.ok(HrsOrgPageVO);
    }


    @Operation(summary="用户管理用户详情")
    @GetMapping("/getManagerUserDetail")
    public MessageInfo<HrsManagerUserDetailVO> getManagerUserDetail(@RequestParam("userId") Long userId) {
        HrsManagerUserDetailVO hrsUserVO = iHrsUserService.getMangerUserDetail(userId, currentLoginUserOrgId());
        return MessageInfo.ok(hrsUserVO);
    }

    @Operation(summary="用户管理更新用户")
    @PostMapping("/modifyManagerUser")
    public MessageInfo<String> modifyManagerUser(@RequestBody HrsManagerUserDetailVO req) {
        req.setOrgId(currentLoginUserOrgId());
        iHrsUserService.modifyManagerUser(req, super.currentLoginUser());
        return MessageInfo.ok();
    }

    @Operation(summary="根据当前用户获取数据权限-返回所涉猎的全部人员")
    @GetMapping("/getOrderPermissionInfo")
    public MessageInfo<HrsUserDataPermissonVO> getOrderPermissionInfo(@RequestParam("userId") Long userId,@RequestParam("orgId") Long orgId,@RequestParam("orderType") String orderType) {
        HrsUserDataPermissonVO vo = iHrsUserService.getOrderPermissionInfo(userId, orgId, orderType);
        return MessageInfo.ok(vo);
    }


}

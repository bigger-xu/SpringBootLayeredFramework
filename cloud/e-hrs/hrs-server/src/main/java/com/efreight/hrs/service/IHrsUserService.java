package com.efreight.hrs.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.efreight.common.global.login.LoginUserVO;
import com.efreight.hrs.entity.HrsUser;
import com.efreight.hrs.model.web.req.HrsGlobalUserQueryReq;
import com.efreight.hrs.model.web.req.HrsManagerUserQueryReq;
import com.efreight.hrs.model.web.vo.HrsGlobalUserDetailVO;
import com.efreight.hrs.model.web.vo.HrsGlobalUserPageVO;
import com.efreight.hrs.model.web.vo.HrsManagerUserDetailVO;
import com.efreight.hrs.model.web.vo.HrsManagerUserPageVO;
import com.efreight.hrs.model.web.vo.HrsUserDataPermissonVO;
import com.efreight.hrs.model.web.vo.TreeVO;

/**
 * <p>
 * HRS 用户表 服务类
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
public interface IHrsUserService extends IService<HrsUser> {
    /**
     *
     * @param userId  用户ID
     * @param orgId   公司ID
     * @param loginWay  登录方式
     * @param menuType  菜单类型
     * @param parentModule 父模块
     * @return
     */
    List<TreeVO> getUserMenuPermission(Long userId, Long orgId, String loginWay, String menuType, String parentModule);

    /**
     *
     * @param userId 用户ID
     * @param orgId  公司ID
     * @param loginWay 登录方式
     * @return
     */
    List<String> getUserButtonPermission(Long userId, Long orgId, String loginWay);

    /**
     * 全局用户管理分页列表
     * @param req HrsGlobalUserQueryReq
     * @return
     */
    IPage<HrsGlobalUserPageVO> getGlobalUserPage(HrsGlobalUserQueryReq req);

    /**
     * 全局用户管理用户详情
     * @param userId
     * @return
     */
    HrsGlobalUserDetailVO getGlobalUserDetail(Long userId);

    /**
     * 全局用户管理更新用户
     * @param req
     */
    void modifyGlobalUser(HrsGlobalUserDetailVO req);

    /**
     * 管理用户管理列表
     * @param queryReq
     * @return
     */
    IPage<HrsManagerUserPageVO> getManagerUserPage(HrsManagerUserQueryReq queryReq);

    /**
     * 用户管理用户详情
     * @param userId
     * @param orgId
     * @return
     */
    HrsManagerUserDetailVO getMangerUserDetail(Long userId, Long orgId);

    /**
     * 用户管理更新用户
     * @param req
     * @param loginUserVO
     */
    void modifyManagerUser(HrsManagerUserDetailVO req, LoginUserVO loginUserVO);

    /**
     * 根据当前用户获取数据权限-返回所涉猎的全部人员
     * @param userId
     * @param orgId
     * @param orderType
     * @return
     */
    HrsUserDataPermissonVO getOrderPermissionInfo(Long userId,Long orgId,String orderType);
}

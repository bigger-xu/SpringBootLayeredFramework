package com.efreight.hrs.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.efreight.common.constants.HrsResultCode;
import com.efreight.common.enums.OrderTypeEnum;
import com.efreight.common.global.login.LoginDeptVO;
import com.efreight.common.global.login.LoginUserVO;
import com.efreight.common.utils.BizExceptionCheckUtils;
import com.efreight.common.utils.ObjectConvertUtils;
import com.efreight.hrs.constants.HrsConstants;
import com.efreight.hrs.dao.HrsPermissionMapper;
import com.efreight.hrs.dao.HrsUserMapper;
import com.efreight.hrs.entity.HrsOrgUser;
import com.efreight.hrs.entity.HrsPermission;
import com.efreight.hrs.entity.HrsUser;
import com.efreight.hrs.entity.HrsUserDept;
import com.efreight.hrs.entity.HrsUserOrgWorkgroup;
import com.efreight.hrs.entity.HrsUserRole;
import com.efreight.hrs.model.enmus.LoginWayEnum;
import com.efreight.hrs.model.web.req.HrsGlobalUserQueryReq;
import com.efreight.hrs.model.web.req.HrsManagerUserQueryReq;
import com.efreight.hrs.model.web.vo.HrsGlobalUserDetailVO;
import com.efreight.hrs.model.web.vo.HrsGlobalUserPageVO;
import com.efreight.hrs.model.web.vo.HrsManagerUserDetailVO;
import com.efreight.hrs.model.web.vo.HrsManagerUserPageVO;
import com.efreight.hrs.model.web.vo.HrsUserDataPermissonVO;
import com.efreight.hrs.model.web.vo.OrgDeptVO;
import com.efreight.hrs.model.web.vo.TreeVO;
import com.efreight.hrs.service.IHrsOrgUserService;
import com.efreight.hrs.service.IHrsUserDeptService;
import com.efreight.hrs.service.IHrsUserOrgWorkgroupService;
import com.efreight.hrs.service.IHrsUserRoleService;
import com.efreight.hrs.service.IHrsUserService;
import com.efreight.hrs.util.TreeUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * HRS 用户表 服务实现类
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
@Service
@Slf4j
public class HrsUserServiceImpl extends ServiceImpl<HrsUserMapper, HrsUser> implements IHrsUserService {
    @Resource
    private  HrsPermissionMapper hrsPermissionMapper;
    @Resource
    private IHrsUserDeptService hrsUserDeptService;
    @Resource
    private IHrsOrgUserService hrsOrgUserService;

    @Resource
    private IHrsUserRoleService hrsUserRoleService;
    @Resource
    private IHrsUserOrgWorkgroupService hrsUserOrgWorkgroupService;

    @Override
    public List<TreeVO> getUserMenuPermission(Long userId, Long orgId, String loginWay, String menuType, String parentModule) {
        List<HrsPermission> userMenuPermission;
        if (StringUtils.isNotEmpty(loginWay)&&loginWay.equals(LoginWayEnum.ADMIN.name())) {
            userMenuPermission = hrsPermissionMapper.getUserPermission(userId, orgId, menuType, HrsConstants.Y, parentModule);
        } else {
            userMenuPermission = hrsPermissionMapper.getUserPermission(userId, orgId, menuType, HrsConstants.N, parentModule);
        }
        return TreeUtil.getMenuTreeVOS(userMenuPermission);
    }

    @Override
    public List<String> getUserButtonPermission(Long userId, Long orgId, String loginWay) {
        List<HrsPermission> userMenuPermission;
        if (StringUtils.isNotEmpty(loginWay)&&loginWay.equals(LoginWayEnum.ADMIN.name())) {
            userMenuPermission = hrsPermissionMapper.getUserPermission(userId, orgId, HrsConstants.BUTTON, HrsConstants.Y, HrsConstants.MODULE);
        } else {
            userMenuPermission = hrsPermissionMapper.getUserPermission(userId, orgId, HrsConstants.BUTTON, HrsConstants.N, HrsConstants.MODULE);
        }
        if (CollectionUtil.isEmpty(userMenuPermission)) {
            return new ArrayList<>();
        }
        return userMenuPermission.stream().map(HrsPermission::getPermission).collect(Collectors.toList());
    }

    @Override
    public IPage<HrsGlobalUserPageVO> getGlobalUserPage(HrsGlobalUserQueryReq req) {
        return baseMapper.getGlobalUserPage(new Page<>(req.getCurrent(), req.getSize()), req);
    }

    @Override
    public HrsGlobalUserDetailVO getGlobalUserDetail(Long userId) {
        HrsUser hrsUser = getBaseMapper().selectById(userId);
        HrsGlobalUserDetailVO hrsUserVO = new HrsGlobalUserDetailVO();
        hrsUserVO.setUserEname(hrsUser.getUserEname());
        hrsUserVO.setUserEmail(hrsUser.getUserEmail());
        hrsUserVO.setUserId(hrsUser.getId());
        hrsUserVO.setRemark(hrsUser.getRemark());
        hrsUserVO.setJobNumber(hrsUser.getJobNumber());
        hrsUserVO.setPhoneNumber(hrsUser.getPhoneNumber());
        hrsUserVO.setUserName(hrsUser.getUserName());
        hrsUserVO.setOrgDeptVOList(baseMapper.getGlobalUserOrgDeptList(userId));
        return hrsUserVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyGlobalUser(HrsGlobalUserDetailVO req) {
        //清除用户部门
        Set<Long> deptIds = hrsUserDeptService.list(Wrappers.<HrsUserDept>lambdaQuery().eq(HrsUserDept::getUserId, req.getUserId()))
                .stream().map(HrsUserDept::getId)
                .collect(Collectors.toSet());
        hrsUserDeptService.removeBatchByIds(deptIds);
        List<OrgDeptVO> orgDeptVOList = req.getOrgDeptVOList();
        List<HrsUserDept> hrsUserDeptList = ObjectConvertUtils.listCopyProperties(orgDeptVOList, HrsUserDept.class);
        hrsUserDeptService.saveBatch(hrsUserDeptList);
        List<HrsOrgUser> hrsOrgUsers = hrsOrgUserService.list(Wrappers.<HrsOrgUser>lambdaQuery().eq(HrsOrgUser::getUserId, req.getUserId()));
        if (CollectionUtil.isNotEmpty(hrsOrgUsers)) {
            Set<Long> haveOrgIds = hrsOrgUsers.stream().map(HrsOrgUser::getId).collect(Collectors.toSet());
            hrsOrgUserService.removeBatchByIds(haveOrgIds);
        }
        HrsOrgUser hrsOrgUser = hrsOrgUsers.stream().filter(item -> item.getIsDefaultOrg().equals(HrsConstants.Y)).findFirst().orElse(new HrsOrgUser());
        List<HrsOrgUser> hrsOrgUserList = Lists.newArrayList();
        boolean haveDefaultOrg = false;
        for (OrgDeptVO item : orgDeptVOList) {
            HrsOrgUser record = new HrsOrgUser();
            record.setUsed(item.getUsed());
            record.setOrgId(item.getOrgId());
            record.setUserId(item.getUserId());
            record.setIsAdmin(HrsConstants.N);
            if (item.getOrgId().equals(hrsOrgUser.getOrgId())) {
                record.setIsDefaultOrg(HrsConstants.Y);
                haveDefaultOrg = true;
            } else {
                record.setIsDefaultOrg(HrsConstants.N);
            }
            hrsOrgUserList.add(record);
        }
        if (!haveDefaultOrg) {
            hrsOrgUserList.get(0).setIsDefaultOrg(HrsConstants.Y);
        }
        //修改备注
        boolean saveOrgUsers = hrsOrgUserService.saveBatch(hrsOrgUserList);
        if (saveOrgUsers) {
            HrsUser hrsUser = this.baseMapper.selectById(req.getUserId());
            if (hrsUser != null) {
                hrsUser.setRemark(req.getRemark());
                hrsUser.setUserEname(req.getUserEname());
                this.baseMapper.updateById(hrsUser);
            }
        }
    }
    @Override
    public IPage<HrsManagerUserPageVO> getManagerUserPage(HrsManagerUserQueryReq req) {
        return baseMapper.getManagerUserPage(new Page<>(req.getCurrent(), req.getSize()), req);
    }
    @Override
    public HrsManagerUserDetailVO getMangerUserDetail(Long userId, Long orgId) {
        HrsManagerUserDetailVO detailVO = new HrsManagerUserDetailVO();
        HrsUser hrsUser = getBaseMapper().selectById(userId);
        BeanUtil.copyProperties(hrsUser, detailVO);
        List<HrsUserRole> hrsUserRoles = hrsUserRoleService.list(Wrappers.<HrsUserRole>lambdaQuery().eq(HrsUserRole::getUserId, userId).eq(HrsUserRole::getOrgId, orgId));
        if (CollectionUtil.isNotEmpty(hrsUserRoles)) {
            Long masterRoleId = hrsUserRoles.stream()
                    .filter(hrsUserRole -> hrsUserRole.getRoleType().equals(HrsConstants.MASTER))
                    .map(HrsUserRole::getRoleId).findFirst()
                    .orElse(null);
            List<Long> slaveRoleIds = hrsUserRoles.stream()
                    .filter(hrsUserRole -> hrsUserRole.getRoleType().equals(HrsConstants.SLAVE))
                    .map(HrsUserRole::getRoleId)
                    .collect(Collectors.toList());
            detailVO.setMastRoleId(masterRoleId);
            detailVO.setSlaveRoleIds(slaveRoleIds);
        }
        LoginDeptVO userDeptInfo = hrsUserDeptService.getUserDeptInfo(userId, orgId);
        if (userDeptInfo != null) {
            detailVO.setDeptName(userDeptInfo.getDeptName());
            detailVO.setDeptId(userDeptInfo.getId());
        }
        LambdaQueryWrapper<HrsOrgUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(HrsOrgUser::getUserId,userId);
        wrapper.eq(HrsOrgUser::getOrgId,orgId);
        HrsOrgUser orgUser = hrsOrgUserService.getOne(wrapper);
        if(orgUser!=null){
            detailVO.setOrderPermission(orgUser.getOrderPermission());
            detailVO.setOrderPermissionAi(orgUser.getOrderPermissionAi());
            detailVO.setShareUserIds(orgUser.getShareUserIds());
            detailVO.setShareUserIdsAi(orgUser.getShareUserIdsAi());
        }
        return detailVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyManagerUser(HrsManagerUserDetailVO req, LoginUserVO loginUserVO) {
        BizExceptionCheckUtils.isTrue((Objects.isNull(req.getMastRoleId()) || req.getMastRoleId() == 0L), HrsResultCode.MASTER_ROLE_NULL);
        HrsUser hrsUser = getBaseMapper().selectById(req.getId());
        hrsUser.setRemark(req.getRemark());
        hrsUser.setUserEname(req.getUserEname());
        hrsUser.setQq(req.getQq());
        hrsUser.setWechat(req.getWechat());
        getBaseMapper().updateById(hrsUser);
        LambdaUpdateWrapper<HrsOrgUser> wrapperUp = new LambdaUpdateWrapper<>();
        wrapperUp.set(HrsOrgUser::getOrderPermission,req.getOrderPermission())
                .set(HrsOrgUser::getOrderPermissionAi,req.getOrderPermissionAi())
                .set(HrsOrgUser::getShareUserIds,req.getShareUserIds())
                .set(HrsOrgUser::getShareUserIdsAi,req.getShareUserIdsAi())
                .eq(HrsOrgUser::getUserId,req.getId());
        hrsOrgUserService.update(wrapperUp);
        List<HrsUserRole> hrsUserRoles = hrsUserRoleService.list(Wrappers.<HrsUserRole>lambdaQuery().eq(HrsUserRole::getOrgId, req.getOrgId()).eq(HrsUserRole::getUserId, req.getId()));
        hrsUserRoleService.removeBatchByIds(hrsUserRoles);
        HrsUserRole hrsUserRole = new HrsUserRole();
        hrsUserRole.setUserId(req.getId());
        hrsUserRole.setOrgId(req.getOrgId());
        hrsUserRole.setRoleId(req.getMastRoleId());
        hrsUserRole.setRoleType(HrsConstants.MASTER);
        hrsUserRoleService.save(hrsUserRole);
        req.getSlaveRoleIds().forEach(roleId -> {
            HrsUserRole role = new HrsUserRole();
            role.setUserId(req.getId());
            role.setOrgId(req.getOrgId());
            role.setRoleId(roleId);
            role.setRoleType(HrsConstants.SLAVE);
            hrsUserRoleService.save(role);
        });
    }

    @Override
    public HrsUserDataPermissonVO getOrderPermissionInfo(Long userId, Long orgId, String orderType) {
        BizExceptionCheckUtils.isNull(userId, HrsResultCode.HRS_NO_USER_ID_ERROR);
        BizExceptionCheckUtils.isNull(orgId, HrsResultCode.HRS_NO_ORG_ID_ERROR);
        orderType = StringUtils.isEmpty(orderType)?"AE":orderType;
        HrsUserDataPermissonVO vo = new HrsUserDataPermissonVO();
        //查询用户个人信息 扩展信息
        HrsManagerUserDetailVO detailVo = this.getMangerUserDetail(userId, orgId);
        HashSet<Long> setList = new HashSet<>();
        //可能有逻辑 互斥，所以未来解耦合  做了两次轮子
        if(OrderTypeEnum.AE.name().equals(orderType)){
            //出口业务
            //数据分享人  创建人  责任销售 责任客服 责任XX 等等如果有分享人是当前等路人 就有权限查询
            if (detailVo != null && StringUtils.isNotEmpty(detailVo.getOrderPermission())) {
                if(StringUtils.isNotEmpty(detailVo.getShareUserIds())){
                    String[] strArr = detailVo.getShareUserIds().split(",");
                    for(int i=0;i<strArr.length;i++){
                        setList.add(Long.valueOf(strArr[i]));
                    }
                }
            }
            //校验用户是否设置数据集权限
            if (detailVo != null && StringUtils.isNotEmpty(detailVo.getOrderPermission())) {
                //SELF 个人,GROUP 工作组,DEPT  部门
                //个人
                if (detailVo.getOrderPermission().contains("SELF")) {
                    setList.add(userId);
                }
                //工作组
                if (detailVo.getOrderPermission().contains("GROUP")) {
                    //获取userID 所在工作组
                    LambdaQueryWrapper<HrsUserOrgWorkgroup> queryWrapper = Wrappers.<HrsUserOrgWorkgroup>lambdaQuery()
                            .eq(HrsUserOrgWorkgroup::getUserId, userId)
                            .eq(HrsUserOrgWorkgroup::getOrgId, orgId);
                    List<HrsUserOrgWorkgroup> listGroup = hrsUserOrgWorkgroupService.list(queryWrapper);
                    if (!CollectionUtil.isEmpty(listGroup)) {
                        vo.setWorkGroupIdList(listGroup.stream().map(item -> {
                            return item.getWorkgroupId();
                        }).collect(Collectors.toList()));
                    }
                    setList.add(userId);
                }
                //部门
                if (detailVo.getOrderPermission().contains("DEPT")) {
                    //如果当前用户没有部门 ，权限还设置的部门权限怎么办？
                    LambdaQueryWrapper<HrsUserDept> queryWrapper = Wrappers.<HrsUserDept>lambdaQuery()
                            .eq(HrsUserDept::getOrgId, orgId)
                            .eq(HrsUserDept::getDeptId, detailVo.getDeptId());
                    List<HrsUserDept> listDep = hrsUserDeptService.list(queryWrapper);
                    if (!CollectionUtil.isEmpty(listDep)) {
                        listDep.stream().forEach(item -> {
                            setList.add(item.getUserId());
                        });
                    }
                    setList.add(userId);
                }
            }
        }else{
            //进口业务
            //数据分享人  创建人  责任销售 责任客服 责任XX 等等如果有分享人是当前等路人 就有权限查询
            if (detailVo != null && StringUtils.isNotEmpty(detailVo.getOrderPermissionAi())) {
                if(StringUtils.isNotEmpty(detailVo.getShareUserIdsAi())){
                    String[] strArr = detailVo.getShareUserIdsAi().split(",");
                    for(int i=0;i<strArr.length;i++){
                        setList.add(Long.valueOf(strArr[i]));
                    }
                }
            }
            //校验用户是否设置数据集权限
            if (detailVo != null && StringUtils.isNotEmpty(detailVo.getOrderPermissionAi())) {
                //SELF 个人,GROUP 工作组,DEPT  部门
                //个人
                if (detailVo.getOrderPermissionAi().contains("SELF")) {
                    setList.add(userId);
                }
                //工作组
                if (detailVo.getOrderPermissionAi().contains("GROUP")) {
                    //获取userID 所在工作组
                    LambdaQueryWrapper<HrsUserOrgWorkgroup> queryWrapper = Wrappers.<HrsUserOrgWorkgroup>lambdaQuery()
                            .eq(HrsUserOrgWorkgroup::getUserId, userId)
                            .eq(HrsUserOrgWorkgroup::getOrgId, orgId);
                    List<HrsUserOrgWorkgroup> listGroup = hrsUserOrgWorkgroupService.list(queryWrapper);
                    if (!CollectionUtil.isEmpty(listGroup)) {
                        vo.setWorkGroupIdList(listGroup.stream().map(item -> {
                            return item.getWorkgroupId();
                        }).collect(Collectors.toList()));
                    }
                    setList.add(userId);
                }
                //部门
                if (detailVo.getOrderPermissionAi().contains("DEPT")) {
                    //如果当前用户没有部门 ，权限还设置的部门权限怎么办？
                    LambdaQueryWrapper<HrsUserDept> queryWrapper = Wrappers.<HrsUserDept>lambdaQuery()
                            .eq(HrsUserDept::getOrgId, orgId)
                            .eq(HrsUserDept::getDeptId, detailVo.getDeptId());
                    List<HrsUserDept> listDep = hrsUserDeptService.list(queryWrapper);
                    if (!CollectionUtil.isEmpty(listDep)) {
                        listDep.stream().forEach(item -> {
                            setList.add(item.getUserId());
                        });
                    }
                    setList.add(userId);
                }
            }
        }
        List<Long> listIds = new ArrayList<>(setList);
        vo.setUserIdList(listIds);
        return vo;
    }
}

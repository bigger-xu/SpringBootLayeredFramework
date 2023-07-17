package com.cto.freemarker.entity;

import java.util.Date;

import com.cto.freemarker.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author Bigger-Xu
 * @since 2020-09-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AdminUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户类型(1个人；2餐饮机构 ；3分销商)
     */
    private String userType;

    /**
     * 用户状态(0锁定1正常)
     */
    private String status;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码校验规则md5(md5(password)+salt)
     */
    private String password;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String avator;

    /**
     * 最后一次登录时间
     */
    private Date lastLoginTime;

    /**
     * 最后一次登录IP
     */
    private String lastLoginIp;

    /**
     * 登录次数
     */
    private Integer loginCount;

    /**
     * 省
     */
    private Integer province;

    /**
     * 市
     */
    private Integer city;

    /**
     * 县
     */
    private Integer district;

    /**
     * 性别
     */
    private String sex;

    private String email;

    private String mobile;

    private String address;

    /**
     * 删除状态
     */
    private String deleteFlag;


}

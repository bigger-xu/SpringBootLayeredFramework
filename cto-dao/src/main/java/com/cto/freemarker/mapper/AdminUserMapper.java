package com.cto.freemarker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cto.freemarker.entity.AdminUser;
import com.cto.freemarker.entity.query.AdminUserQuery;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author Bigger-Xu
 * @since 2020-09-19
 */
public interface AdminUserMapper extends BaseMapper<AdminUser> {

    AdminUserQuery getByUserName(String userName);

    IPage<AdminUser> selectPageVo(IPage<AdminUserQuery> page, @Param("search") AdminUserQuery search);

    //IPage<AdminUserVo> selectPageVo(Page<AdminUserVo> page, AdminUserVo search);
}

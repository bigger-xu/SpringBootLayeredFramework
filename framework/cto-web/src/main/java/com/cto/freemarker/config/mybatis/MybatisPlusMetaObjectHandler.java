package com.cto.freemarker.config.mybatis;


import java.util.Date;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.cto.freemarker.entity.AdminUser;
import com.cto.freemarker.utils.UUIDUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.shiro.SecurityUtils;

import org.springframework.stereotype.Component;

/**
 * @author Bigger-Xu
 * @version v1.0.1
 * @date 2020/9/19 23:42
 */
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        AdminUser adminUser = (AdminUser) SecurityUtils.getSubject().getPrincipal();
        setFieldValByName("uuid", UUIDUtils.getRandom(32),metaObject);
        setFieldValByName("addTime",new Date(),metaObject);
        if(adminUser != null) {
            setFieldValByName("addUserId", adminUser.getId(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        AdminUser adminUser = (AdminUser) SecurityUtils.getSubject().getPrincipal();
        setFieldValByName("updateTime",new Date(),metaObject);
        if(adminUser != null){
            setFieldValByName("updateUserId",adminUser.getId(),metaObject);
        }
    }
}

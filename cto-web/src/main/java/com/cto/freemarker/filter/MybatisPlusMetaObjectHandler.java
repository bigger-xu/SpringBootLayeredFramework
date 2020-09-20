package com.cto.freemarker.filter;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.cto.freemarker.utils.UUIDUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Bigger-Xu
 * @version v1.0.1
 * @date 2020/9/19 23:42
 */
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("uuid", UUIDUtils.getRandom(32),metaObject);
        setFieldValByName("addTime",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateTime",new Date(),metaObject);
    }
}

package com.efreight.base.config;

import java.time.LocalDateTime;
import java.util.Optional;

import cn.dev33.satoken.context.SaHolder;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.efreight.common.global.login.LoginUserVO;
import com.efreight.common.global.login.LoginVO;
import org.apache.ibatis.reflection.MetaObject;

import org.springframework.stereotype.Component;

/**
 * 自动填充添加时间和修改时间
 *
 * @author 张永伟
 * @since 2023/8/3
 */
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {
    private final static String USER_DETAIL = "USER_DETAIL";
    
    @Override
    public void insertFill(MetaObject metaObject) {
        LoginUserVO loginUserVO = getLoginUserVO();
        setFieldValByName("creatorId", loginUserVO.getId(), metaObject);
        setFieldValByName("creatorName", loginUserVO.getUserName(), metaObject);
        setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        
    }
    
    @Override
    public void updateFill(MetaObject metaObject) {
        LoginUserVO loginUserVO = getLoginUserVO();
        setFieldValByName("editorId", loginUserVO.getId(), metaObject);
        setFieldValByName("editorName", loginUserVO.getUserName(), metaObject);
        setFieldValByName("editTime", LocalDateTime.now(), metaObject);
    }

    private static LoginUserVO getLoginUserVO() {
        try {
            Object userDetail = Optional.ofNullable(SaHolder.getStorage().get(USER_DETAIL)).orElseGet(LoginVO::new);
            LoginVO loginVO = (LoginVO) userDetail;
            return Optional.ofNullable(loginVO.getLoginUserVO()).orElseGet(() -> {
                LoginUserVO vo = new LoginUserVO();
                vo.setId(-1L);
                vo.setUserName("系统预置");
                return vo;
            });
        } catch (Exception e) {
            LoginUserVO vo = new LoginUserVO();
            vo.setId(-1L);
            vo.setUserName("系统预置");
            return vo;
        }
    }
}

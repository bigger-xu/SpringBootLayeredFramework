package com.efreight.hrs.model.web.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * className:HrsUserDataPermissonVO
 * Description:
 *
 * @Date:2023-11-27 10:40
 * @Author:Rocky
 */
@Getter
@Setter
@ToString
@Schema(name = "HrsUserDataPermissonVO", description = "HRS 用户 数据级权限整合")
public class HrsUserDataPermissonVO implements Serializable {

    @Schema(description = "当前登录用户所配置的数据级权限所涉及的userId 集合")
    private List<Long> userIdList;

    @Schema(description = "当前登录用户所在的工作组，如果权限没有设置工作组则集合为空")
    private List<Long> workGroupIdList;
}

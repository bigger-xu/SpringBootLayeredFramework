package com.efreight.base.model.web.req;


import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 视图表
 * </p>
 *
 * @author Zhang Yongwei
 * @since 2024-08-13
 */
@Getter
@Setter
@ToString
@Schema(name = "BaseGlobalViewReq对象", description = "视图表")
public class BaseGlobalViewReq implements Serializable {

    
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "数据库连接地址(必须是Mysql连接地址)")
    private String connectUrl;

    @Schema(description = "数据库连接名")
    private String connectUser;

    @Schema(description = "数据库连接密码")
    private String connectPassword;

    @Schema(description = "数据库：例 cargo")
    private String databaseName;

    @Schema(description = "订单类型")
    private String orderType;

    @Schema(description = "视图名称：例 v_af_order")
    private String viewName;

    @Schema(description = "视图注释")
    private String viewComment;

    @Schema(description = "SQL脚本信息")
    private String sqlDetail;

    @Schema(description = "备注")
    private String remark;
}
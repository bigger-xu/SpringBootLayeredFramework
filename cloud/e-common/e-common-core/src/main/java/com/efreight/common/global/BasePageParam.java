package com.efreight.common.global;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 基础分页数据
 * @author LB
 * @date 2022/04/15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BasePageParam extends PageParam {

    @Schema(description = "创建人ID集合")
    private List<Long> createIds;

    @Schema(description = "创建开始时间")
    private String beginDate;

    @Schema(description = "创建结束时间")
    private String endDate;
}

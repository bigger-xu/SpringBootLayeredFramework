package com.efreight.common.global;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * 全局分页请求对象
 *
 * @author LB
 * @date 2023/10/12
 */
@Data
public class PageParam {

    @Schema(description = "当前页", required = true, example = "1")
    private Integer current = 1;

    @Schema(description ="每页条数", required = true, example = "10")
    private Integer size = 10;

}

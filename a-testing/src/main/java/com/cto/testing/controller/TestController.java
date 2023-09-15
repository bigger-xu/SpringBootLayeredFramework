package com.cto.testing.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Zhang Yongwei
 * @since 2023-07-03
 */
@Api(tags = { "测试" })
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    @GetMapping("/time")
    @ApiOperation(value = "测试时间时区")
    public Object time() {
        return new Object();
    }
}

package com.cto.order.ae.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;
import com.cto.common.base.BaseController;
import com.cto.common.mq.order.model.RocketTest;
import com.cto.common.mq.order.productor.OrderMessageProducer;
import com.cto.order.ae.service.ITestService;
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
public class TestController extends BaseController {

    private final OrderMessageProducer orderMessageProducer;

    private final ITestService iTestService;

    @GetMapping("/time")
    @ApiOperation(value = "测试时间时区")
    public Object time() {
        iTestService.createTest();
        //发送mq消息
        RocketTest rocketTest = new RocketTest();
        rocketTest.setUuid(UUID.randomUUID().toString());
        orderMessageProducer.sendOrderCreateSubsequent(rocketTest);
        JSONObject result = new JSONObject();
        result.put("time", LocalDateTime.now());
        return result;
    }

}

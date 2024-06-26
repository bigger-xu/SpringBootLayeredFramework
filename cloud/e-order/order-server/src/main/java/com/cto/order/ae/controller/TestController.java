package com.cto.order.ae.controller;

import java.time.LocalDateTime;

import com.alibaba.fastjson.JSONObject;
import com.cto.common.base.BaseController;
import com.cto.common.exception.CtoBizException;
import com.cto.order.model.web.vo.TestVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@Tag(name = "测试")
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController extends BaseController {

    private final RedissonClient redissonClient;

    @GetMapping("/time")
    @Operation(summary = "测试时间时区")
    @Parameters({@Parameter(name = "id", description = "ID编号", in = ParameterIn.QUERY)})
    public void time(Long id) {
        if(id > 100){
            throw new CtoBizException("111");
        }
        System.out.println(id);
        //发送mq消息
        //RocketTest rocketTest = new RocketTest();
        //rocketTest.setUuid(UUID.randomUUID().toString());
        //orderMessageProducer.sendOrderCreateSubsequent(rocketTest);
        //JSONObject result = new JSONObject();
        //result.put("time", LocalDateTime.now());
        //return result;
    }
    
    @PostMapping("/time1")
    @Operation(summary = "测试时间时区1")
    public Object time1(@RequestBody TestVO testVO) {
        System.out.println(testVO);
        RLock lock = redissonClient.getLock("1");
        try{
            boolean b = lock.tryLock();
            if(b){
                System.out.println("1111111");
                
            }
            System.out.println("222222222222");
        }catch (Exception e){
            throw e;
        }finally {
            lock.unlock();
        }
        //iTestService.createTest();
        //发送mq消息
        //RocketTest rocketTest = new RocketTest();
        //rocketTest.setUuid(UUID.randomUUID().toString());
        //orderMessageProducer.sendOrderCreateSubsequent(rocketTest);
        JSONObject result = new JSONObject();
        result.put("time", LocalDateTime.now());
        return result;
        //return new Object();
    }

}

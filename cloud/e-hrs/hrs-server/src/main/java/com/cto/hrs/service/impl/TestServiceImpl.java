package com.cto.hrs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cto.hrs.dao.TestMapper;
import com.cto.hrs.entity.Test;
import com.cto.hrs.service.ITestService;
import com.cto.hrs.service.tcc.ITccTestService;
import com.cto.order.feign.FeignTestService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zhang Yongwei
 * @since 2023-07-03
 */
@Service
@RequiredArgsConstructor
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

    private final FeignTestService feignTestService;

    private final ITccTestService tccTestService;

    //AT强一致模式事务处理方式
    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public void createTest() {
        //1.创建一条数据
        //2.另外的服务也创建一条一样的数据
        //正确结果
        //3.不抛异常，数据库是2条数据
        //4.抛异常，数据库一条数据都没有
        Test test = new Test();
        test.setName("测试");
        test.setNameEn("test");
        this.save(test);

        feignTestService.test();

        //throw new CtoBizException("故意丢个异常，测试回滚");
    }

    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public void createTestTcc() {

        tccTestService.createTestTcc(1L);

        feignTestService.test();

        //throw new CtoBizException("故意丢个异常，测试回滚");
    }
}

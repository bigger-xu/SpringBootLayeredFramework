package com.cto.order.ae.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cto.order.ae.dao.TestMapper;
import com.cto.order.ae.entity.Test;
import com.cto.order.ae.service.ITestService;

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
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

    @Override
    public void createTest() {
        Test test = new Test();
        test.setName("测试");
        test.setNameEn("test");
        this.save(test);

        //throw new CtoBizException("主动异常测回滚");
    }
}

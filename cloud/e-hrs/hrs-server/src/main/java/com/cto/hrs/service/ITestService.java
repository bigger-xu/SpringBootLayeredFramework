package com.cto.hrs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cto.hrs.entity.Test;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Zhang Yongwei
 * @since 2023-07-03
 */
public interface ITestService extends IService<Test> {

    /**
     * 测试全局事务
     *
     * @since 2023/7/12
     */
    void createTest();

    /**
     * 测试TCC事务
     *
     * @since 2023/7/17
     */
    void createTestTcc();
}

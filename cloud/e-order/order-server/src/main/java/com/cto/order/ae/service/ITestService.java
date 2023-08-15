package com.cto.order.ae.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cto.order.ae.entity.Test;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Zhang Yongwei
 * @since 2023-07-03
 */
public interface ITestService extends IService<Test> {

    void createTest();
}

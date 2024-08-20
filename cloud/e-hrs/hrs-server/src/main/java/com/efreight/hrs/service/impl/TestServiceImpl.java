package com.efreight.hrs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.efreight.hrs.dao.TestMapper;
import com.efreight.hrs.entity.Test;
import com.efreight.hrs.service.ITestService;

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

}

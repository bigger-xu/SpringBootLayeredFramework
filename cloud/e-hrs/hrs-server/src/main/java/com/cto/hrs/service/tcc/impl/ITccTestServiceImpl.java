package com.cto.hrs.service.tcc.impl;

import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.cto.common.exception.CtoBizException;
import com.cto.hrs.dao.TestMapper;
import com.cto.hrs.entity.Test;
import com.cto.hrs.service.tcc.ITccTestService;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

/**
 * @author 张永伟
 * @since 2023/7/12
 */
@Slf4j
@Component
public class ITccTestServiceImpl implements ITccTestService {

    private final ConcurrentHashMap<String, String> syncMap = new ConcurrentHashMap<>();

    @Resource
    private TestMapper testMapper;

    @Override
    public Boolean createTestTcc(Long id) {
        Test test = new Test();
        test.setName("测试");
        //设置数据为代提交
        test.setNameEn("try");
        testMapper.insert(test);
        if (StringUtils.isNotBlank(RootContext.getXID())) {
            //此处应该放数据的唯一UUID，偷懒写ID，后续开发注意
            syncMap.put(RootContext.getXID(), test.getTestId().toString());
        }
        return true;
    }

    @Override
    public Boolean commitTcc(BusinessActionContext businessActionContext) {
        log.info(">>>>>>>>>>>>>>>>>>>>订单第二阶段事务提交,事务ID={}", businessActionContext.getXid());
        try {
            String id = syncMap.get(businessActionContext.getXid());
            Test test = testMapper.selectById(id);
            test.setNameEn("commit");
            //修改订单状态冻结为成功
            int i = testMapper.updateById(test);
            if (i > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CtoBizException("提交阶段异常");
        }
        return false;
    }

    @Override
    public Boolean rollbackTcc(BusinessActionContext businessActionContext) {
        log.error(">>>>>>>>>>>>>>>>>>>>订单第二阶段事务回滚,事务ID={}", businessActionContext.getXid());
        try {
            String id = syncMap.get(businessActionContext.getXid());
            //修改订单状态冻结为成功
            int i = testMapper.deleteById(id);
            if (i > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CtoBizException("回滚异常，此处需要编写人工补偿机制");
        }
        return false;
    }
}

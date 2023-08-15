package com.cto.hrs.service.tcc;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author 张永伟
 * @since 2023/7/12
 */
@LocalTCC
public interface ITccTestService {

    /**
     * 创建TCC测试
     *
     * @param id return Boolean
     */
    @TwoPhaseBusinessAction(name = "tccTest", commitMethod = "commitTcc", rollbackMethod = "rollbackTcc")
    Boolean createTestTcc(Long id);

    /**
     * 提交
     *
     * @param businessActionContext 上下文
     * @return Boolean
     */
    Boolean commitTcc(BusinessActionContext businessActionContext);

    /**
     * 回滚
     *
     * @param businessActionContext 上下文
     * @return Boolean
     */
    Boolean rollbackTcc(BusinessActionContext businessActionContext);
}

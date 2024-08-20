package com.efreight.common.express.operator;

import com.ql.util.express.Operator;

/**
 * 字符串长度规则
 *
 * @author 张永伟
 * @since 2023/8/23
 */
public class LengthStringOperator extends Operator {

    @Override
    public Object executeInner(Object[] list) {
        String str = (String) list[0];
        return str.length();
    }
}

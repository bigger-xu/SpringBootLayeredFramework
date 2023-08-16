package com.cto.testing.operator;

import com.ql.util.express.Operator;

public class LengthStringOperator extends Operator {

    @Override
    public Object executeInner(Object[] list) {
        String str = (String) list[0];
        return str.length();
    }
}

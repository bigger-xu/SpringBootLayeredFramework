package com.cto.testing.operator;

import com.ql.util.express.Operator;

public class SubStringOperator extends Operator {
    private final String operator;

    public SubStringOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public Object executeInner(Object[] list) {
        String str = list[0].toString();
        int length = (int) list[1];
        if ("LEFT".equals(this.operator)) {
            return str.substring(length);
        } else if ("RIGHT".equals(this.operator)) {
            return str.substring(0, str.length() - length);
        }else {
            return "";
        }
    }
}

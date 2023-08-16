package com.cto.testing.operator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ql.util.express.Operator;

/**
 * 项目名称: mhqc-ms-teacher-evaluation
 * 描述:
 * 作者: zhengyi.wang
 * 日期: 2022-01-25
 */
public class AvgOperator extends Operator {

    @Override
    public Object executeInner(Object[] objects) {
        List<Number> list = new ArrayList<>();
        for (Object obj : objects) {
            if (obj instanceof Collection) {
                list.addAll((Collection<? extends Number>) obj);
            } else {
                list.add((Number) obj);
            }
        }

        return list.stream().mapToDouble(Number::doubleValue).average().orElse(0);
    }

}

package com.efreight.common.express.operator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ql.util.express.Operator;

/**
 * 求和规则
 *
 * @author 张永伟
 * @since 2023/8/15
 */
public class SumOperator extends Operator {
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
        return list.stream().mapToDouble(Number::doubleValue).sum();
    }
}

package com.efreight.common.express.operator;

import java.math.BigDecimal;
import java.util.Objects;

import com.ql.util.express.Operator;
import org.apache.commons.lang3.StringUtils;

/**
 * 杂费计算
 *
 * @author 张永伟
 * @since 2024/3/27
 */
public class OperatorMiscNumerate extends Operator {
    /** 单价 */
    private static final String PRICE = "PRICE";
    /** 总价 */
    private static final String AMOUNT = "AMOUNT";
    /** 数量 */
    private static final String NUM = "NUM";
    private final String operator;

    public OperatorMiscNumerate(String operator) {
        this.operator = operator;
    }

    @Override
    public Object executeInner(Object[] list) {
        if(list == null || list.length == 0){
            return null;
        }
        if(Objects.isNull(list[0]) || Objects.isNull(list[1])){
            return null;
        }
        String str = list[0].toString();
        String name = list[1].toString();
        //两层判空逻辑
        if(StringUtils.isBlank(str) || StringUtils.isBlank(name)){
            return null;
        }
        return executeInner(str, name);
    }
    
    private Object executeInner(String str, String name) {
        String[] dataArray = str.split("\n");
        for (String s : dataArray) {
            String[] value = s.split("\\*");
            if(value.length < 4){
                continue;
            }
            if(name.equals(value[0])) {
                switch (operator) {
                    case NUM:
                        return Integer.valueOf(value[2]);
                    case AMOUNT:
                        return new BigDecimal(value[3]);
                    case PRICE:
                        return new BigDecimal(value[1]);
                    default:
                        break;
                }
            }
        }
        return null;
    }
}

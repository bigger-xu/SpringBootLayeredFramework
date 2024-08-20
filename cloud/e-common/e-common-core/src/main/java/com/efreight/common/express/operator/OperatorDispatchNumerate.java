package com.efreight.common.express.operator;

import java.math.BigDecimal;
import java.util.Objects;

import com.ql.util.express.Operator;
import org.apache.commons.lang3.StringUtils;

/**
 * 派车单计算器
 *
 * @author 张永伟
 * @since 2024/3/27
 */
public class OperatorDispatchNumerate extends Operator {
    /** 单价 */
    private static final String PRICE = "PRICE";
    /** 金额 */
    private static final String AMOUNT = "AMOUNT";
    /** 车队名 */
    private static final String CAR_NAME = "CAR_NAME";
    /** 车队类型 */
    private static final String CAR_TYPE = "CAR_TYPE";
    
    /** 毛重 */
    private static final String WEIGHT = "WEIGHT";
    /** 自营 */
    private static final String SELF_SUPPORT = "SELF_SUPPORT";
    /** 内订 */
    private static final String INTERNAL_BOOKING = "INTERNAL_BOOKING";
    /** 外订 */
    private static final String OUTSOURCING = "OUTSOURCING";

    private final String operator;

    public OperatorDispatchNumerate(String operator) {
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
        String[] dataArray = str.split(";");
        for (String s : dataArray) {
            String[] value = s.split(",");
            if(value.length < 5){
                continue;
            }
            if(name.equals(value[0])) {
                switch (operator) {
                    case CAR_NAME:
                        return String.valueOf(value[1]);
                    case CAR_TYPE:
                        switch (value[2]){
                            case SELF_SUPPORT:
                                return "自营";
                            case INTERNAL_BOOKING:
                                return "内订";
                            case OUTSOURCING:
                                return "外订";
                        }
                    case PRICE:
                        if("UNIT".equals(value[4])){
                            return new BigDecimal(value[3]);
                        }else{
                            return null;
                        }
                    case AMOUNT:
                        if("UNIT".equals(value[4])){
                            return null;
                        }else{
                            return new BigDecimal(value[3]);
                        }
                    case WEIGHT:
                        return new BigDecimal(value[5]);
                    default:
                        break;
                }
            }
        }
        return null;
    }
}

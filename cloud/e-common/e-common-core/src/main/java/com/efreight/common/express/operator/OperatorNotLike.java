package com.efreight.common.express.operator;

import java.util.ArrayList;

import com.ql.util.express.Operator;

/**
 * NOT LIKE 不包含
 * @author ZhangYongWei
 * @since 2024/3/25
 */
public class OperatorNotLike extends Operator {
    
    public OperatorNotLike(String name) {
        this.name = name;
    }
    
    @Override
    public Object executeInner(Object[] list) {
        return executeInner(list[0], list[1]);
    }
    
    public Object executeInner(Object op1, Object op2) {
        boolean result = true;
        String s1 = op1.toString();
        String s2 = op2.toString();
        if (s2.contains("%")) {
            String[] list = split(s2, "%");
            for (String s : list) {
                int index = s1.indexOf(s);
                if (index >= 0) {
                    result = false;
                    break;
                }
            }
        } else {
            result = s1.equals(s2);
        }
        return result;
    }
    
    public String[] split(String str, String s) {
        int start = 0;
        int end;
        String tmpStr;
        ArrayList<String> list = new ArrayList<>();
        do {
            end = str.indexOf(s, start);
            if (end < 0) {
                tmpStr = str.substring(start);
            } else {
                tmpStr = str.substring(start, end);
            }
            if (!tmpStr.isEmpty()) {
                list.add(tmpStr);
            }
            start = end + 1;
            if (start >= str.length()) {
                break;
            }
        } while (end >= 0);
        return list.toArray(new String[0]);
    }
}

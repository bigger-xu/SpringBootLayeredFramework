package com.cto.common.utils;

/**
 * 内部识别号生成工具
 * <p>
 * Company: 翌飞锐特
 * <p>
 *
 * @author liyulong
 * @version 1.0.0
 **/
public class InternalNumberUtils {
    public static String create(int length, int orderMaxSerialNo, String prefix) {
        return prefix + minNumString(length - String.valueOf(orderMaxSerialNo).length()) + orderMaxSerialNo;
    }

    public static String minNumString(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(0);
        }
        return stringBuilder.toString();
    }

    public static int maxNumber(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        if (length == 0) {
            stringBuilder.append(0);
        }
        for (int i = 0; i < length; i++) {
            stringBuilder.append(9);
        }
        return Integer.valueOf(stringBuilder.toString());
    }
}

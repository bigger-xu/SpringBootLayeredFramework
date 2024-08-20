package com.efreight.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.efreight.common.enums.FeeFormulaEnum.RoundType;
import com.efreight.common.enums.FeeFormulaEnum.Scale;
import org.jetbrains.annotations.NotNull;

/**
 * 扩展科学计算工具类
 *
 * @author 张永伟
 * @since 2023/8/23
 */
public class EfMathRoundingUtils {
    
    /**
     * 执行科学计算方法
     *
     * @param sourceValue 需要计算的数据
     * @param roundType   进位方式
     * @param scale       保留位数
     * @return BigDecimal
     * @since 2023/8/23
     */
    public static BigDecimal execute(BigDecimal sourceValue, RoundType roundType, Scale scale) {
        if (Scale.INTEGER.getKey().equals(scale.getKey())) {
            if (RoundType.UP.equals(roundType)) {
                return sourceValue.setScale(0, RoundingMode.UP);
            } else if (RoundType.DOWN.equals(roundType)) {
                return sourceValue.setScale(0, RoundingMode.DOWN);
            } else {
                return sourceValue.setScale(0, RoundingMode.HALF_UP);
            }
        } else if (Scale.ROUND_ONE.getKey().equals(scale.getKey())) {
            if (RoundType.UP.equals(roundType)) {
                return sourceValue.setScale(1, RoundingMode.UP);
            } else if (RoundType.DOWN.equals(roundType)) {
                return sourceValue.setScale(1, RoundingMode.DOWN);
            } else {
                return sourceValue.setScale(1, RoundingMode.HALF_UP);
            }
        } else if (Scale.ROUND_TWO.getKey().equals(scale.getKey())) {
            if (RoundType.UP.equals(roundType)) {
                return sourceValue.setScale(2, RoundingMode.UP);
            } else if (RoundType.DOWN.equals(roundType)) {
                return sourceValue.setScale(2, RoundingMode.DOWN);
            } else {
                return sourceValue.setScale(2, RoundingMode.HALF_UP);
            }
        } else if (Scale.ROUND_FIVE.getKey().equals(scale.getKey())) {
            if (RoundType.UP.equals(roundType)) {
                String value = getRoundOrUpData(sourceValue, RoundingMode.UP);
                return new BigDecimal(value);
            } else if (RoundType.ROUND.equals(roundType)) {
                String value = getRoundOrUpData(sourceValue, RoundingMode.HALF_UP);
                return new BigDecimal(value);
            } else {
                String value = getDownData(sourceValue);
                return new BigDecimal(value);
            }
        } else {
            return BigDecimal.ZERO;
        }
    }
    
    /**
     * .5向上进位或者四舍五入
     *
     * @param sourceValue 处理的值
     * @param up          进位方式
     * @return String
     * @since 2024/8/12
     */
    private static @NotNull String getRoundOrUpData(BigDecimal sourceValue, RoundingMode up) {
        BigDecimal setScale = sourceValue.setScale(1, up);
        String stringScale = setScale.toString();
        int round = Integer.parseInt(stringScale.split("\\.")[0]);
        int nu = Integer.parseInt(stringScale.substring(stringScale.length() - 1));
        String value;
        if (nu != 0) {
            if (nu > 5) {
                value = round + 1 + ".0";
            } else {
                value = round + ".5";
            }
        } else {
            value = round + "";
        }
        return value;
    }
    
    /**
     * .5向下进位
     *
     * @param sourceValue 处理的值
     * @return String
     * @since 2024/8/12
     */
    private static @NotNull String getDownData(BigDecimal sourceValue) {
        BigDecimal setScale = sourceValue.setScale(1, RoundingMode.DOWN);
        String stringScale = setScale.toString();
        int round = Integer.parseInt(stringScale.split("\\.")[0]);
        int nu = Integer.parseInt(stringScale.substring(stringScale.length() - 1));
        String value;
        if (nu != 0) {
            if (nu >= 5) {
                value = round + ".5";
            } else {
                value = round + ".0";
            }
        } else {
            value = round + "";
        }
        return value;
    }
}

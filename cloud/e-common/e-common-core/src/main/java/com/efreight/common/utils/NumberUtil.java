package com.efreight.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.concurrent.ThreadLocalRandom;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Libiao
 */
public class NumberUtil {

    public static final String ZERO = "0";

    /**
     * 生成流水号
     *
     * @param length   数字位数
     * @param startNum 起始数字
     * @param prefix   前缀
     * @return str
     */
    public static String create(int length, int startNum, String prefix) {
        return prefix + minNumString(length - String.valueOf(startNum).length()) + startNum;
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
        return Integer.parseInt(stringBuilder.toString());
    }

    /**
     * 年月日流水号
     *
     * @return String
     * @since 2023/8/24
     */
    public static String getDateRandomNumber() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String date = format.format(new Date());
        int randomNum = ThreadLocalRandom.current().nextInt(1000, 10000);
        return date + randomNum;
    }

    /**
     * 年月日时分秒流水号
     * @return
     */
    public static String getDateTimeRandomNumber() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String date = format.format(new Date());
        int randomNum = ThreadLocalRandom.current().nextInt(1000, 10000);
        return date + randomNum;
    }
    /**
     * description: 格式化数字，实现左侧补 0
     *
     * @param num 格式化的数字
     * @param min 最小位数
     * @param max 最大位数
     * @return String
     */
    public static String fill(int num, int min, int max) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 禁用数字格式化分组。 如：  000,001
        numberFormat.setGroupingUsed(false);
        // 保留最小位数
        numberFormat.setMinimumIntegerDigits(min);
        // 保留最大位数
        numberFormat.setMaximumIntegerDigits(max);
        return numberFormat.format(num);
    }

    /**
     * 将数字增加千位符
     *
     * @param num 数字的字符串
     * @return 转换后带千位符的字符串
     */
    public static String thousandSymbol(String num) {
        if (Objects.isNull(num)) {
            return null;
        }
        // 判断是否有小数
        int index = num.indexOf(".");
        if (index >= 0) {
            String integer = num.substring(0, index);
            String decimal = num.substring(index);
            // 分隔后的整数+小数拼接起来
            return addSeparator(integer) + decimal;
        } else {
            return addSeparator(num);
        }
    }

    /**
     * 添加分隔符
     *
     * @param num 原字符串
     * @return str
     */
    private static String addSeparator(String num) {
        int length = num.length();
        List<String> list = Lists.newArrayList();
        while (length > 3) {
            list.add(num.substring(length - 3, length));
            length = length - 3;
        }
        // 将前面小于三位的数字添加到ArrayList中
        list.add(num.substring(0, length));
        StringJoiner sj = new StringJoiner(",");
        // 倒序拼接
        for (int i = list.size() - 1; i > 0; i--) {
            sj.add(list.get(i));
        }
        sj.add(list.get(0));
        return sj.toString();
    }


    /**
     * 计算密度   毛重/体积
     *
     * @param weight 毛重
     * @param volume 体积
     * @return void
     * @since 2023/11/24
     */
    public static BigDecimal getDensity(BigDecimal weight, BigDecimal volume) {
        if(volume==null || BigDecimal.ZERO.compareTo(volume) == 0){
            return BigDecimal.ZERO;
        }
        if(weight==null || BigDecimal.ZERO.compareTo(weight) == 0){
            return BigDecimal.ZERO;
        }
        return weight.divide(volume, 0, RoundingMode.DOWN);
    }

    /**
     * 计费重量  取净毛重和体积重量的大者
     * @param weight  毛重
     * @param volumeWeight 体积重量
     * @author 张永伟
     * @since 2023/11/24
     * @return BigDecimal
     */
    public static BigDecimal getWeight(BigDecimal weight, BigDecimal volumeWeight) {
        if(weight == null){
            return volumeWeight;
        }
        if(weight.compareTo(volumeWeight) > 0){
            return weight;
        }
        return volumeWeight;
    }

    /**
     * 体积重量
     * @param volume
     * @author 张永伟
     * @since 2023/11/24
     * @return BigDecimal
     */
    public static BigDecimal getVolumeWeight(BigDecimal volume, Integer scale) {
        if (volume == null) {
            return BigDecimal.ZERO;
        }
        if(scale == null){
            scale = 0;
        }
        return volume.multiply(BigDecimal.valueOf(1000000)).divide(BigDecimal.valueOf(6000), scale , RoundingMode.HALF_UP);
    }

    /**
     * 根据尺寸算体积
     * @param cargoDimensions
     * @return
     */
    public static  BigDecimal computeVolume(String cargoDimensions){
        BigDecimal volumeTotal = BigDecimal.ZERO;
        BigDecimal oneHundred = BigDecimal.valueOf(100);
        try{
            if(StringUtils.isNotEmpty(cargoDimensions)){
                String[] sizes = cargoDimensions.split(";");
                for(String size : sizes){
                    String[] temp = size.split("/");
                    if(temp.length ==2){
                        BigDecimal packages = new BigDecimal( temp[1] );
                        String[] sizeArr = temp[0].split("\\*");
                        if(sizeArr.length == 3){
                            BigDecimal length = new BigDecimal( sizeArr[0] ).divide(oneHundred);
                            BigDecimal width = new BigDecimal( sizeArr[1] ).divide(oneHundred);
                            BigDecimal height = new BigDecimal( sizeArr[2] ).divide(oneHundred);
                            //体积 = 长*宽*高*件数
                            BigDecimal volume = length.multiply(width).multiply(height).multiply(packages);
                            volumeTotal = volumeTotal.add(volume);
                        }
                    }
                }
            }
        }catch (Exception e){
        }
        return volumeTotal;
    }
}

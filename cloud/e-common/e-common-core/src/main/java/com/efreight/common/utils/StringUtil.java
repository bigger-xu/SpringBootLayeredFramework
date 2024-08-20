package com.efreight.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 字符串工具类
 *
 * @author 张永伟
 * @since 2023/9/19
 */
public class StringUtil {
    
    public static final String DEFAULT_TAGS_START = "###";
    
    public static final String DEFAULT_TAGS_END = "###";
    
    public static final String BLANK = " ";
    
    
    /**
     * 描述：获取字符串中被两个字符（串）包含的所有数据
     *
     * @param str       处理字符串
     * @param start     起始字符（串）
     * @param end       结束字符（串）
     * @param isSpecial 起始和结束字符是否是特殊字符
     * @param isTag     输出是否带上标签
     * @return List<String>
     */
    public static List<String> getStrContainData(String str, String start, String end, boolean isSpecial, boolean isTag) {
        if (isSpecial) {
            start = String.format("\\%s", start);
            end = String.format("\\%s", end);
        }
        Set<String> strings = matcherTags(str, start, end, isTag);
        return new ArrayList<>(strings);
    }
    
    /**
     * 描述：获取字符串中被两个字符（串）包含的所有数据
     *
     * @param str 处理字符串
     * @return List<String>
     */
    public static List<String> getStrContainData(String str) {
        Set<String> strings =  matcherTags(str, DEFAULT_TAGS_START, DEFAULT_TAGS_END, true);
        return new ArrayList<>(strings);
    }
    
    /**
     * 描述：获取字符串中被两个字符（串）包含的所有数据
     *
     * @param str    处理字符串
     * @param start  起始字符（串）
     * @param end    结束字符（串）
     * @param isTag  输出是否带上标签
     * @since 2023/9/19
     */
    private static Set<String> matcherTags(String str, String start, String end, boolean isTag) {
        Set<String> set = new HashSet<>();
        String regex = start + "(.*?)" + end;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            String key = matcher.group(1);
            if (!key.contains(start) && !key.contains(end)) {
                if (isTag) {
                    set.add(start + key + end);
                } else {
                    set.add(key);
                }
            }
        }
        return set;
    }

    /**
     * * 判断一个对象数组是否非空
     *
     * @param objects 要判断的对象数组
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Object[] objects)
    {
        return !isEmpty(objects);
    }

    /**
     * * 判断一个对象数组是否为空
     *
     * @param objects 要判断的对象数组
     ** @return true：为空 false：非空
     */
    public static boolean isEmpty(Object[] objects)
    {
        return isNull(objects) || (objects.length == 0);
    }
    /**
     * 判断字符串是否为空
     *
     * @param str 字符值
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        } else {
            return str.trim().length() == 0;
        }
    }
    /**
     * * 判断一个对象是否为空
     *
     * @param object Object
     * @return true：为空 false：非空
     */
    public static boolean isNull(Object object)
    {
        return object == null;
    }

    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == "_".charAt(0)) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String incrLetter(String letter) {
        char cha = letter.charAt(0);
        return String.valueOf((char) (cha + 1));
    }

    public static String reduceLetter(String letter) {
        char cha = letter.charAt(0);
        return String.valueOf((char) (cha - 1));
    }

    /**
     * @author shihongkai
     * @description 字符串保留英文、数字、普通空格或点
     */
    public static String formatStr(String str, Integer size) {
        // 使用正则表达式过滤字符串，只保留数字、字母和空格
        String filteredString;
        StringBuilder filteredStringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int hash = Character.hashCode(c);

            // 判断哈希值是否对应英文、数字、普通空格
            if ((hash >= 65 && hash <= 90) || (hash >= 97 && hash <= 122) || (hash >= 48 && hash <= 57) || hash == 32) {
                filteredStringBuilder.append(c);
            }
        }
        // 限制字符串长度
        if (size != null && filteredStringBuilder.length() > size) {
            filteredString = filteredStringBuilder.substring(0, size);
        }else {
            filteredString = filteredStringBuilder.toString();
        }
        return filteredString;
    }
    /**
     * @author shihongkai
     * @date 2024/3/27 13:52
     * @parameters null
     * @return
     * @version 1.0
     * @description
     */
    public static String formatStrTwo(String str) {
        if (StringUtils.isNotEmpty(str) && str.length() > 3){
            str = str.substring(0,3);
        }
        return str;
    }
    
    /**
     * 字符串逗号分割去重
     * @param str  字符串
     * @author ZhangYongWei
     * @since 2024/6/7
     * @return String
     */
    public static String deduplicationStr(String str){
        if(StringUtils.isNotEmpty(str)) {
            String[] items = str.split(",");
            Set<String> uniqueItems = new HashSet<>(Arrays.asList(items));
            StringBuilder output = new StringBuilder();
            for (String item : uniqueItems) {
                if (output.length() > 0) {
                    output.append(",");
                }
                output.append(item);
            }
            return output.toString();
        }
        return null;
    }
    
    public static String parseOrderId(String input) {
        // 找到等号的位置
        int equalSignIndex = input.indexOf("=");
        
        // 如果等号存在，从等号后面的位置开始提取
        if (equalSignIndex != -1 && equalSignIndex < input.length() - 1) {
            // 获取等号后面的子字符串
            String afterEqual = input.substring(equalSignIndex + 1).trim();
            
            // 删除子字符串中的非数字字符
            StringBuilder orderIdBuilder = new StringBuilder();
            for (char c : afterEqual.toCharArray()) {
                if (Character.isDigit(c)) {
                    orderIdBuilder.append(c);
                } else {
                    // 遇到非数字字符时停止循环
                    break;
                }
            }
            // 返回提取的订单 ID
            return orderIdBuilder.toString();
        }
        
        // 如果没有找到等号或者等号后面没有数字，返回空字符串
        return null;
    }
    /**
     * @author shihongkai
     * @date 2024/6/27 10:58
     * @parameters null
     * @return
     * @version 1.0
     * @description 正则匹配括号中的字符串
     */

    public static String parseStr(String input) {
        // 正则表达式匹配第一个括号中的值，包括空括号
        Pattern pattern = Pattern.compile("\\(([^)]*)\\)");
        Matcher matcher = pattern.matcher(input);

        // 只寻找第一个匹配项
        if (matcher.find()) {
            // 返回第一个括号中的值，如果括号是空的，则返回空字符串
            return matcher.group(1);
        }
        // 如果没有找到括号，也返回空字符串
        return "";
    }
}

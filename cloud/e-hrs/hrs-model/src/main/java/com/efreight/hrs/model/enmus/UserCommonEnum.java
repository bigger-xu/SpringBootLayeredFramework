package com.efreight.hrs.model.enmus;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Maps;

/**
 * @author 马玉龙
 * @since 2023/9/5
 */
public enum UserCommonEnum {
    /**
     * 全部
     */
//    ALL(1, "ALL", "全部"),
    /**
     * 个人
     */
    SELF(1, "SELF", "个人"),

    /**
     * 工作组
     */
    GROUP(1, "GROUP", "工作组"),
    DEPT(1, "DEPT", "部门"),
    NOMAl_SHOW(2, "NOMAl_SHOW", "常规可见"),
    HIDDEN_DETAIL(2, "HIDDEN_DETAIL", "隐藏明细"),
    ALL_HIDDEN(2, "ALL_HIDDEN", "全部隐藏"),
    /**
     * 个人设置订单查询日期枚举
     */
    CURRENT_DATE(3,"CURRENT_DATE","当前日期"),
    CURRENT_WEEK_FIRST(3,"CURRENT_WEEK_FIRST","本周第一天"),
    LAST_WEEK_FIRST(3,"LAST_WEEK_FIRST","上周第一天"),
    CURRENT_MONTH_FIRST(3,"CURRENT_MONTH_FIRST","本月第一天"),
    LAST_MONTH_FIRST(3,"LAST_MONTH_FIRST","上月第一天"),
    CURRENT_YEAR_FIRST(3,"CURRENT_YEAR_FIRST","本年第一天");


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private int code;
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    UserCommonEnum(int code, String key, String value) {
        this.code = code;
        this.key = key;
        this.value = value;
    }

    public static List<Map<String, String>> getLevelList() {
        return Arrays.stream(UserCommonEnum.values()).filter(userCommonEnum -> userCommonEnum.getCode() == 2).map(userCommonEnum -> {
            Map<String, String> map = Maps.newHashMap();
            map.put("k", userCommonEnum.getKey());
            map.put("v", userCommonEnum.getValue());
            return map;
        }).collect(Collectors.toList());
    }

    public static List<Map<String, String>> getDataPermissionList() {
        return Arrays.stream(UserCommonEnum.values()).filter(userCommonEnum -> userCommonEnum.getCode() == 1).map(userCommonEnum -> {
            Map<String, String> map = Maps.newHashMap();
            map.put("k", userCommonEnum.getKey());
            map.put("v", userCommonEnum.getValue());
            return map;
        }).collect(Collectors.toList());
    }
    public static List<Map<String, String>> getOrderDefaultDate() {
        return Arrays.stream(UserCommonEnum.values()).filter(userCommonEnum -> userCommonEnum.getCode() == 3).map(userCommonEnum -> {
            Map<String, String> map = Maps.newHashMap();
            map.put("k", userCommonEnum.getKey());
            map.put("v", userCommonEnum.getValue());
            return map;
        }).collect(Collectors.toList());
    }

}

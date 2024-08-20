package com.efreight.common.utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;

/**
 * 通过新旧数据对比修改了哪些字段工具类，用于记录数据变动日志
 *
 * @author ZhangYongWei
 * @since 2024/8/12
 */
@Slf4j
public class FindDifferencesUtil {
    
    /**
     * 通过将对象转换json方式，对比数据差异来显示变动记录
     * 支持对象内有对象或者List多级嵌套
     *
     * @param oldData 旧数据
     * @param newData 新数据
     * @return String   公司名称：华贸->华贸翌飞
     * 币种：USD->CNY
     * @since 2024/4/30
     */
    public static String findDifferencesWithJsonObject(Object oldData, Object newData) {
        StringBuilder result = new StringBuilder();
        String jsonString = JsonCompareUtils.compareJsonObject(JSONObject.toJSONString(oldData), JSONObject.toJSONString(newData));
        Map<String, Object> differenceData = new LinkedHashMap<>();
        JsonCompareUtils.analysisJson(JSONObject.parseObject(jsonString), differenceData, "");
        for (Entry<String, Object> entry : differenceData.entrySet()) {
            result.append(entry.getKey().replace(".value", "")).append(":").append(entry.getValue()).append(";");
        }
        return result.toString();
    }
    
}


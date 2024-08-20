package com.efreight.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

/**
 * 尺寸信息加减操作
 *
 * @author ZhangYongWei
 * @since 2024/1/11
 */
public class DimensionsUtils {
    
    /**
     * 相同尺寸追加
     *
     * @param sourceDimensions 订单源尺寸
     * @param newDimensions    新追加的尺寸
     * @return String
     * @since 2024/1/11
     */
    public static String addDimensions(String sourceDimensions, String newDimensions) {
        Map<String, Integer> map = new HashMap<>();
        //将源尺寸根据斜杠放进map
        if (StringUtils.isNotBlank(sourceDimensions)) {
            for (String s : sourceDimensions.split(";")) {
                if (StringUtils.isNotEmpty(s)) {
                    String[] dimArray = s.split("/");
                    map.merge(dimArray[0], Integer.parseInt(dimArray[1]), Integer::sum);
                }
            }
        }
        if (StringUtils.isNotBlank(newDimensions)) {
            //遍历新尺寸，根据map的key匹配，如果匹配上了代表源尺寸有这样的尺寸，将件数加起来，否则追加
            for (String s : newDimensions.split(";")) {
                if (StringUtils.isNotEmpty(s)) {
                    String[] dimArray = s.split("/");
                    if (map.get(dimArray[0]) == null) {
                        map.put(dimArray[0], Integer.valueOf(dimArray[1]));
                    } else {
                        map.put(dimArray[0], map.get(dimArray[0]) + Integer.parseInt(dimArray[1]));
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Entry<String, Integer> entry : map.entrySet()) {
            sb.append(entry.getKey()).append("/").append(entry.getValue()).append(";");
        }
        if (sb.length() > 0) {
            return sb.substring(0, sb.length() - 1);
        }
        return null;
    }
    
    
    /**
     * 相同尺寸相减
     *
     * @param sourceDimensions 订单源尺寸
     * @param newDimensions    要减去的尺寸
     * @return String
     * @since 2024/1/11
     */
    public static String subDimensions(String sourceDimensions, String newDimensions) {
        //如果源尺寸为空，返回新尺寸
        if (StringUtils.isBlank(sourceDimensions)) {
            return null;
        }
        Map<String, Integer> map = new HashMap<>();
        //将源尺寸根据斜杠放进map
        for (String s : sourceDimensions.split(";")) {
            String[] dimArray = s.split("/");
            map.merge(dimArray[0], Integer.parseInt(dimArray[1]), Integer::sum);
        }
        //遍历新尺寸，根据map的key匹配，如果匹配上了代表源尺寸有这样的尺寸，剪掉
        if (StringUtils.isNotBlank(newDimensions)) {
            for (String s : newDimensions.split(";")) {
                String[] dimArray = s.split("/");
                if (map.get(dimArray[0]) != null) {
                    map.put(dimArray[0], map.get(dimArray[0]) - Integer.parseInt(dimArray[1]));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Entry<String, Integer> entry : map.entrySet()) {
            //value为0说明没有这个尺寸了，不返回
            if (entry.getValue() > 0) {
                sb.append(entry.getKey()).append("/").append(entry.getValue()).append(";");
            }
        }
        if (sb.length() > 0) {
            return sb.substring(0, sb.length() - 1);
        }
        return null;
    }
    
    /*public static void main(String[] args) {
        String s = addDimensions("45*34*33/2;34*55*55/3;45*56*34/2;45*34*33/10;", "45*34*33/2;34*55*55/3;45*34*33/2");
        System.out.println(s);
    }*/
    
}

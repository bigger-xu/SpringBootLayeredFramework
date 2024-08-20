package com.efreight.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 报文里面的特殊字符替换工具类
 *
 * @author 张永伟
 * @since 2023/5/12
 */
@Slf4j
public class GlobalSpecialUtils {

    public static final String REGEX = "[　_`°º~!@#$%^&*+={}':;·<>《》/?！￥…（）—【】’‘；：”“\"\\\\。，、|？]|[|]";

    public static final String REGEX_NAME = "[ ().-]";

    public static final String REGEX_CODE = "[()]";

    /**
     * 替换全局特殊字符串
     *
     * @param str 源数据
     * @return String 过滤后的数据
     */
    public static String checkSpecialCharFilter(String str, String specialType) {
        if (StringUtils.isNotBlank(str)) {
            Pattern p = Pattern.compile(REGEX);
            Matcher matcher = p.matcher(str);
            String result = matcher.replaceAll("").trim();
            if (StringUtils.isNotBlank(specialType)) {
                if (specialType.equals(SpecialType.REGEX_NAME)) {
                    Pattern child = Pattern.compile(REGEX_NAME);
                    Matcher childMatcher = child.matcher(result);
                    return childMatcher.replaceAll("").trim();
                } else {
                    Pattern child = Pattern.compile(REGEX_CODE);
                    Matcher childMatcher = child.matcher(result);
                    return childMatcher.replaceAll("").trim();
                }
            }
            return result;
        }
        return str;
    }

    /**
     * 替换字符并指定长度
     *
     * @param str       源字符串
     * @param subString 长度
     * @return String
     * @since 2023/5/12
     */
    public static String checkSpecialCharFilter(String str, String specialType, Integer subString) {
        String source = checkSpecialCharFilter(str, specialType);
        if (StringUtils.isNotBlank(source) && subString != null && source.length() > subString) {
            return source.substring(0, subString);
        }
        return source;
    }

    public static class SpecialType {
        public static final String REGEX_NAME = "REGEX_NAME";

        public static final String REGEX_CODE = "REGEX_CODE";
    }
}

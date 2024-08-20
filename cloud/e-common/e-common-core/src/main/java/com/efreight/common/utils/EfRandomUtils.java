package com.efreight.common.utils;

import java.time.LocalDateTime;

import cn.hutool.core.util.RandomUtil;
import com.efreight.common.constants.CommonConstant;
import org.slf4j.MDC;

/**
 * @author ZhangYongWei
 * @since 2024/1/9
 */
public class EfRandomUtils {

    /**
     * 获取当前请求的RequestId
     *
     * @return string
     */
    public static String currentRequestId() {
        return MDC.get(CommonConstant.REQUEST_ID);
    }
    
    /**
     * 获取5位随机数的请求时间戳
     *
     * @return String
     * @since 2024/1/9
     */
    public static String randomRequestId() {
        return randomRequestId(10000, 99999);
    }
    
    /**
     * 获取有大小限制位随机数的请求时间戳
     *
     * @param min 最小数
     * @param max 最大数
     * @return String
     * @since 2024/1/9
     */
    public static String randomRequestId(long min, long max) {
        return DateTimeUtil.format(LocalDateTime.now(), "yyyyMMddHHmmssSSS") + RandomUtil.randomLong(min, max);
    }
}

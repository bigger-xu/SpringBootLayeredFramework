package com.cto.testing.config.i18n;

import java.util.TimeZone;

/**
 * 用于传递时区上下文
 *
 * @author 张永伟
 * @since 2023/7/4
 */
public class TimeZoneContext {

    private static final ThreadLocal<TimeZone> TIME_ZONE_LOCAL = new ThreadLocal<>();

    public static TimeZone getContext() {
        return TIME_ZONE_LOCAL.get();
    }

    public static void setContext(TimeZone context) {
        TIME_ZONE_LOCAL.set(context);
    }

    /**
     * 防止内存溢出，用完就删
     * @author 张永伟
     * @since 2023/7/18
     */
    public static void remove() {
        TIME_ZONE_LOCAL.remove();
    }

}

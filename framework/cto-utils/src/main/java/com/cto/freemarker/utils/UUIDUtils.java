package com.cto.freemarker.utils;

import java.util.UUID;

import org.springframework.util.StringUtils;

/**
 * @author Zhang Yongwei
 * @version 1.0
 * @date 2019-03-27
 */
public class UUIDUtils {
    public UUIDUtils() {
    }

    public static String getRandom(int len) {
        String uuid = UUID.randomUUID().toString();
        uuid = StringUtils.replace(uuid, "-", "");
        int realLen = uuid.length();
        return realLen > len ? uuid.substring(uuid.length() - len) : uuid;
    }
}

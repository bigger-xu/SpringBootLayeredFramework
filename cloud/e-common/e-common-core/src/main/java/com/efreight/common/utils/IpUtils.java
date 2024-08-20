package com.efreight.common.utils;

import java.net.InetSocketAddress;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import org.springframework.http.server.reactive.ServerHttpRequest;


/**
 * 获取IP工具类
 *
 * @author 张永伟
 * @since 2023/7/6
 */
@Slf4j
public class IpUtils {

    private static final String UNKNOWN = "unknown";

    private static final String SEPARATOR = ",";

    private static final String HEADER_X_FORWARDED_FOR = "x-forwarded-for";

    private static final String HEADER_PROXY_CLIENT_IP = "Proxy-Client-IP";

    private static final String HEADER_WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";

    /**
     * 获取真实客户端IP
     *
     * @param request ServerHttpRequest
     * @return IP
     */
    public static String getRealIpAddress(ServerHttpRequest request) {
        String ipAddress;
        try {
            // 1.根据常见的代理服务器转发的请求ip存放协议，从请求头获取原始请求ip。值类似于203.98.182.163, 203.98.182.163
            ipAddress = request.getHeaders().getFirst(HEADER_X_FORWARDED_FOR);
            if (StringUtils.isEmpty(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeaders().getFirst(HEADER_PROXY_CLIENT_IP);
            }
            if (StringUtils.isEmpty(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeaders().getFirst(HEADER_WL_PROXY_CLIENT_IP);
            }
            // 2.如果没有转发的ip，则取当前通信的请求端的ip
            if (StringUtils.isEmpty(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                InetSocketAddress inetSocketAddress = request.getRemoteAddress();
                if (inetSocketAddress != null) {
                    ipAddress = inetSocketAddress.getAddress().getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割拿第一个
            if (ipAddress != null) {
                ipAddress = ipAddress.split(SEPARATOR)[0].trim();
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress == null ? "" : ipAddress;
    }
}

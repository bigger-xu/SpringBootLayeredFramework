package com.efreight.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * IO工具类
 *
 * @author LB
 */
public class IoUtil extends cn.hutool.core.io.IoUtil {

    /**
     * 获取 HttpServletRequest
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    /**
     * 获取 HttpServletResponse
     *
     * @return HttpServletResponse
     */
    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
    }

    /**
     * 获取文件输入流
     *
     * @param fileUrl   minio文件地址
     * @return  InputStream 文件流
     */
    @SneakyThrows(IOException.class)
    public static InputStream getInputStream(String fileUrl) {
        URL url = new URL(fileUrl);
        return url.openStream();
    }

    @SneakyThrows(IOException.class)
    public static ServletOutputStream getServletOutputStream(String fileName) {
        HttpServletResponse response = getResponse();
        response.setContentType("application/x-download");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        return response.getOutputStream();
    }

}

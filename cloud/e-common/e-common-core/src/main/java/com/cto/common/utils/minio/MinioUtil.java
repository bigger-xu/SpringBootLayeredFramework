package com.cto.common.utils.minio;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDate;

import cn.hutool.core.lang.UUID;
import com.cto.common.constants.CommonResultCode;
import com.cto.common.utils.BizExceptionCheckUtils;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传工具类
 *
 * @author 张永伟
 * @since 2023/7/5
 */
@Component
@RequiredArgsConstructor
public class MinioUtil {
    private final MinIoClientConfig minIoClientConfig;

    /**
     * 获取文件临时外链
     *
     * @param filePath 文件访问路径
     * @param expires  过期时间 <=7
     * @return url
     */
    public String getObjectUrl(String filePath, int expires) {
        return minIoClientConfig.getObjectUrl(filePath, expires);
    }


    /**
     * 获取文件流
     *
     * @param filePath 文件访问路径
     * @return url
     */
    public InputStream getObjectUrl(String filePath) {
        return minIoClientConfig.getObject(filePath);
    }

    /**
     * 上传文件
     *
     * @param file 文件
     * @return String 文件地址
     * @since 2023/7/5
     */
    public String uploadFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        BizExceptionCheckUtils.isNull(originalFilename, CommonResultCode.FILE_ERROR);
        String fileName = UUID.fastUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String objectName = LocalDate.now() + "/" + fileName;
        return minIoClientConfig.putObject(objectName, file);
    }

    /**
     * 上传文件
     *
     * @param file 文件
     * @return String 文件地址
     * @since 2023/7/5
     */
    public String uploadFile(File file) {
        String originalFilename = file.getName();
        BizExceptionCheckUtils.isNull(originalFilename, CommonResultCode.FILE_ERROR);
        String fileName = UUID.fastUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String objectName = LocalDate.now() + "/" + fileName;
        return minIoClientConfig.putObject(objectName, file);
    }


    /**
     * 删除文件
     *
     * @param filePath 文件的访问路径
     * @since 2023/7/5
     */
    public void removeFile(String filePath) {
        minIoClientConfig.removeObject(filePath);
    }
}
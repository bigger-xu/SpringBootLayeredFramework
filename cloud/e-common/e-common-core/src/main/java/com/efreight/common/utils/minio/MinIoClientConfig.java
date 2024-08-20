package com.efreight.common.utils.minio;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

import com.efreight.common.constants.CommonResultCode;
import com.efreight.common.exception.EfreightBizException;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

/**
 * MinIO配置
 *
 * @author 张永伟
 * @since 2023/7/5
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(MinioProperties.class)
public class MinIoClientConfig {
    @Autowired
    private MinioProperties minioProperties;

    private MinioClient minioClient;

    public MinIoClientConfig() {}

    public MinioClient getMinioClient() {
        if (minioClient == null) {
            try {
                minioClient = MinioClient.builder().endpoint(minioProperties.getEndpoint()).credentials(minioProperties.getAccessKey(),
                        minioProperties.getSecretKey()).build();
                log.info("初始化MinIO完成");
            } catch (Exception e) {
                log.info("初始化MinIO异常");
                throw new EfreightBizException(CommonResultCode.FILE_CONFIG_ERROR, e);
            }
        }
        return minioClient;
    }

    /**
     * 获取文件外链
     *
     * @param filePath 文件路径
     * @param expires  过期时间 <=7
     * @return 文件url
     */
    public String getObjectUrl(String filePath, int expires) {
        try {
            String objectName = filePath.replace(minioProperties.getEndpoint() + "/" + minioProperties.getBucketName() + "/", "");
            log.info("MinIO获取文件临时外链 --> 方法名:【getObjectUrl】--> 参数:filePath = {}, expires = {}", filePath, expires);
            return getMinioClient().getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder().object(objectName).expiry(expires).bucket(minioProperties.getBucketName()).build());
        } catch (Exception e) {
            log.info("MinIO获取文件临时外链异常 --> 方法名:【getObjectUrl】--> 参数:filePath = {}, expires = {}", filePath, expires);
            throw new EfreightBizException(CommonResultCode.FILE_ERROR, e);
        }
    }

    /**
     * 获取文件
     *
     * @param filePath 文件路径
     * @return 文件流
     */
    public InputStream getObject(String filePath) {
        try {
            String objectName = filePath.replace(minioProperties.getEndpoint() + "/" + minioProperties.getBucketName() + "/", "");
            log.info("MinIO获取文件 --> 方法名:【getObject】--> 参数:filePath = {}", filePath);
            return getMinioClient().getObject(GetObjectArgs.builder().object(objectName).bucket(minioProperties.getBucketName()).build());
        } catch (Exception e) {
            log.info("MinIO获取文件异常 --> 方法名:【getObject】--> 参数:filePath = {}", filePath);
            throw new EfreightBizException(CommonResultCode.FILE_ERROR, e);
        }
    }

    /**
     * 上传文件
     *
     * @param objectName 文件名称
     * @param multipartFile       MultipartFile格式文件
     */
    public String putObject(String objectName, MultipartFile multipartFile) {
        try {
            PutObjectArgs objectArgs = PutObjectArgs.builder().bucket(minioProperties.getBucketName()).object(objectName).stream(
                    multipartFile.getInputStream(), multipartFile.getSize(), -1).contentType(multipartFile.getContentType()).build();
            //文件名称相同会覆盖
            getMinioClient().putObject(objectArgs);
            log.info("MinIO上传文件 --> 方法名:【putObject】--> 参数:objectName = {}, file = {}", objectName, multipartFile);
            return minioProperties.getEndpoint() + "/" + minioProperties.getBucketName() + "/" + objectName;
        } catch (Exception e) {
            log.info("MinIO上传文件异常 --> 方法名:【putObject】--> 参数:objectName = {}, file = {}", objectName, multipartFile);
            throw new EfreightBizException(CommonResultCode.FILE_ERROR, e);
        }
    }

    /**
     * 上传文件
     *
     * @param objectName 文件名称
     * @param file       File格式文件
     */
    public String putObject(String objectName, File file) {
        try {
            PutObjectArgs objectArgs = PutObjectArgs.builder().bucket(minioProperties.getBucketName()).object(objectName).stream(
                    Files.newInputStream(file.toPath()), file.length(), -1).contentType(Files.probeContentType(file.toPath())).build();
            //文件名称相同会覆盖
            getMinioClient().putObject(objectArgs);
            log.info("MinIO上传文件 --> 方法名:【putObject】--> 参数:objectName = {}, file = {}", objectName, file);
            return minioProperties.getEndpoint() + "/" + minioProperties.getBucketName() + "/" + objectName;
        } catch (Exception e) {
            log.info("MinIO上传文件异常 --> 方法名:【putObject】--> 参数:objectName = {}, file = {}", objectName, file);
            throw new EfreightBizException(CommonResultCode.FILE_ERROR, e);
        }
    }

    /**
     * 删除文件
     *
     * @param filePath 文件路径
     */
    public void removeObject(String filePath) {
        try {
            log.info("MinIO删除文件 --> 方法名:【removeObject】--> 参数:filePath = {}", filePath);
            String objectName = filePath.replace(minioProperties.getEndpoint() + "/" + minioProperties.getBucketName() + "/", "");
            getMinioClient().removeObject(RemoveObjectArgs.builder().object(objectName).bucket(minioProperties.getBucketName()).build());
        } catch (Exception e) {
            log.info("MinIO删除文件异常 --> 方法名:【removeObject】--> 参数:filePath = {}", filePath);
            throw new EfreightBizException(CommonResultCode.FILE_ERROR, e);
        }
    }
}

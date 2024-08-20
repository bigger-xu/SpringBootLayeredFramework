package com.efreight.common.utils.minio;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 张永伟
 * @since 2023/7/5
 */
@Getter
@Setter
@Component
@ConfigurationProperties(value = "minio")
public class MinioProperties {
    /**
     * 链接地址
     */
    private String endpoint;

    /**
     * 密钥
     */
    private String accessKey;

    private String secretKey;

    /**
     * 存储桶
     */
    private String bucketName;
}

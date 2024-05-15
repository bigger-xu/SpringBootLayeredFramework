package com.cto.order.test;

import java.io.File;

import com.cto.common.utils.minio.MinioUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 张永伟
 * @since 2023/7/31
 */
@SpringBootTest
@Slf4j
public class UploadTest {

    @Autowired
    private MinioUtil minioUtil;

    @Test
    public void test() {
        File file = new File("D:\\datadisk\\test.txt");
        minioUtil.uploadFile(file);
    }

}

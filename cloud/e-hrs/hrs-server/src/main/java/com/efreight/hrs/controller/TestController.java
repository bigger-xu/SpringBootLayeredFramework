package com.efreight.hrs.controller;

import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;

import com.alibaba.fastjson2.JSONObject;
import com.efreight.common.constants.HrsResultCode;
import com.efreight.common.exception.EfreightBizException;
import com.efreight.common.message.model.MailMessageInfo;
import com.efreight.common.mq.hrs.productor.MailSendProducer;
import com.efreight.common.utils.minio.MinioUtil;
import com.efreight.hrs.entity.Test;
import com.efreight.hrs.service.ITestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Zhang Yongwei
 * @since 2023-07-03
 */
@Tag(name = "测试")
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
@Slf4j
public class TestController {

    private final ITestService iTestService;

    private final MailSendProducer mailSendProducer;

    private final MinioUtil minioUtil;

    @PostMapping("/save")
    @Operation(summary = "保存")
    public void save(@RequestBody Test test) {
        iTestService.save(test);
    }

    @GetMapping("/get")
    @Operation(summary = "查询")
    public Test get(Long id) {
        if (id == 1) {
            throw new EfreightBizException(HrsResultCode.HRS_NO_ORG_ERROR);
        }
        return iTestService.getById(id);
    }

    @GetMapping("/time")
    @Operation(summary = "测试时间时区")
    public Object time() {
        MailMessageInfo messageInfo = new MailMessageInfo();
        messageInfo.setUuid(UUID.randomUUID().toString());
        messageInfo.setSubject("主题");
        messageInfo.setContent("内容");
        mailSendProducer.sendMailMessage(messageInfo);
        JSONObject result = new JSONObject();
        result.put("time", LocalDateTime.now());
        return result;
    }

    @PostMapping("/upload")
    @Operation(summary = "文件上传")
    public Object upload(MultipartFile file) {
        String upload = minioUtil.uploadFile(file);
        JSONObject result = new JSONObject();
        result.put("file", upload);
        File file1 = new File("D:\\datadisk\\test.txt");
        minioUtil.uploadFile(file1);
        return result;
    }

    @GetMapping("/removeFile")
    @Operation(summary = "文件删除")
    @Parameters({ @Parameter(name = "filePath", description = "filePath", in = ParameterIn.DEFAULT, required = true) })
    public void upload(String filePath) {
        minioUtil.removeFile(filePath);
    }
}

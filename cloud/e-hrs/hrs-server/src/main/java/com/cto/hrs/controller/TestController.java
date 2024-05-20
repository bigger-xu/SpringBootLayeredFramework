package com.cto.hrs.controller;

import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;
import com.cto.common.base.BaseController;
import com.cto.common.constants.HrsResultCode;
import com.cto.common.exception.CtoBizException;
import com.cto.common.message.model.MailMessageInfo;
import com.cto.common.mq.hrs.productor.MailSendProducer;
import com.cto.common.utils.minio.MinioUtil;
import com.cto.hrs.entity.Test;
import com.cto.hrs.service.ITestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
//@Api(tags = { "测试" })
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
@Slf4j
public class TestController extends BaseController {

    private final ITestService iTestService;

    private final MailSendProducer mailSendProducer;

    private final MinioUtil minioUtil;

    @PostMapping("/transactional")
    //@ApiOperation(value = "事务回滚")
    public void transactional() {
        iTestService.createTest();
        //iTestService.createTestTcc();
    }

    @GetMapping("/get")
    //@ApiOperation(value = "查询")
    //@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", dataTypeClass = Long.class, required = true) })
    public Test get(Long id) {
        if (id == 1) {
            throw new CtoBizException(HrsResultCode.HRS_NO_ORG_ERROR);
        }
        return iTestService.getById(id);
    }

    @GetMapping("/time")
    //@ApiOperation(value = "测试时间时区")
    public Object time() {
        //发送mq消息
        MailMessageInfo messageInfo = new MailMessageInfo();
        messageInfo.setUuid(UUID.randomUUID().toString());
        messageInfo.setSubject("主题");
        messageInfo.setContent("内容");
        SendResult sendResult = mailSendProducer.sendMailMessage(messageInfo);
        if(sendResult.getSendStatus().equals(SendStatus.SEND_OK)){
            //投递成功
        }
        JSONObject result = new JSONObject();
        result.put("time", LocalDateTime.now());
        return result;
    }

    @PostMapping("/upload")
    //@ApiOperation(value = "文件上传")
    public Object upload(MultipartFile file) {
        //表单提交上传 （禁止使用，由前端发起上传，否则文件需要传递两次，很耗时）
        String upload = minioUtil.uploadFile(file);
        JSONObject result = new JSONObject();
        result.put("file", upload);
        //后端生产的文件上传云端 （推荐）
        File file1 = new File("D:\\datadisk\\test.txt");
        minioUtil.uploadFile(file1);
        return result;
    }

    @GetMapping("/removeFile")
    //@ApiOperation(value = "文件删除")
    //@ApiImplicitParams({ @ApiImplicitParam(name = "filePath", value = "filePath", dataTypeClass = String.class, required = true) })
    public void upload(String filePath) {
        minioUtil.removeFile(filePath);
    }

}

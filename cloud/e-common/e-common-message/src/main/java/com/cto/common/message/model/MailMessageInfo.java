package com.cto.common.message.model;

import java.io.File;
import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * @author 张永伟
 * @since 2023/7/10
 */
@Data
public class MailMessageInfo {

    /**
     * 保证幂等性的UUID
     */
    private String uuid;

    /**
     * 发送邮件的用户信息
     */
    private MailSendUserInfo mailSendUserInfo;

    /**
     * 收件人列表
     */
    private String[] receiverList;

    /**
     * 抄送人列表
     */
    private String[] ccUserList;

    /**
     * 密送人列表
     */
    private String[] bccUserList;

    /**
     * 标题
     */
    private String subject;

    /**
     * 内容
     */
    private String content;

    /**
     * 附件列表
     */
    private List<Map<String, String>> fileMaps;

    /**
     * 嵌入图片
     */
    private Map<String, File> imgMap;

}

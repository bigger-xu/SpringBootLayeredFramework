package com.efreight.common.message.model;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;

/**
 * 发送系统邮件数据
 * @author 张永伟
 * @since 2023/7/10
 */
@Data
public class MailSystemMessageInfo implements Serializable {
    
    private static final long serialVersionUID = 1957015719688011870L;
    
    /**
     * 保证幂等性的UUID
     */
    private String uuid;
    
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
    private Map<String, String> fileMaps;

    /**
     * 附件图片
     */
    private Map<String, String> imgMap;

}

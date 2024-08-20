package com.efreight.common.message.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MailSendRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer mailSendId;

    private Integer orgId;

    private String mailType;

    private Integer mailFromUserId;

    private String mailTo;

    private String mailCc;

    private String mailBcc;

    private Integer mailAttachmentsNumber;

    private Integer mailAttachmentsZip;

    private String mailTitle;

    private String mailBody;

    private String mailSignature;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date mailSendTime;

    private Integer mailPriorityLevel;

    private Integer isValid;

    private Integer creatorId;

    private String creatorName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private String mailAttachmentsUrl;

    private String mailAttachmentsName;

    private Integer mailSendStatus;

    private String mailSendRemark;

    private String mailFromRemark;

    private Boolean mailValid;

    private String userEmail;

    private String mailName;

    private String mailUser;

    private String mailVerifyCode;

    private String mailSmtp;

    private Integer mailPort;

    private Boolean mailSsl;

    private String mailAddress;
}

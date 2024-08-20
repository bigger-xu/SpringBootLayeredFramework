package com.efreight.common.message;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.CryptoException;
import com.efreight.common.constants.CommonResultCode;
import com.efreight.common.constants.HrsResultCode;
import com.efreight.common.enums.EmailErrorEnum;
import com.efreight.common.exception.EfreightBizException;
import com.efreight.common.message.model.MailSendUserInfo;
import com.efreight.common.utils.RsaUtil;
import com.efreight.common.utils.ZipUtil;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * 邮件发送服务
 *
 * @author ZhangYongWei
 * @since 2024/8/13
 */
@Slf4j
@Component
@RefreshScope
public class MailSendService {
    
    private static final JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
    
    static {
        System.getProperties().setProperty("mail.mime.splitlongparameters", "false");
    }
    
    @Value("${system-email.user-name:''}")
    private String emailUserName;
    
    @Value("${system-email.nick-name:''}")
    private String emailNickName;
    
    @Value("${system-email.email-password:''}")
    private String emailPassword;
    
    @Value("${system-email.email-host:''}")
    private String emailHost;
    
    @Value("${system-email.email-port:0}")
    private Integer emailPort;
    
    @Value("${system-email.email-ssl:false}")
    private Boolean emailSsl;
    
    /**
     * html邮件
     *
     * @param mailSendUserInfo 发件人相关信息
     * @param receiverList     收件人
     * @param ccUserList       抄送人
     * @param bccUserList      密送人
     * @param subject          主题
     * @param content          内容
     * @param fileMaps         附件
     * @param imgMap           附件图片
     * @since 2024/8/13
     */
    public void sendHtmlMailNewForHrs(MailSendUserInfo mailSendUserInfo, String[] receiverList, String[] ccUserList, String[] bccUserList,
            String subject, String content, Map<String, String> fileMaps, Map<String, String> imgMap) {
        try {
            content = addMailSignature(mailSendUserInfo.getMailSignature(), content);
            String sender = mailSendUserInfo.getMailUser();
            String nickname = mailSendUserInfo.getMailName();
            javaMailSenderImpl.setUsername(mailSendUserInfo.getMailUser());
            javaMailSenderImpl.setPassword(RsaUtil.defaultDecryptByPublicKey(mailSendUserInfo.getMailVerifyCode()));
            javaMailSenderImpl.setDefaultEncoding("UTF-8");
            javaMailSenderImpl.setHost(mailSendUserInfo.getMailSmtp());
            javaMailSenderImpl.setPort(mailSendUserInfo.getMailPort());
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.auth", "true");
            if ("Y".equals(mailSendUserInfo.getMailSsl())) {
                properties.setProperty("mail.smtp.ssl.enable", "true");
                properties.setProperty("mail.smtp.socketFactory.fallback", "false");
                properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            }
            javaMailSenderImpl.setJavaMailProperties(properties);
            //获取MimeMessage对象
            MimeMessage message = javaMailSenderImpl.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            //邮件发送人
            messageHelper.setFrom(new InternetAddress(sender, nickname));
            //邮件接收人
            if (receiverList == null || receiverList.length == 0) {
                throw new EfreightBizException(CommonResultCode.EMAIL_RECIPIENT_IS_NOT_NULL);
            }
            messageHelper.setTo(receiverList);
            //邮件抄送人
            if (ccUserList != null && ccUserList.length > 0) {
                messageHelper.setCc(ccUserList);
            }
            //邮件秘送人
            if (bccUserList != null && bccUserList.length > 0) {
                messageHelper.setBcc(bccUserList);
            }
            
            //邮件主题
            message.setSubject(subject);
            //邮件内容图片
            if (!CollectionUtils.isEmpty(imgMap)) {
                for (Map.Entry<String, String> entry : imgMap.entrySet()) {
                    content += "<img src=\"" + entry.getValue() + "\">";
                }
            }
            //邮件内容，html格式
            messageHelper.setText(content, true);
            if (!CollectionUtils.isEmpty(fileMaps)) {
                for (Map.Entry<String, String> entry : fileMaps.entrySet()) {
                    InputStreamSource streamSource = () -> Objects.requireNonNull(ZipUtil.downloadFileStream(entry.getValue()));
                    messageHelper.addAttachment(entry.getKey(), streamSource);
                }
            }
            //发送
            javaMailSenderImpl.send(message);
            //日志信息
            log.info("邮件发送成功, info={}", mailSendUserInfo);
        } catch (MailAuthenticationException | CryptoException e) {
            log.error("发送邮件失败密码错误 --> {}", mailSendUserInfo, e);
            sendSystemMessage(EmailErrorEnum.PASSWORD, mailSendUserInfo.getMailUser().split(","),
                              receiverList, ccUserList, bccUserList, subject, content, fileMaps, imgMap, e);
        } catch (EfreightBizException e) {
            log.error("发送邮件失败收件人为空 --> {}", mailSendUserInfo, e);
            sendSystemMessage(EmailErrorEnum.RECEIVER, mailSendUserInfo.getMailUser().split(","),
                              receiverList, ccUserList, bccUserList, subject, content, fileMaps, imgMap, e);
        } catch (Exception e) {
            log.error("发送邮件失败其他异常 --> {}", mailSendUserInfo, e);
            sendSystemMessage(EmailErrorEnum.CHAR, mailSendUserInfo.getMailUser().split(","),
                              receiverList, ccUserList, bccUserList, subject, content, fileMaps, imgMap, e);
        }
    }
    
    /**
     * 添加邮件签名信息
     *
     * @param signature 签名信息
     * @param content   邮件内容
     * @return 带有签名的邮件内容
     */
    private String addMailSignature(String signature, String content) {
        if (StringUtils.isNotBlank(signature)) {
            signature = signature.replace("\n", "<br />");
            content += "<br /><br />" + signature;
        } else {
            content += "<br /><br />华贸ATIS<br /><br />";
        }
        return content;
    }
    
    private void sendSystemMessage(EmailErrorEnum emailErrorEnum, String[] sysSendEmail, String[] receiverList, String[] ccUserList, String[] bccUserList,
            String subject, String content, Map<String, String> fileMaps, Map<String, String> imgMap, Exception e) {
        String systemSubject = "";
        StringBuilder systemContent = new StringBuilder();
        switch (emailErrorEnum) {
            case CHAR:
                systemSubject = "邮件发送失败，请检查收件人、抄送人以及邮件内容";
                systemContent.append("<b>错误原因分析：</b>").append("全局参数对应的名称是否在订单中不存在、收件人抄送人是否为空").append("<br/>");
                systemContent.append("<b>原始邮件标题：</b>").append(subject).append("<br/>");
                systemContent.append("<b>原始邮件收件人：</b>").append(Arrays.toString(receiverList)).append("<br/>");
                if (ccUserList != null && ccUserList.length > 0) {
                    systemContent.append("<b>原始邮件抄送人：</b>").append(Arrays.toString(ccUserList)).append("<br/>");
                }
                if (bccUserList != null && bccUserList.length > 0) {
                    systemContent.append("<b>原始邮件密送人：</b>").append(Arrays.toString(bccUserList)).append("<br/>");
                }
                systemContent.append("<b>原始邮件内容：</b>").append(content);
                systemContent.append("<b>系统异常代码：</b>").append(e.getMessage());
                break;
            case PASSWORD:
                systemSubject = "邮件发送失败，邮件配置账号密码不正确";
                systemContent.append("<b>原始邮件标题：</b>").append(subject).append("<br/>");
                systemContent.append("<b>原始邮件收件人：</b>").append(Arrays.toString(receiverList)).append("<br/>");
                if (ccUserList != null && ccUserList.length > 0) {
                    systemContent.append("<b>原始邮件抄送人：</b>").append(Arrays.toString(ccUserList)).append("<br/>");
                }
                if (bccUserList != null && bccUserList.length > 0) {
                    systemContent.append("<b>原始邮件密送人：</b>").append(Arrays.toString(bccUserList)).append("<br/>");
                }
                systemContent.append("<b>原始邮件内容：</b>").append(content);
                systemContent.append("<b>系统异常代码：</b>").append(e.getMessage());
                break;
            case RECEIVER:
                systemSubject = "邮件发送失败，邮件收件人为空";
                systemContent.append("<b>原始邮件标题：</b>").append(subject).append("<br/>");
                systemContent.append("<b>原始邮件收件人：邮件收件人为空</b>").append("<br/>");
                if (ccUserList != null && ccUserList.length > 0) {
                    systemContent.append("<b>原始邮件抄送人：</b>").append(Arrays.toString(ccUserList)).append("<br/>");
                }
                if (bccUserList != null && bccUserList.length > 0) {
                    systemContent.append("<b>原始邮件密送人：</b>").append(Arrays.toString(bccUserList)).append("<br/>");
                }
                systemContent.append("<b>原始邮件内容：</b>").append(content);
                break;
            default:
                break;
        }
        sendSystemMessage(sysSendEmail, systemSubject, systemContent.toString(), fileMaps, imgMap, true);
    }
    
    /**
     * 系统管理员发送邮件
     *
     * @param receiverList 接收人
     * @param subject      邮件主题
     * @param content      邮件内容
     * @param fileMaps     附件文件
     * @param imgMap       附件图片
     * @since 2023/12/27
     */
    public void sendSystemMessage(String[] receiverList, String subject, String content, Map<String, String> fileMaps, Map<String, String> imgMap, boolean isHtml) {
        try {
            log.info("系统发送系统邮件日志开始 --> 方法名:【sendSystemMessage】--> 参数:receiverList = {}, subject = {}, content = {}, fileMaps = {}, imgMap = {}", receiverList, subject, content, fileMaps, imgMap);
            javaMailSenderImpl.setUsername(emailUserName);
            javaMailSenderImpl.setPassword(emailPassword);
            javaMailSenderImpl.setDefaultEncoding("UTF-8");
            javaMailSenderImpl.setHost(emailHost);
            javaMailSenderImpl.setPort(emailPort);
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.auth", "true");
            if (emailSsl) {
                properties.setProperty("mail.smtp.ssl.enable", "true");
                properties.setProperty("mail.smtp.socketFactory.fallback", "false");
                properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            }
            javaMailSenderImpl.setJavaMailProperties(properties);
            //获取MimeMessage对象
            MimeMessage message = javaMailSenderImpl.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            //邮件发送人
            messageHelper.setFrom(new InternetAddress(emailUserName, emailNickName));
            //邮件收件人
            messageHelper.setTo(receiverList);
            //邮件主题
            message.setSubject("【系统重要提醒】" + subject);
            //邮件内容图片
            if (!CollectionUtils.isEmpty(imgMap)) {
                for (Map.Entry<String, String> entry : imgMap.entrySet()) {
                    content += "<img src=\"" + entry.getValue() + "\">";
                }
            }
            //邮件内容，html格式
            messageHelper.setText(content, isHtml);
            if (!CollectionUtils.isEmpty(fileMaps)) {
                for (Map.Entry<String, String> entry : fileMaps.entrySet()) {
                    InputStreamSource streamSource = () -> Objects.requireNonNull(ZipUtil.downloadFileStream(entry.getValue()));
                    messageHelper.addAttachment(entry.getKey(), streamSource);
                }
            }
            //发送
            javaMailSenderImpl.send(message);
            //日志信息
            log.info("系统发送异常邮件日志结束 --> 方法名:【sendSystemMessage】--> 邮件发送成功");
        } catch (Exception e) {
            log.error("方法名:【sendSystemMessage】--> 参数:{},{},{}", receiverList, subject, content);
            throw new EfreightBizException(HrsResultCode.MAIL_SEND_ERROR, e);
        }
    }
    
    public Boolean checkEmail(Map<String, Object> param) {
        /*if (param.get("loginRole") == null || StrUtil.isBlank((String) param.get("loginRole"))) {
            throw new RuntimeException("用户类型不能为空");
        }*/
        if (param.get("mailName") == null || StrUtil.isBlank((String) param.get("mailName"))) {
            throw new EfreightBizException(HrsResultCode.MAIL_NICKNAME_IS_MISSING);
        }
        if (param.get("mailUser") == null || StrUtil.isBlank((String) param.get("mailUser"))) {
            throw new EfreightBizException(HrsResultCode.MAIL_USERNAME_IS_MISSING);
        }
        if (param.get("mailVerifyCode") == null || StrUtil.isBlank((String) param.get("mailVerifyCode"))) {
            throw new EfreightBizException(HrsResultCode.MAIL_PASSWORD_IS_MISSING);
        }
        if (param.get("mailSmtp") == null || StrUtil.isBlank((String) param.get("mailSmtp"))) {
            throw new EfreightBizException(HrsResultCode.MAIL_SMTP_IS_MISSING);
        }
        if (param.get("mailPort") == null) {
            throw new EfreightBizException(HrsResultCode.MAIL_PORT_IS_MISSING);
        }
        if (param.get("mailSsl") == null) {
            throw new EfreightBizException(HrsResultCode.MAIL_SSL_IS_MISSING);
        }
        
        String sender = "";
        if ("admin".equals(param.get("loginRole"))) {
            if (param.get("mailAddress") == null || StrUtil.isBlank((String) param.get("mailAddress"))) {
                throw new EfreightBizException(HrsResultCode.MAIL_SENDER_ADDRESS_IS_MISSING);
            }
            sender = (String) param.get("mailAddress");
        } else {
            if (param.get("mailUser") == null || StrUtil.isBlank((String) param.get("mailUser"))) {
                throw new EfreightBizException(HrsResultCode.MAIL_SENDER_ADDRESS_IS_MISSING);
            }
            sender = (String) param.get("mailUser");
        }
        String nickname = (String) param.get("mailName");
        javaMailSenderImpl.setUsername((String) param.get("mailUser"));
        javaMailSenderImpl.setPassword(RsaUtil.defaultDecryptByPublicKey((String) param.get("mailVerifyCode")));
        javaMailSenderImpl.setDefaultEncoding("UTF-8");
        javaMailSenderImpl.setHost((String) param.get("mailSmtp"));
        javaMailSenderImpl.setPort((Integer) param.get("mailPort"));
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.connectiontimeout", "4000");
        if ("Y".equals(param.get("mailSsl").toString())) {
            properties.setProperty("mail.smtp.ssl.enable", "true");
            properties.setProperty("mail.smtp.socketFactory.fallback", "false");
            properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }
        javaMailSenderImpl.setJavaMailProperties(properties);
        try {
            //创建SimpleMailMessage对象
            MimeMessage message = javaMailSenderImpl.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, false);
            //邮件发送人
            messageHelper.setFrom(new InternetAddress(sender, nickname));
            //邮件接收人
            messageHelper.setTo(sender);
            //邮件主题
            messageHelper.setSubject("邮箱验证");
            //邮件内容
            messageHelper.setText("邮箱验证成功");
            //发送邮件
            javaMailSenderImpl.send(message);
            log.info("邮箱检验成功");
            return true;
        } catch (Exception e) {
            log.error("邮箱校验失败：{}", e.getMessage());
            return false;
        }
    }
    
    /**
     * 对邮件地址进行处理
     *
     * @param emailArr
     */
    private String[] filterEmailAddress(String[] emailArr) {
        if (null == emailArr) {
            return new String[0];
        }
        return Arrays.stream(emailArr).filter(StringUtils::isNotBlank).toArray(String[]::new);
    }
    
}

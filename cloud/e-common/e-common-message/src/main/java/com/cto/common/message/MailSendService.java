package com.cto.common.message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.cto.common.constants.CommonResultCode;
import com.cto.common.exception.CtoBizException;
import com.cto.common.message.model.MailSendRecord;
import com.cto.common.message.model.MailSendUserInfo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeUtility;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MailSendService {

    private static final JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();

    static {
        System.getProperties().setProperty("mail.mime.splitlongparameters", "false");
    }

    private String sender;

    private String nickname;

    private MailSendUserInfo setValueWhenUserSelf(MailSendUserInfo mailSendUserInfo) {
        sender = mailSendUserInfo.getUserEmail();
        nickname = mailSendUserInfo.getMailName();
        javaMailSenderImpl.setUsername(mailSendUserInfo.getMailUser());
        javaMailSenderImpl.setPassword(mailSendUserInfo.getMailVerifyCode());
        javaMailSenderImpl.setDefaultEncoding("UTF-8");
        javaMailSenderImpl.setHost(mailSendUserInfo.getMailSmtp());
        javaMailSenderImpl.setPort(mailSendUserInfo.getMailPort());
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        if (mailSendUserInfo.getMailSsl()) {
            properties.setProperty("mail.smtp.ssl.enable", "true");
            properties.setProperty("mail.smtp.socketFactory.fallback", "false");
            properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }
        javaMailSenderImpl.setJavaMailProperties(properties);
        return mailSendUserInfo;
    }

    public void sendHtmlMailForApi(MailSendUserInfo mailSendUserInfo, String[] receiverList, String[] ccUserList, String[] bccUserList,
            String subject, String content, Map<String, File> imgMap) {
        try {
            MailSendUserInfo userVo = setValueWhenUserSelf(mailSendUserInfo);
            content = addMailSignature(userVo.getMailSignature(), content);
            //获取MimeMessage对象
            MimeMessage message = javaMailSenderImpl.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            //邮件发送人
            messageHelper.setFrom(new InternetAddress(sender, nickname));
            //邮件接收人
            if (receiverList == null || receiverList.length == 0) {
                throw new CtoBizException(CommonResultCode.EMAIL_RECIPIENT_IS_NOT_NULL);
            }
            messageHelper.setTo(receiverList);
            //邮件抄送人
            if (ccUserList != null && ccUserList.length > 0 && !"".equals(ccUserList[0])) {
                messageHelper.setCc(ccUserList);
            }
            //邮件秘送人
            if (bccUserList != null && bccUserList.length > 0 && !"".equals(bccUserList[0])) {
                messageHelper.setBcc(bccUserList);
            }

            //邮件主题
            message.setSubject(subject);
            //邮件内容，html格式
            messageHelper.setText(content, true);
            if (imgMap != null) {
                for (Map.Entry<String, File> entry : imgMap.entrySet()) {
                    messageHelper.addInline(entry.getKey(), entry.getValue());
                }
            }
            //发送
            javaMailSenderImpl.send(message);
            //日志信息
            log.info("邮件发送成功");
        } catch (Exception e) {
            log.error("邮件发送失败：{}", e.getMessage());
            throw new CtoBizException(CommonResultCode.EMAIL_PARAMS_ERROR);
        }
    }

    /**
     * 简单文本邮件
     *
     * @param receiverList 收件人
     * @param subject      主题
     * @param content      内容
     *                     参数说明：ifUseSelf是否开启自定义发件邮箱；receiverList收件人列表；ccUserList抄送人列表；bccUserList密送人列表；subject主题；content内容；
     */
    public void sendSimpleMailNew(MailSendUserInfo mailSendUserInfo, String[] receiverList, String[] ccUserList, String[] bccUserList,
            String subject, String content) {
        try {
            setValueWhenUserSelf(mailSendUserInfo);
            //创建SimpleMailMessage对象
            MimeMessage message = javaMailSenderImpl.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, false);
            //邮件发送人
            messageHelper.setFrom(new InternetAddress(sender, nickname));
            //邮件接收人
            if (receiverList == null || receiverList.length == 0) {
                throw new CtoBizException(CommonResultCode.EMAIL_RECIPIENT_IS_NOT_NULL);
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
            messageHelper.setSubject(subject);
            //邮件内容
            messageHelper.setText(content);

            //发送邮件
            javaMailSenderImpl.send(message);
            log.info("邮件发送成功");
        } catch (Exception e) {
            log.error("邮件发送失败：{}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * html邮件
     *
     * @param receiverList 收件人
     * @param subject      主题
     * @param content      内容
     *                     参数说明：ifUseSelf是否开启自定义发件邮箱；receiverList收件人列表；ccUserList抄送人列表；bccUserList密送人列表；subject主题；content内容；
     *                     imgMap嵌入图片时使用，类型Map（key为cid的值，value为图片文件类型为File） 与content的内容相匹配 例如：<img src="cid:key"></img> 则map.put("key",imgFile)
     */
    public void sendHtmlMailNew(MailSendUserInfo mailSendUserInfo, String[] receiverList, String[] ccUserList, String[] bccUserList,
            String subject, String content, Map<String, File> imgMap) {
        try {
            MailSendUserInfo userVo = setValueWhenUserSelf(mailSendUserInfo);
            content = addMailSignature(userVo.getMailSignature(), content);
            //获取MimeMessage对象
            MimeMessage message = javaMailSenderImpl.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            //邮件发送人
            messageHelper.setFrom(new InternetAddress(sender, nickname));
            //邮件接收人
            if (receiverList == null || receiverList.length == 0) {
                throw new CtoBizException(CommonResultCode.EMAIL_RECIPIENT_IS_NOT_NULL);
            }
            messageHelper.setTo(receiverList);
            //邮件抄送人
            if (ccUserList != null && ccUserList.length > 0 && !"".equals(ccUserList[0])) {
                messageHelper.setCc(ccUserList);
            }
            //邮件秘送人
            if (bccUserList != null && bccUserList.length > 0 && !"".equals(bccUserList[0])) {
                messageHelper.setBcc(bccUserList);
            }

            //邮件主题
            message.setSubject(subject);
            //邮件内容，html格式
            messageHelper.setText(content, true);
            if (imgMap != null) {
                for (Map.Entry<String, File> entry : imgMap.entrySet()) {
                    messageHelper.addInline(entry.getKey(), entry.getValue());
                }
            }
            //发送
            javaMailSenderImpl.send(message);
            //日志信息
            log.info("邮件发送成功");
        } catch (Exception e) {
            log.error("邮件发送失败：{}", e.getMessage());
            throw new CtoBizException(CommonResultCode.EMAIL_PARAMS_ERROR);
        }
    }

    /**
     * 带附件的邮件
     *
     * @param receiverList 收件人
     * @param subject      主题
     * @param content      内容
     * @param fileMaps     附件
     *                     参数说明：ifUseSelf是否开启自定义发件邮箱；receiverList收件人列表；ccUserList抄送人列表；bccUserList密送人列表；subject主题；content内容；
     *                     fileMaps附件列表（本地文件路径flag为local 路径建议为：/datadisk/html/PDFtemplate/temp/下面,七牛为upload）
     *                     imgMap嵌入图片时使用，类型Map（key为cid的值，value为图片文件类型为File） 与content的内容相匹配 例如：<img src="cid:key"></img> 则map.put("key",imgFile)
     */
    public void sendAttachmentsMailNew(MailSendUserInfo mailSendUserInfo, String[] receiverList, String[] ccUserList, String[] bccUserList,
            String subject, String content, List<Map<String, String>> fileMaps, Map<String, File> imgMap) {
        try {
            MailSendUserInfo userVo = setValueWhenUserSelf(mailSendUserInfo);
            content = addMailSignature(userVo.getMailSignature(), content);
            System.getProperties().setProperty("mail.mime.splitlongparameters", "false");
            MimeMessage message = javaMailSenderImpl.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(new InternetAddress(sender, nickname));
            if (receiverList == null || receiverList.length == 0) {
                throw new CtoBizException(CommonResultCode.EMAIL_RECIPIENT_IS_NOT_NULL);
            }
            helper.setTo(receiverList);
            //邮件抄送人
            if ((ccUserList = filterEmailAddress(ccUserList)).length > 0) {
                helper.setCc(ccUserList);
            }
            //邮件秘送人
            if (bccUserList != null && bccUserList.length > 0) {
                helper.setBcc(bccUserList);
            }
            helper.setSubject(subject);
            helper.setText(content, true);
            if (imgMap != null) {
                for (Map.Entry<String, File> entry : imgMap.entrySet()) {
                    helper.addInline(entry.getKey(), entry.getValue());
                }
            }
            if (fileMaps == null || fileMaps.size() == 0) {
                throw new RuntimeException("请上传附件");
            }
            fileMaps.stream().forEach(file -> {
                if (StrUtil.isBlank(file.get("name")) || StrUtil.isBlank(file.get("path")) || StrUtil.isBlank(file.get("flag"))) {
                    throw new RuntimeException("附件缺失必要的参数信息");
                }
                String newFilePath = "";
                if ("upload".equals(file.get("flag"))) {
                    newFilePath = "/datadisk/html/PDFtemplate/temp/" + new Date().getTime() + "/" + file.get("name");
                    downloadFile(file.get("path"), newFilePath);
                } else {
                    newFilePath = file.get("path");
                }
                try {
                    helper.addAttachment(MimeUtility.encodeText(file.get("name")), new File(newFilePath));
                } catch (MessagingException | UnsupportedEncodingException e) {
                    throw new RuntimeException(e.getMessage());
                }
            });
            javaMailSenderImpl.send(message);
            //日志信息
            log.info("邮件发送成功");
        } catch (Exception e) {
            log.error("邮件发送失败：{}", e.getMessage());
            throw new CtoBizException(CommonResultCode.EMAIL_PARAMS_ERROR);
        }
    }

    /**
     * html邮件-专为HRS
     *
     * @param receiverList 收件人
     * @param subject      主题
     * @param content      内容
     *                     参数说明：ifUseSelf是否开启自定义发件邮箱；receiverList收件人列表；ccUserList抄送人列表；bccUserList密送人列表；subject主题；content内容；
     *                     imgMap嵌入图片时使用，类型Map（key为cid的值，value为图片文件类型为File） 与content的内容相匹配 例如：<img src="cid:key"></img> 则map.put("key",imgFile)
     */
    public void sendHtmlMailNewForHrs(MailSendUserInfo mailSendUserInfo, String[] receiverList, String[] ccUserList, String[] bccUserList,
            String subject, String content, Map<String, File> imgMap) {
        try {
            content = addMailSignature(mailSendUserInfo.getMailSignature(), content);
            sender = mailSendUserInfo.getMailAddress();
            nickname = mailSendUserInfo.getMailName();
            javaMailSenderImpl.setUsername(mailSendUserInfo.getMailUser());
            javaMailSenderImpl.setPassword(mailSendUserInfo.getMailVerifyCode());
            javaMailSenderImpl.setDefaultEncoding("UTF-8");
            javaMailSenderImpl.setHost(mailSendUserInfo.getMailSmtp());
            javaMailSenderImpl.setPort(mailSendUserInfo.getMailPort());
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.auth", "true");
            if (mailSendUserInfo.getMailSsl()) {
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
                throw new CtoBizException(CommonResultCode.EMAIL_RECIPIENT_IS_NOT_NULL);
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
            //邮件内容，html格式
            messageHelper.setText(content, true);
            if (imgMap != null) {
                for (Map.Entry<String, File> entry : imgMap.entrySet()) {
                    messageHelper.addInline(entry.getKey(), entry.getValue());
                }
            }
            //发送
            javaMailSenderImpl.send(message);
            //日志信息
            log.info("邮件发送成功");
        } catch (Exception e) {
            log.error("邮件发送失败：{}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 带附件的邮件-专为HRS
     *
     * @param receiverList 收件人
     * @param subject      主题
     * @param content      内容
     * @param fileMaps     附件
     *                     参数说明：ifUseSelf是否开启自定义发件邮箱；receiverList收件人列表；ccUserList抄送人列表；bccUserList密送人列表；subject主题；content内容；
     *                     fileMaps附件列表（本地文件路径flag为local 路径建议为：/datadisk/html/PDFtemplate/temp/下面,七牛为upload）
     *                     imgMap嵌入图片时使用，类型Map（key为cid的值，value为图片文件类型为File） 与content的内容相匹配 例如：<img src="cid:key"></img> 则map.put("key",imgFile)
     */
    public void sendAttachmentsMailNewForHrs(MailSendUserInfo mailSendUserInfo, String[] receiverList, String[] ccUserList,
            String[] bccUserList, String subject, String content, List<Map<String, String>> fileMaps, Map<String, File> imgMap) {
        try {
            sender = mailSendUserInfo.getMailAddress();
            nickname = mailSendUserInfo.getMailName();
            javaMailSenderImpl.setUsername(mailSendUserInfo.getMailUser());
            javaMailSenderImpl.setPassword(mailSendUserInfo.getMailVerifyCode());
            javaMailSenderImpl.setDefaultEncoding("UTF-8");
            javaMailSenderImpl.setHost(mailSendUserInfo.getMailSmtp());
            javaMailSenderImpl.setPort(mailSendUserInfo.getMailPort());
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.auth", "true");
            javaMailSenderImpl.setJavaMailProperties(properties);
            System.getProperties().setProperty("mail.mime.splitlongparameters", "false");
            MimeMessage message = javaMailSenderImpl.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(new InternetAddress(sender, nickname));
            if (receiverList == null || receiverList.length == 0) {
                throw new CtoBizException(CommonResultCode.EMAIL_RECIPIENT_IS_NOT_NULL);
            }
            helper.setTo(receiverList);
            //邮件抄送人
            if (ccUserList != null && ccUserList.length > 0) {
                helper.setCc(ccUserList);
            }
            //邮件秘送人
            if (bccUserList != null && bccUserList.length > 0) {
                helper.setBcc(bccUserList);
            }
            helper.setSubject(subject);
            helper.setText(content, true);
            if (imgMap != null) {
                for (Map.Entry<String, File> entry : imgMap.entrySet()) {
                    helper.addInline(entry.getKey(), entry.getValue());
                }
            }
            if (fileMaps == null || fileMaps.size() == 0) {
                throw new RuntimeException("请上传附件");
            }
            fileMaps.stream().forEach(file -> {
                if (StrUtil.isBlank(file.get("name")) || StrUtil.isBlank(file.get("path")) || StrUtil.isBlank(file.get("flag"))) {
                    throw new RuntimeException("附件缺失必要的参数信息");
                }
                String newFilePath = "";
                if ("upload".equals(file.get("flag"))) {
                    newFilePath = "/datadisk/html/PDFtemplate/temp/" + new Date().getTime() + "/" + file.get("name");
                    downloadFile(file.get("path"), newFilePath);
                } else {
                    newFilePath = file.get("path");
                }
                try {
                    helper.addAttachment(MimeUtility.encodeText(file.get("name")), new File(newFilePath));
                } catch (MessagingException | UnsupportedEncodingException e) {
                    throw new RuntimeException(e.getMessage());
                }
            });
            javaMailSenderImpl.send(message);
            //日志信息
            log.info("邮件发送成功");
        } catch (Exception e) {
            log.error("邮件发送失败：{}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    private boolean downloadFile(String fileURL, String fileName) {
        try {
            String path = fileName.substring(0, fileName.lastIndexOf("/"));
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            URL url = new URL(fileURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            DataInputStream in = new DataInputStream(connection.getInputStream());
            DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName));
            byte[] buffer = new byte[4096];
            int count = 0;
            while ((count = in.read(buffer)) > 0) {
                out.write(buffer, 0, count);
            }
            out.close();
            in.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean checkEmail(Map<String, Object> param) {
        /*if (param.get("loginRole") == null || StrUtil.isBlank((String) param.get("loginRole"))) {
            throw new RuntimeException("用户类型不能为空");
        }*/
        if (param.get("mailName") == null || StrUtil.isBlank((String) param.get("mailName"))) {
            throw new RuntimeException("昵称不能为空");
        }
        if (param.get("mailUser") == null || StrUtil.isBlank((String) param.get("mailUser"))) {
            throw new RuntimeException("用户名不能为空");
        }
        if (param.get("mailVerifyCode") == null || StrUtil.isBlank((String) param.get("mailVerifyCode"))) {
            throw new RuntimeException("密码不能为空");
        }
        if (param.get("mailSmtp") == null || StrUtil.isBlank((String) param.get("mailSmtp"))) {
            throw new RuntimeException("SMTP不能为空");
        }
        if (param.get("mailPort") == null) {
            throw new RuntimeException("端口不能为空");
        }
        if (param.get("mailSsl") == null) {
            throw new RuntimeException("是否开启ssl不能为空");
        }

        String sender = "";
        if ("admin".equals(param.get("loginRole"))) {
            if (param.get("mailAddress") == null || StrUtil.isBlank((String) param.get("mailAddress"))) {
                throw new RuntimeException("发件人地址不能为空");
            }
            sender = (String) param.get("mailAddress");
        } else {
            if (param.get("userEmail") == null || StrUtil.isBlank((String) param.get("userEmail"))) {
                throw new RuntimeException("发件人地址不能为空");
            }
            sender = (String) param.get("userEmail");
        }
        String nickname = (String) param.get("mailName");
        javaMailSenderImpl.setUsername((String) param.get("mailUser"));
        javaMailSenderImpl.setPassword((String) param.get("mailVerifyCode"));
        javaMailSenderImpl.setDefaultEncoding("UTF-8");
        javaMailSenderImpl.setHost((String) param.get("mailSmtp"));
        javaMailSenderImpl.setPort((Integer) param.get("mailPort"));
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.connectiontimeout", "4000");
        if ((Boolean) param.get("mailSsl")) {
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
            content += "<br /><br />翌Cargo | 物流管理系统，助力中国物流信息化发展<br /><br />";
        }
        return content;
    }

    /**
     * 对邮件地址进行处理
     *
     * @param emailArr
     * @return
     */
    private String[] filterEmailAddress(String[] emailArr) {
        if (null == emailArr) {
            return new String[0];
        }
        List<String> result = Arrays.asList(emailArr).stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());
        return result.toArray(new String[result.size()]);
    }

    /**
     * 带附件的邮件(翌飞结算管理使用)
     *
     * @param receiverList 收件人
     * @param subject      主题
     * @param content      内容
     * @param fileMaps     附件
     *                     参数说明：sendMailUserId是对应负责人；receiverList收件人列表；ccUserList抄送人列表；bccUserList密送人列表；subject主题；content内容；
     *                     fileMaps附件列表（本地文件路径flag为local 路径建议为：/datadisk/html/PDFtemplate/temp/下面,七牛为upload）
     *                     imgMap嵌入图片时使用，类型Map（key为cid的值，value为图片文件类型为File） 与content的内容相匹配 例如：<img src="cid:key"></img> 则map.put("key",imgFile)
     */
    public void sendAttachmentsMailNewForEFBill(MailSendUserInfo mailSendUserInfo, String[] receiverList, String[] ccUserList,
            String[] bccUserList, String subject, String content, List<Map<String, String>> fileMaps, Map<String, File> imgMap) {
        try {
            MailSendUserInfo sendUserInfo = setValueWhenUserSelf(mailSendUserInfo);
            content = addMailSignature(sendUserInfo.getMailSignature(), content);
            System.getProperties().setProperty("mail.mime.splitlongparameters", "false");
            MimeMessage message = javaMailSenderImpl.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(new InternetAddress(sender, nickname));
            if (receiverList == null || receiverList.length == 0) {
                throw new CtoBizException(CommonResultCode.EMAIL_RECIPIENT_IS_NOT_NULL);
            }
            helper.setTo(receiverList);
            //邮件抄送人
            if ((ccUserList = filterEmailAddress(ccUserList)).length > 0) {
                helper.setCc(ccUserList);
            }
            //邮件秘送人
            if (bccUserList != null && bccUserList.length > 0) {
                helper.setBcc(bccUserList);
            }
            helper.setSubject(subject);
            helper.setText(content, true);
            if (imgMap != null) {
                for (Map.Entry<String, File> entry : imgMap.entrySet()) {
                    helper.addInline(entry.getKey(), entry.getValue());
                }
            }
            if (fileMaps == null || fileMaps.size() == 0) {
                throw new RuntimeException("请上传附件");
            }
            fileMaps.stream().forEach(file -> {
                if (StrUtil.isBlank(file.get("name")) || StrUtil.isBlank(file.get("path")) || StrUtil.isBlank(file.get("flag"))) {
                    throw new RuntimeException("附件缺失必要的参数信息");
                }
                String newFilePath = "";
                if ("upload".equals(file.get("flag"))) {
                    newFilePath = "/datadisk/html/PDFtemplate/temp/" + new Date().getTime() + "/" + file.get("name");
                    downloadFile(file.get("path"), newFilePath);
                } else {
                    newFilePath = file.get("path");
                }
                try {
                    helper.addAttachment(MimeUtility.encodeText(file.get("name")), new File(newFilePath));
                } catch (MessagingException | UnsupportedEncodingException e) {
                    throw new RuntimeException(e.getMessage());
                }
            });
            javaMailSenderImpl.send(message);
            //日志信息
            log.info("邮件发送成功");
        } catch (Exception e) {
            log.error("邮件发送失败：{}", e.getMessage());
            throw new CtoBizException(CommonResultCode.EMAIL_PARAMS_ERROR);
        }
    }

    @SneakyThrows
    public void sendAttachmentsMailNewForEFBillAuto(MailSendRecord mailSendRecord, String[] receiverList, String[] ccUserList,
            String[] bccUserList, List<Map<String, String>> fileMaps, Map<String, File> imgMap) {
        log.info("mailSendRecord -> {}", JSONObject.toJSONString(mailSendRecord));
        log.info("receiverList -> {}", JSONObject.toJSONString(receiverList));
        log.info("ccUserList -> {}", JSONObject.toJSONString(ccUserList));
        log.info("bccUserList -> {}", JSONObject.toJSONString(bccUserList));
        log.info("fileMaps -> {}", JSONObject.toJSONString(fileMaps));
        log.info("imgMap -> {}", JSONObject.toJSONString(imgMap));
        if (mailSendRecord.getMailFromUserId() == 1) {//说明是使用 翌飞小云邮箱发送
            setValueWhenEFBillAutoDefault(mailSendRecord);
        } else {
            setValueWhenEFBillAuto(mailSendRecord);
        }
        String content = addMailSignature(mailSendRecord.getMailSignature(), mailSendRecord.getMailBody());
        System.getProperties().setProperty("mail.mime.splitlongparameters", "false");
        MimeMessage message = javaMailSenderImpl.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(new InternetAddress(sender, nickname));
        if (receiverList == null || receiverList.length == 0) {
            throw new CtoBizException(CommonResultCode.EMAIL_RECIPIENT_IS_NOT_NULL);
        }
        helper.setTo(receiverList);
        //邮件抄送人
        if ((ccUserList = filterEmailAddress(ccUserList)).length > 0) {
            helper.setCc(ccUserList);
        }
        //邮件秘送人
        if (bccUserList != null && bccUserList.length > 0) {
            helper.setBcc(bccUserList);
        }
        helper.setSubject(mailSendRecord.getMailTitle());
        helper.setText(content, true);
        if (imgMap != null) {
            for (Map.Entry<String, File> entry : imgMap.entrySet()) {
                helper.addInline(entry.getKey(), entry.getValue());
            }
        }

        if (fileMaps != null) {
            for (Map<String, String> map : fileMaps) {
                if (StrUtil.isBlank(map.get("name")) || StrUtil.isBlank(map.get("path")) || StrUtil.isBlank(map.get("flag"))) {
                    throw new RuntimeException("附件缺失必要的参数信息");
                }
                String newFilePath;
                if ("upload".equals(map.get("flag"))) {
                    newFilePath = "/datadisk/html/PDFtemplate/temp/" + new Date().getTime() + "/" + map.get("name");
                    downloadFile(map.get("path"), newFilePath);
                } else {
                    newFilePath = map.get("path");
                }
                helper.addAttachment(MimeUtility.encodeText(map.get("name")), new File(newFilePath));
            }
        }
        javaMailSenderImpl.send(message);
    }

    private void setValueWhenEFBillAuto(MailSendRecord user) {
        if (user.getMailValid()) {
            sender = user.getUserEmail();
            nickname = user.getMailName();
            javaMailSenderImpl.setUsername(user.getMailUser());
            javaMailSenderImpl.setPassword(user.getMailVerifyCode());
            javaMailSenderImpl.setDefaultEncoding("UTF-8");
            javaMailSenderImpl.setHost(user.getMailSmtp());
            javaMailSenderImpl.setPort(user.getMailPort());
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.auth", "true");
            if (user.getMailSsl()) {
                properties.setProperty("mail.smtp.ssl.enable", "true");
                properties.setProperty("mail.smtp.socketFactory.fallback", "false");
                properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            }
            javaMailSenderImpl.setJavaMailProperties(properties);
        }
    }

    private void setValueWhenEFBillAutoDefault(MailSendRecord superadmin) {
        sender = superadmin.getMailAddress();
        nickname = superadmin.getMailName();
        javaMailSenderImpl.setUsername(superadmin.getMailUser());
        javaMailSenderImpl.setPassword(superadmin.getMailVerifyCode());
        javaMailSenderImpl.setDefaultEncoding("UTF-8");
        javaMailSenderImpl.setHost(superadmin.getMailSmtp());
        javaMailSenderImpl.setPort(superadmin.getMailPort());
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        javaMailSenderImpl.setJavaMailProperties(properties);
    }

}

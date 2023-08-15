package com.cto.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 *
 **/
@Slf4j
public class XmlSpecialUtils {
    public static String checkSpecialCharFliter(String str, String type) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        String regEx = "[^0-9a-zA-Z\\r\\n .-]";
        if ("noSpecial".equals(type)) {
            regEx = "[^0-9a-zA-Z]";
        } else if ("tel".equals(type)) {
            regEx = "[^0-9a-zA-Z -()+]";
        } else if ("xml".equals(type)) {
            //            regEx="[^0-9a-zA-Z\\u4e00-\\u9fa5 .,@<>/\\r\\n\\t]";
            ////            regEx="\\pP|\\pS|\\s+";
            return checkSpecialChar(str);
        } else if ("num".equals(type)) {
            regEx = "[^0-9]";
        } else if ("letter".equals(type)) {
            regEx = "[^a-zA-Z]";
        } else if ("zh".equals(type)) {
            regEx = "[^0-9a-zA-Z\\r\\n\\u4e00-\\u9fa5 .-]";
        }
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 过滤xml不识别字符及emoji字符
     *
     * @param data
     * @return
     */
    public static String checkXmlChar(String data) {
        StringBuffer appender = new StringBuffer();
        if (StringUtils.isNotBlank(data)) {
            appender = new StringBuffer(data.length());
            for (int i = 0; i < data.length(); i++) {
                char ch = data.charAt(i);
                if ((ch == 0x9) || (ch == 0xA) || (ch == 0xD) || ((ch >= 0x20) && (ch <= 0xD7FF)) || ((ch >= 0xE000) && (ch <= 0xFFFD)) || ((ch >= 0x10000) && (ch <= 0x10FFFF))) {
                    appender.append(ch);
                }
            }
        }
        String result = appender.toString();
        return result.replaceAll("]]>", "");
    }

    /**
     * 过滤掉报文共性字符
     *
     * @param str
     * @return
     */
    public static String checkSpecialChar(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        str = checkXmlChar(str);
        //一站式殊字符
        String regEx = "[&%^]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        boolean find = m.find();
        return m.replaceAll("").trim();
    }

    /*
     * 过滤掉emoji字符
     *
     * @param xml
     * @return
     *//*
    public static String checkEmojiChar(String xml) {
        String res = "";
        for (int i = 0; i < xml.length(); ++i) {
            char ch = xml.charAt(i);
            if (Character.isDefined(ch)
                    //&& ch!= '&' && ch != '<' && ch != '>'
                    && !Character.isHighSurrogate(ch)
//                     && !Character.isISOControl(ch)
                    && !Character.isLowSurrogate(ch)
            ) {
                res = res + ch;
            }
        }
        //过滤表情符号
        res.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "");
        return res;
    }*/

    public static void main(String[] args) {
        String str = "\\ud800\\udc00   ./_&^%'\" \\r\\n\\t dING FENG LEATHER CO.,LTD  \n" + "<Ad🆒dres😊s>LIANYI _`°º~!@#$%^&*+={}\\':;·<>《》/?！￥…（）—+{}【】’‘；：”“\\\"\\\"。，、|？N</Address> CI<>!@#$%^&*()_ZXCVBM《》？NM<>?~！@#￥%……&TY GUANG_X005F\u0002DONG PROVINCE，P.R.C";
        String str1 = checkSpecialCharFliter(str, "xml");
        System.out.println(str1);
        String str2 = checkXmlChar(str);
        System.out.println(str2);
        //        String str3 = checkEmojiChar(str);
        //        System.out.println(str3);

    }
}

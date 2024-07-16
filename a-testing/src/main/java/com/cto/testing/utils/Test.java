package com.cto.testing.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

/**
 * @author ZhangYongWei
 * @since 2024/7/16
 */
public class Test {
    
    public static void main(String[] args) {
        CustomDictionary.reload();
        String airline = "翌飞锐特，北京到法兰，7件100公斤3个方，汉莎，12号的头程，22ALL";
        //CustomDictionary.parseLongestText(airline, (begin, end, value) -> {
        //    //System.out.println(value);
        //    System.out.printf("[%d:%d]=%s %s\n", begin, end, new String(airline.toCharArray(), begin, end - begin), value);
        //});
        Segment segment = new NShortSegment();
        segment.enableCustomDictionary(true);
        segment.enableCustomDictionaryForcing(true);
        List<Term> termList = segment.seg(airline);
        //List<Term> segment = NLPTokenizer.segment(airline);
        StringBuilder sb = new StringBuilder();
        int size = termList.size();
        for (int i = 0; i < size; i++) {
            Term pre = i - 1 < 0 ? termList.get(i) : termList.get(i - 1);
            Term term = termList.get(i);
            Term next = i + 1 == size ? termList.get(i) : termList.get(i + 1);
            //System.out.println("-------------------------------" + term.toString());
            if (term.nature.startsWith("coop")) {
                System.out.println("客户：" + term.word);
            }
            if (term.nature.startsWith("airp") &&
                    (next.word.startsWith("飞") || next.word.startsWith("到") || next.word.startsWith("经")) &&
                    (!pre.word.startsWith("飞") && !pre.word.startsWith("到") && !pre.word.startsWith("经"))) {
                System.out.println("始发港：" + term.word);
            }
            if (term.nature.startsWith("airp") &&
                    (pre.word.startsWith("飞") || pre.word.startsWith("到")) && !pre.word.startsWith("经")) {
                System.out.println("目的港：" + term.word);
            }
            if (term.nature.startsWith("airp") &&
                    (next.word.startsWith("飞") || next.word.startsWith("到")) && pre.word.startsWith("经")) {
                System.out.println("中转港：" + term.word);
            }
            if (term.nature.startsWith("airl")) {
                System.out.println("航司：" + term.word);
            }
            if (term.nature.startsWith("tp")) {
                System.out.println("包装：" + term.word);
            }
            if (term.nature.startsWith("efnu")) {
                System.out.println("件数：" + pre.word);
            }
            if ("t".equals(term.nature.toString())){
                sb.append(term.word);
            }
            if ("efpr".equals(term.nature.toString())) {
                System.out.println(term.word + "：" + next.word);
            }
            if ("efn".equals(term.nature.toString())) {
                System.out.println(term.word + "：" + next.word);
            }
            if ("efwe".equals(term.nature.toString())) {
                System.out.println(term.word + "：" + next.word);
            }
        }
        System.out.println("订舱航班日期：" + sb);
        //尺寸
        // 使用正则表达式匹配带*号的数字序列
        String pattern = "(\\d+)\\*(\\d+)\\*(\\d+)";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(airline);
        // 构建一个新的字符串，将匹配到的数字序列合并为一个整体
        StringBuilder sizeSb = new StringBuilder();
        while (m.find()) {
            String matched = m.group();
            sizeSb.append(matched);
        }
        // 输出合并后的文本
        System.out.println("尺寸: " + sizeSb);
    }
}

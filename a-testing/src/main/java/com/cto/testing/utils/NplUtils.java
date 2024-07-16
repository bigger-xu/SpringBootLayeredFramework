package com.cto.testing.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;

/**
 * 自然语言处理器
 * @author ZhangYongWei
 * @since 2024/7/15
 */
public class NplUtils {
    public static void main(String[] args) {
        CustomDictionary.reload();
        //List<Term> segment = HanLP.segment("北京翌飞锐特有限公司，2023年12月25日，始发港BJS,目的港CXK。");
        String s = "北京翌飞锐特有限公司2023年12月25日始发港BJS目的港CXK件数3件毛重80公斤尺寸10*10*200体积200方";
        //Segment segment = new ViterbiSegment();
        //segment.enableCustomDictionary(true);
        List<Term> termList = NLPTokenizer.segment(s);
        int size = termList.size();
        for (int i = 0; i < size; i++) {
            Term term = termList.get(i);
            Term nextTerm = i + 1 == size ? termList.get(i): termList.get(i + 1);
            if (term.nature.startsWith("t")) {
                System.out.println("航班日期: " + term.word);
            }
            if (term.nature.startsWith("nt")) {
                System.out.println("客户单位: " + term.word);
            }
            if (term.word.startsWith("始发港")) {
                System.out.println("始发港: " + nextTerm.word);
            }
            if (term.word.startsWith("目的港")) {
                System.out.println("目的港: " + nextTerm.word);
            }
            if (term.word.startsWith("件数")) {
                System.out.println("件数: " + nextTerm.word);
            }
            if (term.word.startsWith("毛重")) {
                System.out.println("毛重: " + nextTerm.word);
            }
            if (term.word.startsWith("体积")) {
                System.out.println("体积: " + nextTerm.word);
            }
        }
        // 使用正则表达式匹配带*号的数字序列
        String pattern = "(\\d+)\\*(\\d+)\\*(\\d+)";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(s);
        // 构建一个新的字符串，将匹配到的数字序列合并为一个整体
        StringBuilder sb = new StringBuilder();
        while (m.find()) {
            String matched = m.group();
            sb.append(matched);
        }
        // 输出合并后的文本
        System.out.println("尺寸: " + sb);
        /*for (Term term : termList) {
            if (term.nature.startsWith("t")) {
                System.out.println("航班日期: " + term.word);
            }
            if (term.nature.startsWith("nt")) {
                System.out.println("客户单位: " + term.word);
            }
            if (term.word.startsWith("始发港")) {
                String substring = s.substring(term.offset + 3, term.offset + 6);
                System.out.println("始发港: " + substring);
            }
            if (term.word.startsWith("目的港")) {
                String substring = s.substring(term.offset + 3, term.offset + 6);
                System.out.println("目的港: " + substring);
            }
            if (term.word.startsWith("件数")) {
                String substring = s.substring(term.offset + 2, term.offset + 3);
                System.out.println("件数: " + substring);
            }
            if (term.word.startsWith("毛重")) {
                String substring = s.substring(term.offset + 2, term.offset + 4);
                System.out.println("毛重: " + substring);
            }
            
            // 输出词和词性
            //System.out.println(term.toString());
        }*/
        //System.out.println(CustomDictionary.get("BJS"));
        
        //Segment s = HanLP.newSegment().enableOrganizationRecognize(true);
        //List<Term> s1 = s.seg("北京翌飞锐特有限公司，2023-12-25，始发港BJS,目的港CXK。");
        //System.out.println(s1);
    }
}

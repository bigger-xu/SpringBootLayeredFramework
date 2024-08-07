package com.cto.testing.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie.IHit;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CoreDictionary.Attribute;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.dictionary.other.CharType;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

/**
 * @author ZhangYongWei
 * @since 2024/7/16
 */
public class Test {
    
    public static void main(String[] args) {
        //CustomDictionary.reload();
        String airline = "华贸北京，深圳宝安飞ORD，7个托盘，800公斤泡到1.1吨，28分，汉莎，12号，三班清，500全包";
        //String airline = "华贸北京，深圳宝安经北京大兴飞ORD，19箱，纸箱，2024年3月18日，香港快运，毛重800，体积重量1000，100*200*40，分泡比4，总价500";
        //CustomDictionary.parseLongestText(airline, (begin, end, value) -> {
        //    //System.out.println(value);
        //    //System.out.printf("[%d:%d]=%s %s\n", begin, end, new String(airline.toCharArray(), begin, end - begin), value);
        //    System.out.println(airline.substring(begin, end));
        //});
        CustomDictionary.add("19[0-9]{2}年[0-1]?[0-9]月[0-3]?[0-9]日", "t 1");
        CustomDictionary.add("[0-9]{1,3}万", "m 1");
        CustomDictionary.add("[0-9]+", "m 1");
        CustomDictionary.parseLongestText(airline, new AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute>() {
            @Override
            public void hit(int begin, int end, Attribute value) {
                System.out.println(airline.substring(begin, end) + value);
            }
        });
        Segment segment = new NShortSegment();
        segment.enableCustomDictionary(true);
        segment.enableCustomDictionaryForcing(true);
        List<Term> termList = segment.seg(airline);
        /*Segment segment = StandardTokenizer.SEGMENT.enableCustomDictionary(true).enableCustomDictionaryForcing(true);
        List<Term> termList = segment.seg(airline);*/
        String params = null;
        int preSize = termList.size();
        for (int i = 0; i < termList.size(); i++) {
            Term pre = i - 1 < 0 ? termList.get(i) : termList.get(i - 1);
            Term term = termList.get(i);
            Term next = i + 1 == preSize ? termList.get(i) : termList.get(i + 1);
            if("q".equals(term.nature.toString()) && "吨".equals(term.word)){
                String word = pre.word;
                BigDecimal b = new BigDecimal(word).multiply(new BigDecimal("1000")).setScale(0, RoundingMode.HALF_UP);
                params = airline.replace(word, b.toString()).replace("吨", "").replace("公斤", "");
            }else{
                params = airline;
            }
        }
        List<Term> newTermList = segment.seg(params);
        //List<Term> segment = NLPTokenizer.segment(airline);
        StringBuilder sb = new StringBuilder();
        int size = newTermList.size();
        for (int i = 0; i < size; i++) {
            Term pre = i - 1 < 0 ? newTermList.get(i) : newTermList.get(i - 1);
            Term term = newTermList.get(i);
            Term next = i + 1 == size ? newTermList.get(i) : newTermList.get(i + 1);
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
            if ("t".equals(term.nature.toString()) && !term.word.contains("分")){
                sb.append(term.word);
            }
            if ("t".equals(term.nature.toString()) && term.word.contains("分")){
                System.out.println("分泡比：" + term.word.charAt(0));
            }
            if ("efpr".equals(term.nature.toString())) {
                System.out.println(term.word + "：" + next.word);
            }
            if ("efwe".equals(term.nature.toString())) {
                System.out.println(term.word + "：" + next.word);
            }
            if ("efpao".equals(term.nature.toString())) {
                System.out.println("毛重：" + pre.word);
                System.out.println("体积重量：" + next.word);
            }
            if ("efy".equals(term.nature.toString())) {
                if("全包".equals(term.word)){
                    System.out.println("运费卖价：" + pre.word + "，总价，" + ("USD".equals(next.word) ? "USD" : "CNY"));
                }else{
                    System.out.println("运费卖价：" + pre.word + "，单价，" + ("USD".equals(next.word) ? "USD" : "CNY"));
                }
            }
            
            
        }
        System.out.println("订舱航班日期：" + sb);
        //尺寸
        // 使用正则表达式匹配带*号的数字序列
        StringBuilder sizeSb = getStringBuilder(airline, "(\\d+)\\*(\\d+)\\*(\\d+)");
        // 输出合并后的文本
        System.out.println("尺寸: " + sizeSb);
    }
    
    private static StringBuilder getStringBuilder(String airline, String pattern) {
        //String pattern = "(\\d+)\\*(\\d+)\\*(\\d+)";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(airline);
        // 构建一个新的字符串，将匹配到的数字序列合并为一个整体
        StringBuilder sizeSb = new StringBuilder();
        while (m.find()) {
            String matched = m.group();
            sizeSb.append(matched);
        }
        return sizeSb;
    }
}

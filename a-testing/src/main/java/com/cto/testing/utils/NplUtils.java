package com.cto.testing.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.dictionary.DynamicCustomDictionary;
import com.hankcs.hanlp.seg.Dijkstra.DijkstraSegment;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;

import org.springframework.util.StringUtils;

/**
 * 自然语言处理器
 * @author ZhangYongWei
 * @since 2024/7/15
 */
public class NplUtils {
    
    public static void main(String[] args) {
        String str = "华贸北京，深圳宝安经上海浦东飞ORD，19件，托盘，2024年3月18号，香港快运，400公斤泡到800公斤，8个方，100*200*40，37分，500O，三车清";
        Map<String, String> segment = segment(str);
        for (Entry<String, String> entry : segment.entrySet()) {
            System.out.println(entry.getKey() + "--:" + entry.getValue());
        }
    }
    
    /**
     * 语义识别
     * @param str 识别字符串
     * @author ZhangYongWei
     * @since 2024/7/18
     * @return CustomOrderReq
     */
    public static Map<String, String> segment(String str){
        Map<String, String> req = new HashMap<>();
        CustomDictionary.reload();
        Segment segment = getSegment();
        List<Term> termList = segment.seg(str);
        String params = null;
        int preSize = termList.size();
        for (int i = 0; i < preSize; i++) {
            Term pre = i - 1 < 0 ? termList.get(i) : termList.get(i - 1);
            Term term = termList.get(i);
            if("efz".equals(term.nature.toString()) && "吨".equals(term.word)){
                String word = pre.word;
                BigDecimal b = new BigDecimal(word).multiply(new BigDecimal("1000")).setScale(0, RoundingMode.HALF_UP);
                params = str.replace(word, b.toString()).replace("吨", "公斤");
            }else if("efz".equals(term.nature.toString()) && !"吨".equals(term.word)){
                params = str.replace(term.word, "公斤");
            }else{
                if(StringUtils.isEmpty(params)){
                    params = str;
                }
            }
        }
        if(!StringUtils.isEmpty(params)) {
            List<Term> newTermList = segment.seg(params);
            //List<Term> segment = NLPTokenizer.segment(airline);
            StringBuilder sb = new StringBuilder();
            List<String> natureList = newTermList.stream().map(o -> o.nature.toString()).collect(Collectors.toList());
            List<String> wordList = newTermList.stream().map(o -> o.word).collect(Collectors.toList());
            int size = newTermList.size();
            for (int i = 0; i < size; i++) {
                Term pre = i - 1 < 0 ? newTermList.get(i) : newTermList.get(i - 1);
                Term term = newTermList.get(i);
                Term next = i + 1 == size ? newTermList.get(i) : newTermList.get(i + 1);
                //客商
                if (term.nature.startsWith("coop")) {
                    req.put("coopName",term.word);
                    wordList.remove(term.word);
                }
                //始发港目的港
                if (term.nature.startsWith("airp") && ("efd".equals(next.nature.toString()) || "efz".equals(next.nature.toString())) && (!"efd".equals(pre.nature.toString()) && !"efz".equals(pre.nature.toString()))) {
                    req.put("departureStation", term.word);
                    wordList.remove(term.word);
                    wordList.remove(next.word);
                }
                if (term.nature.startsWith("airp") && "efd".equals(pre.nature.toString()) && !"efz".equals(pre.nature.toString())) {
                    req.put("arrivalStation", term.word);
                    wordList.remove(term.word);
                    wordList.remove(pre.word);
                }
                if (term.nature.startsWith("airp") && "efd".equals(next.nature.toString()) && "efz".equals(pre.nature.toString())) {
                    req.put("transitStation", term.word);
                    wordList.remove(term.word);
                    wordList.remove(next.word);
                }
                //航司
                if (term.nature.startsWith("airl")) {
                    req.put("carrier", term.word);
                    wordList.remove(term.word);
                }
                //包装类型
                if (term.nature.startsWith("tp")) {
                    req.put("packageTypes", term.word);
                    wordList.remove(term.word);
                }
                //件重体
                if (term.nature.startsWith("efnu")) {
                    req.put("pieces", pre.word);
                    wordList.remove(term.word);
                    wordList.remove(pre.word);
                    if("托".equals(term.word)){
                        req.put("packageTypes", "托盘");
                    }else{
                        req.put("packageTypes", "纸箱");
                    }
                }
                //日期
                if ("eft".equals(term.nature.toString())) {
                    sb.append(pre.word);
                    sb.append(term.word);
                    wordList.remove(term.word);
                    wordList.remove(pre.word);
                }
                //分泡比
                if ("efr".equals(term.nature.toString())) {
                    req.put("incomeBubbleSplitRatio", String.valueOf(pre.word.charAt(0)));
                    wordList.remove(term.word);
                    wordList.remove(pre.word);
                    
                }
                //识别单价总价
                if ("efpr".equals(term.nature.toString())) {
                    System.out.println(term.word + "：" + next.word);
                    wordList.remove(term.word);
                    wordList.remove(next.word);
                }
                //识别件重体
                if ("efwe".equals(term.nature.toString())) {
                    if ("体积".equals(term.word)) {
                        req.put("volume", next.word);
                    } else if ("毛重".equals(term.word)) {
                        req.put("weight", next.word);
                    } else if ("体积重量".equals(term.word)) {
                        req.put("volumeWeight", next.word);
                    }
                    wordList.remove(term.word);
                    wordList.remove(next.word);
                }
                //毛重
                if("efg".equals(term.nature.toString())){
                    if(!natureList.contains("efpao")) {
                        req.put("weight", pre.word);
                        wordList.remove(term.word);
                        wordList.remove(pre.word);
                    }
                }
                //体积
                if("efv".equals(term.nature.toString())){
                    req.put("volume", pre.word);
                    wordList.remove(term.word);
                    wordList.remove(pre.word);
                }
                //分泡件重体
                if ("efpao".equals(term.nature.toString())) {
                    req.put("weight", pre.word.contains("公斤") ? newTermList.get(i - 2).word : pre.word);
                    req.put("volumeWeight", next.word);
                    wordList.remove(term.word);
                    wordList.remove(next.word);
                    wordList.remove(pre.word);
                    if(pre.word.contains("公斤")){
                        wordList.remove(newTermList.get(i - 2).word);
                    }
                    if(newTermList.get(i + 2).word.contains("公斤")){
                        wordList.remove(newTermList.get(i + 2).word);
                    }
                }
                //运费卖价
                if("efc".equals(term.nature.toString())){
                    req.put("freightPrice", pre.word);
                    req.put("freightCurrency" ,"USD,美金".contains(term.word) ? "USD" : "CNY");
                    if ("全包".equals(next.word)) {
                        req.put("freightPriceType", "总价");
                    } else {
                        req.put("freightPriceType", "单价");
                    }
                    wordList.remove(term.word);
                    wordList.remove(next.word);
                    wordList.remove(pre.word);
                }
                //运费卖价
                if ("efy".equals(term.nature.toString())) {
                    if ("全包".equals(term.word)) {
                        //System.out.println("运费卖价：" + pre.word + "，总价，" + ("USD".equals(next.word) ? "USD" : "CNY"));
                        req.put("freightPriceType", "总价");
                    } else {
                        //System.out.println("运费卖价：" + pre.word + "，单价，" + ("USD".equals(next.word) ? "USD" : "CNY"));
                        req.put("freightPriceType", "单价");
                    }
                    req.put("freightPrice", pre.word);
                    req.put("freightCurrency" ,"USD,美金".contains(next.word) ? "USD" : "CNY");
                    wordList.remove(term.word);
                    wordList.remove(next.word);
                    wordList.remove(pre.word);
                }
                //替换所有逗号
                if ("w".equals(term.nature.toString()) && !"*".equals(term.word)) {
                    wordList.remove(term.word);
                }
            }
            String remark = String.join("", wordList);
            String date = sb.toString();
            if (!StringUtils.isEmpty(date)) {
                req.put("flightDate", formatFlightDate(date));
            }
            //尺寸
            // 使用正则表达式匹配带*号的数字序列
            StringBuilder sizeSb = getStringBuilder(params);
            req.put("dimensions", sizeSb.toString());
            //剩余字符是备注
            remark = remark.replace(sizeSb.toString(), "");
            req.put("orderRemark", remark);
        }
        return req;
    }
    
    private static String formatFlightDate(String date) {
        date = date.replace("号", "日");
        SimpleDateFormat formatYearCn = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat formatYear = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatMonthCn = new SimpleDateFormat("MM月dd日");
        SimpleDateFormat formatMonth = new SimpleDateFormat("MM-dd");
        if (date.contains("年")) {
            Date parse;
            try {
                parse = formatYearCn.parse(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            return formatYear.format(parse);
        } else if (date.contains("月")) {
            Date parse;
            try {
                parse = formatMonthCn.parse(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            return LocalDate.now().getYear() + "-" + formatMonth.format(parse);
        } else {
            date = date.replace("日", "");
            LocalDate now = LocalDate.now();
            return now.getYear() + "-" + String.format("%02d", now.getMonth().getValue()) + "-" + date;
        }
    }
    
    private static Segment getSegment() {
        DynamicCustomDictionary dictionary = new DynamicCustomDictionary(
                "D:/HanLP/data/dictionary/custom/航司.txt",
                "D:/HanLP/data/dictionary/custom/航线.txt",
                "D:/HanLP/data/dictionary/custom/客户.txt",
                "D:/HanLP/data/dictionary/custom/包装类型.txt",
                "D:/HanLP/data/dictionary/custom/其他关键词.txt");
        Segment segment = new DijkstraSegment();
        //segment.enableCustomDictionary(true);
        segment.enableCustomDictionaryForcing(true);
        segment.enableCustomDictionary(dictionary);
        return segment;
    }
    
    private static StringBuilder getStringBuilder(String airline) {
        Pattern p = Pattern.compile("(\\d+)\\*(\\d+)\\*(\\d+)");
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

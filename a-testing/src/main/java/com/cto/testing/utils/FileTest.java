package com.cto.testing.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.seg.common.Term;

/**
 * @author ZhangYongWei
 * @since 2024/7/16
 */
public class FileTest {
    
    public static void main(String[] args) {
        /*String inputFilePath = "D:\\HanLP\\data\\dictionary\\custom\\客商语料.txt";
        String outputFilePath = "D:\\HanLP\\data\\dictionary\\custom\\out客户.txt";
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath));
            String line;
            while ((line = br.readLine()) != null) {
                // 写入文件，包括换行符
                line = line.replaceAll("-", "").replaceAll("（", "").replaceAll("）", "");
                //line += " coop 1024";
                if(line.startsWith("港中旅华贸国际")){
                    System.out.println(line);
                }
                //List<Term> termList = NLPTokenizer.segment(line);
                //for (Term term : termList) {
                //    System.out.println(term.toString());
                //}
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        System.out.println(convertChineseQuantityToNumber("货物重量是一千两百一十一个"));
    }
    
    private static String formatFlightDate(String date) {
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
            return now.getYear() + "-" + now.getMonth().getValue() + "-" + date;
        }
    }
    
    public static int chineseNumberToArabic(String chineseNumber) {
        int result = 0;
        int tempNumber = 0;
        int section = 0;
        int multi = 1;
        
        String[] chineseDigits = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九" ,"两"};
        String[] chineseUnits = {"", "十", "百", "千"};
        String[] chineseBigUnits = {"", "万", "亿", "兆"};
        
        for (int i = 0; i < chineseNumber.length(); i++) {
            boolean bFound = false;
            for (int j = 0; j < chineseDigits.length; j++) {
                if (chineseNumber.substring(i, i + 1).equals(chineseDigits[j])) {
                    if("两".equals(chineseNumber.substring(i, i + 1))){
                        tempNumber = 2;
                    }else{
                        tempNumber = j;
                    }
                    bFound = true;
                    break;
                }
            }
            
            if (bFound) {
                section = section * 10 + tempNumber;
            } else {
                for (int j = 0; j < chineseUnits.length; j++) {
                    if (chineseNumber.substring(i, i + 1).equals(chineseUnits[j])) {
                        section = section == 0 ? 1 : section;
                        result += section * (int) Math.pow(10, j);
                        section = 0;
                        break;
                    }
                }
                
                for (int j = 0; j < chineseBigUnits.length; j++) {
                    if (chineseNumber.substring(i, i + 1).equals(chineseBigUnits[j])) {
                        result = result + section * multi;
                        section = 0;
                        multi = (int) Math.pow(10000, j + 1);
                        break;
                    }
                }
            }
        }
        
        result += section * multi;
        return result;
    }
    
    public static String convertChineseQuantityToNumber(String text) {
        StringBuilder result = new StringBuilder();
        List<Term> termList = HanLP.segment(text);
        
        for (Term term : termList) {
            if (term.nature == Nature.m) {
                // 替换量词为对应的阿拉伯数字
                result.append(chineseNumberToArabic(term.word));
            } else {
                result.append(term.word);
            }
        }
        return result.toString();
    }
}

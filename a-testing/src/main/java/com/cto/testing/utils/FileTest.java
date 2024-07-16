package com.cto.testing.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author ZhangYongWei
 * @since 2024/7/16
 */
public class FileTest {
    
    public static void main(String[] args) {
        String inputFilePath = "D:\\HanLP\\data\\dictionary\\custom\\客户.txt";
        String outputFilePath = "D:\\HanLP\\data\\dictionary\\custom\\out客户.txt";
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath));
            String line;
            while ((line = br.readLine()) != null) {
                // 写入文件，包括换行符
                line = line.replaceAll(" ", "");
                line += " coop 1024";
                System.out.println(line);
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.efreight.oauth2.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import com.efreight.common.response.MessageInfo;
import com.efreight.common.utils.RedisUtil;
import com.efreight.hrs.constants.HrsConstants;
import com.google.common.collect.Maps;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 马玉龙
 * @since 2023/8/10
 */
@Slf4j
@RestController
@Tag(name = "image验证码")
@RequestMapping("/image")
public class ImageCodeController {

    @Autowired
    private RedisUtil redisUtil;


    /**
     * 获取imageToken
     */
    @PostMapping("/token")
    @Operation(summary = "获取imageToken")
    public MessageInfo<Map<String, String>> imageToken() {
        Map<String, String> map = Maps.newHashMap();
        map.put("imageToken", UUID.randomUUID().toString());
        return MessageInfo.ok(map);
    }

    /**
     * 获取验证码图片
     */
    @PostMapping("/getImage")
    @Operation(summary = "获取验证码图片")
    public void getImage(HttpServletRequest request, HttpServletResponse response) {
        BufferedImage bufferedImage1 = getRandomVerificationCodeImage(request);
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(bufferedImage1, "jpg", outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            log.error("生成图片验证码失败", e);
            throw new RuntimeException("生成图片验证码失败");
        }
    }
    //生成白底黑字
    public BufferedImage createImage(HttpServletRequest request) {
        String[] codeSequence = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        int width = 150;
        int height = 50;
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D gd = buffImg.createGraphics();
        Random random = new Random();
        gd.setColor(Color.WHITE);
        gd.fillRect(0, 0, width, height);
        int fontHeight = height - 10;
        Font font = new Font("微软雅黑", Font.PLAIN, fontHeight);
        gd.setFont(font);
        gd.setColor(Color.BLACK);
        gd.drawRect(0, 0, width - 1, height - 1);
//        gd.setColor(Color.RED);
//        for (int i = 0; i < 16; i++) {
//            int x = random.nextInt(width);
//            int y = random.nextInt(height);
//            int xl = random.nextInt(12);
//            int yl = random.nextInt(12);
//            gd.drawLine(x, y, x + xl, y + yl);
//        }
        int codeX = (width - 4) / (4 + 1);
        int codeY = height - 7;

        int red = 0, green = 0, blue = 0;
        StringBuilder captchaText = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String strRand = codeSequence[random.nextInt(codeSequence.length)];
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            gd.setColor(Color.BLACK);
            gd.drawString(strRand, (i + 1) * codeX-10, codeY);
            captchaText.append(strRand);
        }
        String imageToken = request.getParameter("imageToken");
        String imageKey = String.format(HrsConstants.IMAGE_KEY, imageToken);
        //1分钟过期
        redisUtil.set(imageKey, captchaText.toString(), 1, TimeUnit.MINUTES);
        return buffImg;
    }

    //生成指定颜色
    public BufferedImage getRandomVerificationCodeImage(HttpServletRequest request){
        Random random = new Random();
        int width = 150;
        int height = 40;
        BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bImage.getGraphics();
        graphics.fillRect(0, 0, width, height); //将在整个图形上操作，而不是某一部分
        int codeLength = 4;
        String codeFamily = "微软雅黑";
        int codeSize = 40;
        int codeWidth = width / codeLength;
        char[] codeElements = "1234567890".toCharArray();
        StringBuffer codeBuffer = new StringBuffer();
        Color color = new Color(72, 112, 87);//深绿
        Color color2 = new Color(104, 37, 150);//深紫
        Color color3 = new Color(13, 45, 165);//深蓝色
        Color color4 = new Color(0, 0, 0);//黑色
        for (int i = 0; i < codeLength; i++) {
            char ch = codeElements[random.nextInt(codeElements.length)];
            if(i==0){
                graphics.setColor(color);
            }
            if(i==1){
                graphics.setColor(color2);
            }
            if(i==2){
                graphics.setColor(color3);
            }
            if(i==3){
                graphics.setColor(color4);
            }
            graphics.setFont(new Font(codeFamily,Font.PLAIN,codeSize));
            graphics.drawString(ch + "", i * (codeWidth) + (codeWidth - codeSize) / 2,  (height + codeSize) / 2);
            codeBuffer.append(ch);
        }
        String code = codeBuffer.toString();

        double noiseRatio = 0.08; //噪点比率
        int noiseCount = (int) (noiseRatio * width * height); //噪点个数
        for (int i = 0; i < noiseCount; i++) {
            bImage.setRGB(random.nextInt(width), random.nextInt(height), random.nextInt());
        }
        int lineCount = 4;
        int x_start, x_end, y_start, y_end;
        for (int i = 0; i < lineCount; i++) {
            x_start = random.nextInt(width);
            x_end = random.nextInt(width);
            y_start = random.nextInt(height);
            y_end = random.nextInt(height);
            graphics.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            graphics.drawLine(x_start, y_start, x_end, y_end);
        }
        String imageToken = request.getParameter("imageToken");
        String imageKey = String.format(HrsConstants.IMAGE_KEY, imageToken);
        //1分钟过期
        redisUtil.set(imageKey, code, 1, TimeUnit.MINUTES);
        return bImage;
    }
}

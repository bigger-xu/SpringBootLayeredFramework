package com.cto.freemarker.utils.sub;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * @author Bigger-Xu
 * @version v1.0.1
 * @date 2020/9/26 22:40
 */
public class SubImg {
    /**
     * 算术表达式验证码
     * <p>
     * 1.干扰线的产生
     * 2.范围随机颜色，随机数
     *
     * @param outputStream 输出
     * @return
     */
    public static String drawImageVerifyCate(OutputStream outputStream) {
        //定义验证码的宽度和高度
        int width = 110;
        int height = 32;
        //在内存中创建图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //创建图片上下文
        Graphics2D g = image.createGraphics();
        //产生随机对象，此随机对象主要用于算术表达式的数字
        Random random = new Random();
        //设置背景
        g.setColor(getRandomColor(240, 250));
        //设置字体
        g.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        //开始绘制
        g.fillRect(0, 0, width, height);

        //干扰线的绘制，绘制干扰线到图片中
        g.setColor(getRandomColor(180, 230));
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(60);
            int y1 = random.nextInt(60);
            g.drawLine(x, y, x1, y1);
        }

        //开始进行对算术验证码表达式的拼接
        int num1 = (int) (Math.random() * 10 + 1);
        int num2 = (int) (Math.random() * 10 + 1);
        int fuhao = random.nextInt(3);//产生一个0-2之间的随机整数
        //记录符号
        String fuhaostr = null;
        int result = 0;
        switch (fuhao) {
            case 0:
                fuhaostr = "+";
                result = num1 + num2;
                break;
            case 1:
                fuhaostr = "-";
                result = num1 - num2;
                break;
            case 2:
                fuhaostr = "×";
                result = num1 * num2;
                break;
        }
        //拼接算术表达式，用户图片显示
        String calc = num1 + " " + fuhaostr + " " + num2 + " = ?";
        //设置随机颜色
        g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
        //绘制表达式
        g.drawString(calc, 15, 25);
        //结束绘制
        try {
            //输出图片
            ImageIO.write(image, "jpg", outputStream);
            return String.valueOf(result);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 范围随机颜色
     *
     * @param fc
     * @param bc
     * @return
     */
    public static Color getRandomColor(int fc, int bc) {
        //利用随机数
        Random random = new Random();
        //随机颜色，了解颜色-Color(red,green,blue).rgb三元色0-255
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }


    public static void main(String[] args) {
        //System.out.println(calc("8*8"));
        for (int i = 0; i < 1000; i++) {
            System.out.println((int) (Math.random() * 100 + 1));
        }
    }
}

package com.cto.freemarker.utils.vcode;

/**
 * @author Zhangyongwei
 */
public class ValidateCodeProperties {

    // 验证码图片宽度
    private static int width = 160;
    // 验证码图片高度
    private static int height = 50;
    // 验证码字符位数
    private static int length = 4;

    public static int getWidth() {
        return width;
    }
    public static int getHeight() {
        return height;
    }
    public static int getLength() {
        return length;
    }

}

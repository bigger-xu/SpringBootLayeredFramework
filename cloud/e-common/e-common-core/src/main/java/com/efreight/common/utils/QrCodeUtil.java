package com.efreight.common.utils;

import java.io.InputStream;
import java.io.OutputStream;

import cn.hutool.extra.qrcode.QrConfig;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码工具类
 * 参考：https://www.hutool.cn/docs/#/extra/%E4%BA%8C%E7%BB%B4%E7%A0%81%E5%B7%A5%E5%85%B7-QrCodeUtil
 *
 * @author Libiao
 */
public class QrCodeUtil {
    
    public static final String JPG = ".jpg";
    
    /**
     * 生成二维码到输出流
     *
     * @param content 文本内容
     * @param width   宽度
     * @param height  高度
     * @param out     目标流
     */
    public static void generate(String content, int width, int height, OutputStream out) {
        QrConfig config = new QrConfig(width, height);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(2);
        // 高纠错级别
        config.setErrorCorrection(ErrorCorrectionLevel.H);
        // 生成二维码到流
        cn.hutool.extra.qrcode.QrCodeUtil.generate(content, config, JPG.substring(1), out);
    }
    
    /**
     * 解码二维码或条形码图片为文本
     *
     * @param qrCodeInputStream 二维码输入流
     * @return 解码文本
     */
    public static String decode(InputStream qrCodeInputStream) {
        return cn.hutool.extra.qrcode.QrCodeUtil.decode(qrCodeInputStream);
    }
    
}

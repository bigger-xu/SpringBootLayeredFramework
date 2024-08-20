package com.efreight.hrs.config;

import java.util.Properties;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码配置
 */
@Configuration
public class KaptchaConfig {

    @Bean
    @Qualifier("kaptchaProducer")
    public DefaultKaptcha getDefaultKaptcha() {
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty(Constants.KAPTCHA_BORDER,"no");
        //验证码个数
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
        //字体间隔
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE, "10");
        //干扰线颜色

        //干扰实现类
        properties.setProperty(Constants.KAPTCHA_NOISE_IMPL, "com.google.code.kaptcha.impl.NoNoise");

        //图片样式
        properties.setProperty(Constants.KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.ShadowGimpy");

        //文字来源
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING, "0123456789");

        properties.setProperty(Constants.KAPTCHA_BACKGROUND_CLR_FROM,"127,255,212");

        properties.setProperty(Constants.KAPTCHA_BACKGROUND_CLR_TO,"240,255,255");

        properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH,"150");
        Config config = new Config(properties);
        kaptcha.setConfig(config);
        return kaptcha;

    }

}
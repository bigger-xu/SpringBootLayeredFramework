package com.efreight.common.utils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

/**
 * 报文加密解密
 *
 * @author 张永伟
 * @since 2023/9/6
 */
public class RsaUtil {
    
    public static final String KEY_ALGORITHM = "RSA";
    
    private static final String DEFAULT_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJY+zRbKWjvjrwrrmWtXsPASQYXowkXUnGRtdIW7zEQkY9l820MoPuRPVIdeqPfqDFTwGXCyH+bc4zgSNkVFMz7/kRGzZXPzc05vHaz6LkfD1kpvk8j5raSm/F8+IZ+0xTznyb/bY0RnLqUGoYrqGDE+ypE5VGkHzocA7ysLr/K9AgMBAAECgYAdvFy1YcO1njSu7d2trcueXBKdSOmoTfHM+4/IBxjgtK4RZaF0u3KlF6FUcHLzLB6YMfPrBsoDFF0hJBObJpzDUcpn1LsqGkOJac6mpm3YEw8Jr3b3nWWXeWhm1/5612fdFi/WIJ4f9ghCL6dyp2IXOAHoY3gNpyy+VXiL/5m4iQJBAMRac1fBuN/f74rKJjhzXvAQ/dcam11tbwBnF9xnVaMLsnNww8DBDGNrNwJu0HAyKU+4aS72C0oxrOOuCIfVLEUCQQDD4rrvHMY+cizJ1BZW+/Tptzjb3IGtOGa8ZkpmCekV1LVlSPofFE4qgxk6rHzuONCLt/8HsMT9IebnhMXtXiAZAkBg8l/cxnNnZgO5vRGd8ajAU0Nhv2tSd8PEqSIo2oR32kCjqtucAjUBqWAhvWEA5GNtTxnv69+rJFe+mqDvoBsZAkEAudcBQpkp+1SW6WQtTZj8Yj9R0kUBQjWgQl1JeEUuvWdwMQdrRNEe13ZJGUFLxTZhpCyL59/tA7qGNl/g57HFAQJAS937Qb9lF8liv/8kfnQsTImlsOdIhvfW/Ydf3AKpGd6Lk9uhR98fq0T8v4jrXnDeTBkSGiudupoqjuyiR1iAlA==";
    
    private static final String DEFAULT_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCWPs0Wylo7468K65lrV7DwEkGF6MJF1JxkbXSFu8xEJGPZfNtDKD7kT1SHXqj36gxU8Blwsh/m3OM4EjZFRTM+/5ERs2Vz83NObx2s+i5Hw9ZKb5PI+a2kpvxfPiGftMU858m/22NEZy6lBqGK6hgxPsqROVRpB86HAO8rC6/yvQIDAQAB";
    
    public static String encryptBase64(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }
    
    public static byte[] decryptByPrivateKey(String data, String key) throws Exception {
        return decryptByPrivateKey(decryptBase64(data), key);
    }
    
    public static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception {
        byte[] keyBytes = decryptBase64(key);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(2, privateKey);
        return decryptSegment(cipher, data);
    }
    
    public static byte[] decryptBase64(String key) {
        return Base64.decodeBase64(key);
    }
    
    private static byte[] decryptSegment(Cipher cipher, byte[] data) throws Exception {
        if (data.length > 128) {
            byte[] encryptData = new byte[0];
            int mod = data.length % 128;
            int segmentNumber = data.length / 128 + (mod == 0 ? 0 : 1);
            for (int i = 0; i < segmentNumber; ++i) {
                byte[] segmentData;
                byte[] cipherData;
                byte[] newCipherData;
                if (i < segmentNumber - 1) {
                    segmentData = new byte[128];
                    System.arraycopy(data, i * 128, segmentData, 0, 128);
                    cipherData = cipher.doFinal(segmentData);
                    newCipherData = new byte[encryptData.length + cipherData.length];
                    System.arraycopy(encryptData, 0, newCipherData, 0, encryptData.length);
                    System.arraycopy(cipherData, 0, newCipherData, encryptData.length, cipherData.length);
                    encryptData = newCipherData;
                } else {
                    mod = mod == 0 ? 128 : mod;
                    segmentData = new byte[mod];
                    System.arraycopy(data, i * 128, segmentData, 0, mod);
                    cipherData = cipher.doFinal(segmentData);
                    newCipherData = new byte[encryptData.length + cipherData.length];
                    System.arraycopy(encryptData, 0, newCipherData, 0, encryptData.length);
                    System.arraycopy(cipherData, 0, newCipherData, encryptData.length, cipherData.length);
                    encryptData = newCipherData;
                }
            }
            return encryptData;
        } else {
            return cipher.doFinal(data);
        }
    }
    
    public static byte[] decryptByPublicKey(String data, String key) throws Exception {
        byte[] keyBytes = decryptBase64(key);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(2, publicKey);
        return decryptSegment(cipher, data.getBytes());
    }
    
    public static byte[] decryptByPublicKey(byte[] data, String key) throws Exception {
        byte[] keyBytes = decryptBase64(key);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(2, publicKey);
        return decryptSegment(cipher, data);
    }
    
    public static String encryptByPublicKey(String data, String key) throws Exception {
        byte[] keyBytes = decryptBase64(key);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(1, publicKey);
        return new String(encryptSegment(cipher, data.getBytes()));
    }
    
    private static byte[] encryptSegment(Cipher cipher, byte[] data) throws Exception {
        if (data.length > 64) {
            byte[] encryptData = new byte[0];
            int mod = data.length % 64;
            int segmentNumber = data.length / 64 + (mod == 0 ? 0 : 1);
            for (int i = 0; i < segmentNumber; ++i) {
                byte[] segmentData;
                byte[] cipherData;
                byte[] newCipherData;
                if (i < segmentNumber - 1) {
                    segmentData = new byte[64];
                    System.arraycopy(data, i * 64, segmentData, 0, 64);
                    cipherData = cipher.doFinal(segmentData);
                    newCipherData = new byte[encryptData.length + cipherData.length];
                    System.arraycopy(encryptData, 0, newCipherData, 0, encryptData.length);
                    System.arraycopy(cipherData, 0, newCipherData, encryptData.length, cipherData.length);
                    encryptData = newCipherData;
                } else {
                    mod = mod == 0 ? 64 : mod;
                    segmentData = new byte[mod];
                    System.arraycopy(data, i * 64, segmentData, 0, mod);
                    cipherData = cipher.doFinal(segmentData);
                    newCipherData = new byte[encryptData.length + cipherData.length];
                    System.arraycopy(encryptData, 0, newCipherData, 0, encryptData.length);
                    System.arraycopy(cipherData, 0, newCipherData, encryptData.length, cipherData.length);
                    encryptData = newCipherData;
                }
            }
            return encryptData;
        } else {
            return cipher.doFinal(data);
        }
    }
    
    public static byte[] encryptByPrivateKey(byte[] data, String key) throws Exception {
        byte[] keyBytes = decryptBase64(key);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(1, privateKey);
        return encryptSegment(cipher, data);
    }
    
    public static String getUtf8String(byte[] data) {
        return new String(data, StandardCharsets.UTF_8);
    }
    
    public static byte[] getUtf8Bytes(String data) {
        return StringUtils.isEmpty(data) ? new byte[0] : data.getBytes(StandardCharsets.UTF_8);
    }
    
    /**
     * Rsa BMS接口默认解密
     *
     * @param publicKey  公钥
     * @param privateKey 私钥
     * @param data       加密串
     * @return String 解密后的数据
     * @since 2023/9/7
     */
    public static String bmsApiDecryptByPublicKey(String publicKey, String privateKey, String data) {
        RSA rsa = new RSA(privateKey, publicKey);
        return rsa.decryptStr(data, KeyType.PublicKey);
    }
    
    /**
     * Rsa BMS接口默认加密
     *
     * @param publicKey  公钥
     * @param privateKey 私钥
     * @param data       加密串
     * @return String 加密后的数据
     * @since 2023/9/7
     */
    public static String bmsApiEncryptByPrivateKey(String publicKey, String privateKey, String data) {
        RSA rsa = new RSA(privateKey, publicKey);
        return rsa.encryptBase64(data, KeyType.PrivateKey);
    }
    
    
    /**
     * 全局默认解密方法
     *
     * @param data 解密字符串
     * @return String
     * @since 2023/9/19
     */
    public static String defaultDecryptByPublicKey(String data) {
        RSA rsa = new RSA(DEFAULT_PRIVATE_KEY, DEFAULT_PUBLIC_KEY);
        return rsa.decryptStr(data, KeyType.PublicKey);
    }
    
    /**
     * 全局默认加密方法
     *
     * @param data 加密字符串
     * @return String
     * @since 2023/9/19
     */
    public static String defaultEncryptByPrivateKey(String data) {
        RSA rsa = new RSA(DEFAULT_PRIVATE_KEY, DEFAULT_PUBLIC_KEY);
        return rsa.encryptBase64(data, KeyType.PrivateKey);
    }
    
    
/*    public static void main(String[] args) {
        //System.out.println(defaultEncryptByPrivateKey("atis@2023"));
        *//*for (int i = 0; i < 10; i++) {
            String s = defaultDecryptByPublicKey("hZNDOUpoeHQ39XtsoXps0jys8U0czhTGp5XH5J5Gb7BOUlAyVFFJr4dHQeYS97rkJx8XeBVKrCjdGfeD4B0W0dhCWivGtujpHhpKJfFRhNBIvW1FfT67mrHIg/WrOTmeTr8gpa0QkyS4YQsNG1ORe2v/9YipiWd3iryTDgbDwk8=");
            System.out.println(s);
        }*//*
        //for (int i = 0; i < 48; i++) {
        //    System.out.println(IdWorker.getId());
        //
        //}
        System.out.println(BigDecimal.ONE);
    }*/
    
}

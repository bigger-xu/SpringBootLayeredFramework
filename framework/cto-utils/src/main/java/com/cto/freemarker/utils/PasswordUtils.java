package com.cto.freemarker.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 密码工具类
 * @author Zhang Yongwei
 * @date 2019-03-20
 * @version 1.0
 */
public class PasswordUtils {

    /**
     * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
     */
    public static String generateSalt() {
        return (int)(Math.random() * 9000 + 1000)+"";
    }
    /**
     * 生成盐值
     */
    public static String entryptPassword(String plainPassword,String saltStr) {
        String passwordCipherText= new Md5Hash(plainPassword,saltStr,1).toHex();
        return passwordCipherText;
    }

    /**
     * 验证密码
     * @param plainPassword 明文密码
     * @param password 密文密码
     * @return 验证成功返回true
     */
    public static boolean validatePassword(String plainPassword,String saltStr, String password) {
        String newPassword = entryptPassword(plainPassword,saltStr);
        return password.equals(newPassword);
    }
}


package com.efreight.hrs.util;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import lombok.Data;

/**
 * @author 马玉龙
 * @since 2024/1/15
 */
@Data
public class PassGenerator {
    public static final String reg = "^(?=.*[0-9])(?=.*[!@#$%^&*()\\-_+=\\[\\]{}|\\\\:;\"',.<>/?])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
    private static final String SPECIAL_CHARS = "!@#$%^&*()_-+=[]{}|\\:;\",.<>/?'";
    private static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";

    /**
     * 正则匹配8位数必须包含
     *
     * @param input
     * @return
     */
    public static boolean match(String input) {
        return Pattern.matches(reg, input);
    }


    public static String generatePassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        password.append(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));

        password.append(LOWERCASE_CHARS.charAt(random.nextInt(LOWERCASE_CHARS.length())));

        password.append(UPPERCASE_CHARS.charAt(random.nextInt(UPPERCASE_CHARS.length())));

        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));

        for (int i = 0; i < 4; i++) {
            String allChars = LOWERCASE_CHARS + UPPERCASE_CHARS + DIGITS + SPECIAL_CHARS;
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }
        String shuffledPassword = shuffleString(password.toString());
        return shuffledPassword.substring(0, 8);
    }

    private static String shuffleString(String input) {
        List<String> originalList = Arrays.stream(input.split("")).collect(Collectors.toList());
        Collections.shuffle(originalList);
        return originalList.stream().map(Objects::toString).collect(Collectors.joining(""));
    }

}

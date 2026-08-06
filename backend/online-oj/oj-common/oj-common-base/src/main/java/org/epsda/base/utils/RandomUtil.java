package org.epsda.base.utils;

import java.security.SecureRandom;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: EPSDA
 * Date: 2026/08/06
 * Time: 13:48
 * Package Name: org.epsda.base.utils
 * Project Name: online-oj
 */
public class RandomUtil {

    private static final String VALID_CHARACTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String USERNAME_PREFIX = "user_";

    private static String generateRandomString() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            password.append(VALID_CHARACTERS
                    .charAt(random.nextInt(VALID_CHARACTERS.length())));
        }
        return password.toString();
    }

    public static String generateRandomUsername() {
        return USERNAME_PREFIX + generateRandomString();
    }

    public static String generateRandomPassword() {
        return generateRandomString();
    }
}

package com.zosh.config.AppConfig;

import java.security.SecureRandom;
import java.util.Base64;

public class JwtConstant {

//    public static final String SECREATE_KEY = "ffdmkaljziofjqf jfalmkldfjkajfkdjalfja lkfjlfjkdalf";
//    public static final String JWT_HEADER = "Authorization";

    private static final int KEY_LENGTH = 256; // 256-bit key
    private static final SecureRandom secureRandom = new SecureRandom();

    public static final String SECREATE_KEY = generateKey();
    public static final String JWT_HEADER = "Authorization";

    private static String generateKey() {
        byte[] key = new byte[KEY_LENGTH / 8];
        secureRandom.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }
}

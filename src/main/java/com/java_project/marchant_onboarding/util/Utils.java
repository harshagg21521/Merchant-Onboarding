package com.java_project.marchant_onboarding.util;
import org.apache.commons.lang3.RandomStringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.UUID;

public class Utils {
    public static String generateId() {
        return  RandomStringUtils.randomAlphabetic(3).toUpperCase()+ RandomStringUtils.randomNumeric(3);

    }

    public static String generatePassword(){
        String password=RandomStringUtils.randomAlphanumeric(6,10);
        return password;
    }
    public static String encrypt(String str) {
        byte[] hash=str.getBytes();
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }



}

package com.epam.finalproject.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {

    private static Logger logger = LogManager.getLogger();
    private final static String SHA_1 = "SHA-1";
    private final static String UTF_8 = "utf-8";

    /**
     * Method for encoding user password
     * @param password - user password value
     * @return text
     */

    public static String encode(String password) {
        byte[] bytes = null;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance(SHA_1);
            messageDigest.update(password.getBytes(UTF_8));
            bytes = messageDigest.digest();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            logger.error(e);
        }
        BigInteger bigInteger = new BigInteger(1, bytes);
        String encrypted = bigInteger.toString(16);
        return encrypted;
    }
}
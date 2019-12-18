package com.epam.finalproject.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputInfoValidator {

    private final static String REGEX_LOGIN_AND_PASSWORD = "[a-zA-Z0-9]{5,20}";
    private final static String REGEX_NAME = "[a-zA-Zа-яА-я]{1,20}";
    private final static String REGEX_EMAIL = "\\S{1,20}@gmail.com";
    private final static String REGEX_CARD_CODE = "[0-9]{4}";
    private final static String REGEX_CVC_CARD_CODE = "[0-9]{3}";

    /**
     * Method for validation credit card value
     * @param code - card code value
     * @return boolean type
     */

    public static boolean isCardCodeValid(String code) {
        Pattern pattern = Pattern.compile(REGEX_CARD_CODE);
        Matcher matcher = pattern.matcher(code);
        return matcher.matches();
    }

    /**
     * Method for validation credit card value
     * @param code - card code value
     * @return boolean type
     */

    public static boolean isCardCvcValid(String code) {
        Pattern pattern = Pattern.compile(REGEX_CVC_CARD_CODE);
        Matcher matcher = pattern.matcher(code);
        return matcher.matches();
    }

    /**
     * Method for validation user password and login
     * @param string - user login or password
     * @return boolean type
     */

    public static boolean isLoginOrPasswordCorrect(String string) {
        Pattern pattern = Pattern.compile(REGEX_LOGIN_AND_PASSWORD);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    /**
     * Method for checking repeated password
     * @param firstPassword - user password
     * @param secondPassword - repeat password
     * @return boolean type
     */

    public static boolean isPasswordsMatch(String firstPassword, String secondPassword) {
        return firstPassword.equals(secondPassword);
    }

    /**
     * Method for validation user name
     * @param string - user first name
     * @return boolean type
     */

    public static boolean iskNameCorrect(String string) {
        Pattern pattern = Pattern.compile(REGEX_NAME);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    /**
     * Method for validation user last name
     * @param string - user last name
     * @return boolean type
     */

    public static boolean isEmailCorrect(String email) {
        Pattern pattern = Pattern.compile(REGEX_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
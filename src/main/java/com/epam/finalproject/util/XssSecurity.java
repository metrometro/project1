package com.epam.finalproject.util;

public class XssSecurity {

    private final static String LEFT_TAG = "<";
    private final static String RIGHT_TAG = ">";
    private final static String REPLACING_LEFT_TAG = "&lt";
    private final static String REPLACING_RIGHT_TAG = "&gt";

    /**
     * Xss hhh attack protection method
     * @param text - text value
     * @return encoded password
     */

    public static String protectFromXssAttack(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        text.replaceAll(LEFT_TAG, REPLACING_LEFT_TAG).replaceAll(RIGHT_TAG, REPLACING_RIGHT_TAG);
        return text;
    }
}
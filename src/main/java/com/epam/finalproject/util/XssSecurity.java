package com.epam.finalproject.util;

public class XssSecurity {

    private final static String LEFT_TAG = "<";
    private final static String RIGHT_TAG = ">";
    private final static String REPLACING_LEFT_TAG = "(lt)";
    private final static String REPLACING_RIGHT_TAG = "(rt)";

    public static String protectFromXssAttack(String string) {
        return string == null ? null : string.replaceAll(LEFT_TAG, REPLACING_LEFT_TAG).replaceAll(RIGHT_TAG, REPLACING_RIGHT_TAG);
    }
}

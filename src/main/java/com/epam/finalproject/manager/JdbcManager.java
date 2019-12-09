package com.epam.finalproject.manager;

import java.util.ResourceBundle;

public class JdbcManager {

    private final static String JDBC = "jdbc";
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("jdbc");

    private JdbcManager() {}

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
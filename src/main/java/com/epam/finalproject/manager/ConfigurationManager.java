package com.epam.finalproject.manager;

import java.util.ResourceBundle;

public class ConfigurationManager {

    private final static String CONFIG = "config";
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(CONFIG);

    private ConfigurationManager() {}

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
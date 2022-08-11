package com.my.util;

import java.util.ResourceBundle;

public class ConfigManager {
    private final static ResourceBundle bundle = ResourceBundle.getBundle("config");
    private ConfigManager(){}
    public static String getProperty(String key) {
        return bundle.getString(key);
    }
}

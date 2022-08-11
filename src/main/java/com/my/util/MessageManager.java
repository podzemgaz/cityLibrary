package com.my.util;

import java.util.ResourceBundle;

public class MessageManager {
    private final static ResourceBundle bundle = ResourceBundle.getBundle("messages");
    private MessageManager() {}
    public static String getProperty(String key) {
        return bundle.getString(key);
    }
}

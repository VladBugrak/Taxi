package com.taxiservice.utils.BundleManagers;

import java.util.ResourceBundle;

public class ViewManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("view");
    private ViewManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}

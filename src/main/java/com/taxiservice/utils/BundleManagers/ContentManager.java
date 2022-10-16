package com.taxiservice.utils.BundleManagers;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Locale;

public class ContentManager {

    private static final Map<String, ResourceBundle> BUNDLE_MAP = new HashMap<>();

    static {
        BUNDLE_MAP.put("ua", ResourceBundle.getBundle("content", Locale.forLanguageTag("ua")));
        BUNDLE_MAP.put("en", ResourceBundle.getBundle("content"));
        BUNDLE_MAP.put(null, ResourceBundle.getBundle("content"));
    }

    private ContentManager() {
    }

    public static String getProperty(String key) {
        return BUNDLE_MAP.get(null).getString(key);
    }

    public static String getProperty(String key, String locale) {
        return BUNDLE_MAP.getOrDefault(locale, BUNDLE_MAP.get(null))
                .getString(key);
    }


}

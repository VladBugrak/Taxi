package com.taxiservice.utils;

import java.util.Locale;
import java.util.Optional;

public class LocaleThreadLocal {
    private static final ThreadLocal<Locale> threadLocalScope = new  ThreadLocal<>();

    public static Locale getLocale() {
        return Optional.ofNullable(threadLocalScope.get()).orElse(Locale.getDefault());
    }

    public static void setLocale(Locale locale) {
        threadLocalScope.set(locale);
    }

}

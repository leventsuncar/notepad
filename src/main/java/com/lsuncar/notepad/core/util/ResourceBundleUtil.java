package com.lsuncar.notepad.core.util;

import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class ResourceBundleUtil {

    public String getLocaleText(String locale, String key) {
        Locale localeObj = new Locale(locale);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("locale", localeObj);
        return resourceBundle.getString(key);
    }

    public String getLocaleText(String key) {
        Locale localeObj = new Locale("tr_TR");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("locale", localeObj);
        return resourceBundle.getString(key);
    }
}

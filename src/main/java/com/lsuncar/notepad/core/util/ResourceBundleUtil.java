package com.lsuncar.notepad.core.util;

import com.lsuncar.notepad.core.exception.ResourceBundleNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@Component
public class ResourceBundleUtil {

    @Value("${languages}")
    private String languages;

    private static Map<String, ResourceBundle> bundles = new HashMap<>();

    @PostConstruct
    private void init() {
        String[] languagesArray = languages.split(",");
        for (String language : languagesArray) {
            Locale locale = new Locale(language);
            ResourceBundle bundle = ResourceBundle.getBundle("locale", locale);
            bundles.put(language, bundle);
        }
    }

    private static ResourceBundle getBundle(String locale) throws ResourceBundleNotFoundException {
        if (!bundles.isEmpty() && bundles.containsKey(locale)) return bundles.get(locale);
        else throw new ResourceBundleNotFoundException("Resource bundle not found for locale: " + locale);
    }

    public static String getLocaleText(String key, String locale) {
        try {
            ResourceBundle resourceBundle = getBundle(locale);
            if (resourceBundle.containsKey(key)) return resourceBundle.getString(key);
            return key + " not found";
        } catch (ResourceBundleNotFoundException rbnf) {
            return key + " not found";
        }

    }

    public static String getLocaleText(String key) {
        try {
            ResourceBundle resourceBundle = getBundle("tr_TR");
            if (resourceBundle.containsKey(key)) return resourceBundle.getString(key);
            else return key + " not found";
        } catch (ResourceBundleNotFoundException e) {
            return key + " not found";
        }
    }

    public static String getLocaleText(String key, String locale, String... args) {
        try {
            ResourceBundle resourceBundle = getBundle(locale);
            String msg = null;
            if (resourceBundle.containsKey(key)) {
                msg = resourceBundle.getString(key);
                MessageFormat messageFormat = new MessageFormat(msg);
                if (args != null && args.length > 0) {
                    msg = messageFormat.format(args);
                    return msg;
                }
                return msg;
            }
            return key + " not found";
        } catch (ResourceBundleNotFoundException e) {
            return key + " not found";
        }
    }
}

package com.lsuncar.notepad.messaging;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UrlParamSerializer {

    private Map<String, Object> params;

    public void addParameter(String key, Object value) {
        if (Objects.isNull(params))
            params = new HashMap<>();
        params.put(key, value);
    }

    public String serializeAsUrlPathParameters() {
        if (Objects.nonNull(params)) {
            StringBuffer buffer = new StringBuffer();
            this.params.forEach((key, value) -> {
                buffer.append(key);
                buffer.append("=");
                buffer.append(value);
                buffer.append("&");
            });
            String retVal = buffer.toString();
            //url string ends with "&" char
            return retVal.substring(0, retVal.length() - 1);
        } else
            return null;
    }


}

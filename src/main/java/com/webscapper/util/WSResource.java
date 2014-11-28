package com.webscapper.util;

import java.util.Properties;

import com.webscapper.exception.WebScrapperException;

/** WSResource class. */
public final class WSResource {
    /** Constructor. */
    private WSResource() {
    }

    /** Get value.
     * 
     * @param key
     *            key
     * @return value
     * @throws WebScrapperException
     *             exception */
    public static String getValue(String key) throws WebScrapperException {
        String value = null;
        if (key != null && !key.isEmpty()) {
            Properties config = WSResourceLoader.getPropertiesMap();
            value = config.getProperty(key);
            return value;
        }

        return value;
    }
}

package com.webscapper.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.webscapper.exception.WebScrapperException;
import com.webscrapper.constants.CommonConstants;

/** WSResourceLoader class. */
public final class WSResourceLoader {
    private static Properties config = new Properties();

    /** Default constructor. */
    private WSResourceLoader() {
    }

    /** Get properties.
     * 
     * @return properties
     * @throws WebScrapperException */
    public static Properties fetchAndLoadDBProperties() throws WebScrapperException {
        if (config.isEmpty()) {
            try {
                InputStream in = WSResourceLoader.class.getResourceAsStream("/configuration.properties");
                config.load(in);
            } catch (IOException ioEx) {
                throw new WebScrapperException(CommonConstants.EXP_LOAD_RESOURCES_ERROR, ioEx);
            }
        }

        return config;
    }

    /** Get properties.
     * 
     * @return properties
     * @throws WebScrapperException */
    public static Properties getPropertiesMap() throws WebScrapperException {
        return fetchAndLoadDBProperties();
    }
}

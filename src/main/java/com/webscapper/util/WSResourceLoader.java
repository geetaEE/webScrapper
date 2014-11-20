package com.webscapper.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.webscapper.exception.WebScrapperException;
import com.webscrapper.constants.CommonConstants;

public class WSResourceLoader {
    private static Properties config = null;

    /** @param args
     * @throws WebScrapperException */
    public static Properties fetchAndLoadDBProperties() throws WebScrapperException {
        if (null == config) {
            try {
                InputStream in = WSResourceLoader.class.getResourceAsStream("/configuration.properties");
                config = new Properties();
                config.load(in);
            } catch (IOException ioEx) {
                throw new WebScrapperException(CommonConstants.EXP_LOAD_RESOURCES_ERROR, ioEx);
            }
        }

        return config;
    }

    public static Properties getPropertiesMap() throws WebScrapperException {
        return fetchAndLoadDBProperties();
    }
}

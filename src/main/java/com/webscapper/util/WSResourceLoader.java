package com.webscapper.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.webscapper.exception.WebScrapperException;
import com.webscrapper.constants.CommonConstants;

/** WSResourceLoader class. */
public final class WSResourceLoader {
    /** The logger. */
    private static final Logger LOG = Logger.getLogger(WSResourceLoader.class);
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
            InputStream in = null;
            try {
                in = WSResourceLoader.class.getResourceAsStream(CommonConstants.CONFIG_PROP_FILE);
                config.load(in);
            } catch (IOException ioEx) {
                LOG.error(CommonConstants.EXP_LOAD_RESOURCES_ERROR, ioEx);
                throw new WebScrapperException(CommonConstants.EXP_LOAD_RESOURCES_ERROR, ioEx);
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        LOG.error(CommonConstants.EXP_LOAD_RESOURCES_ERROR, e);
                        throw new WebScrapperException(CommonConstants.EXP_LOAD_RESOURCES_ERROR, e);
                    }
                }
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
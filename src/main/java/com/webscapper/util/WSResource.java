package com.webscapper.util;

import java.util.Properties;

import com.webscapper.exception.WebScrapperException;

public final class WSResource 
{
    private WSResource(){}
    
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

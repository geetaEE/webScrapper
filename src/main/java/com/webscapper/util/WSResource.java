package com.webscapper.util;

import java.util.Properties;

import com.webscapper.exception.WebScrapperException;

public class WSResource 
{
	public static String getValue(String key) throws WebScrapperException
	{
		if(key != null && !key.isEmpty())
		{	
			Properties config = WSResourceLoader.getPropertiesMap();
			String value = config.getProperty(key);
			return value;
		}	
		
		return null;
	}
}

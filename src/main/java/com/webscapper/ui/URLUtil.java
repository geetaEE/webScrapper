/*
 * 
 */
package com.webscapper.ui;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

/**
 * The Class URLUtil.
 */
public final class URLUtil 
{
	public void URLUtil(){}
	/** The logger. */
	private static Logger logger = Logger.getLogger(URLUtil.class);
	
	/**
	 * Checks if is valid url.
	 *
	 * @param url the url
	 * @return true, if is valid url
	 */
	public static boolean isValidURL(String url) 
	{
		logger.info("Entering isValidURL method.");
		
		URL u = null;
        try
        {
            u = new URL(url);
        } 
        catch (MalformedURLException e) 
        {        	
        	logger.warn(e);
        	return false;
        }
        
        try 
        {
            u.toURI();
        } 
        catch (URISyntaxException e) 
        {        	
        	logger.warn(e);
        	
        	return false;
        } 
        
        logger.info("Exiting from isValidURL method.");
        
        return true;
    }
	
	/**
	 * Checks if is valid url for connection.
	 *
	 * @param url the url
	 * @return true, if is valid url for connection
	 */
	public static boolean isValidURLForConnection(String url)
	{
		logger.info("Entering isValidURLForConnection method.");
		
		try 
		{		   
			URL webURL = new URL(url);
			URLConnection conn = webURL.openConnection();
		    conn.connect();
		}		
		catch (IOException ex) 
		{			
			logger.warn(ex);
			return false;			 
		}		
		
		logger.info("Exiting from isValidURLForConnection method.");
		
		return true;
	}
}

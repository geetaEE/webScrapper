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
public class URLUtil 
{
	
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
        URL u = null;
        try
        {
            u = new URL(url);
        } 
        catch (MalformedURLException e) 
        {        	
        	return false;
        }
        
        try 
        {
            u.toURI();
        } 
        catch (URISyntaxException e) 
        {        	
        	return false;
        }       
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
		try 
		{		   
			URL webURL = new URL(url);
			URLConnection conn = webURL.openConnection();
		    conn.connect();
		}		
		catch (IOException ex) 
		{			
			return false;			 
		}
		return true;		
	}
}

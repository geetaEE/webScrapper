package com.webscapper.ui;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

public class URLUtil 
{
	/**
	 * 
	 * @param url
	 * @return
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
	 * 
	 * @param url
	 * @return
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

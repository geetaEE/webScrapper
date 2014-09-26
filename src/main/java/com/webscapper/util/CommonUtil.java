package com.webscapper.util;

import java.io.File;
import java.util.Date;

import org.apache.log4j.Logger;

import com.webscrapper.constants.CommonConstants;

/**
 * The Class CommonUtil.
 *
 * @author ruby.jha
 * Common Util
 */
public final class CommonUtil{
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(CommonUtil.class);
	
	/**
	 * Gets the file name.
	 *
	 * @param location the location
	 * @param title the title
	 * @param ext the ext
	 * @return the file name
	 */	
	public static String getFileName(String location, String title, String ext)
	{
		logger.info("Entering getFileName method.");
		String fileName = location + File.separator + title
				+ CommonConstants.DATE_FORMATTER.format(new Date()) + ext;
		logger.info("Exiting from getFileName method.");
		return fileName;
	}

}

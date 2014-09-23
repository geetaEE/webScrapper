package com.webscapper.util;

import java.io.File;
import java.util.Date;

import org.apache.log4j.Logger;

import com.webscrapper.constants.CommonConstants;

/**
 * @author ruby.jha
 * Common Util
 */
public class CommonUtil {
	private static Logger logger = Logger.getLogger(CommonUtil.class);
	/**
	 *  @return the file name
	 *
	 */	
	public static String getFileName(String location, String title, String ext)
	{
		String fileName = location + File.separator + title
				+ CommonConstants.DATE_FORMATTER.format(new Date()) + ext;
		return fileName;
	}

}

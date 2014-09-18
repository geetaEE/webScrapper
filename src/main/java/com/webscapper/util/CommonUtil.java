package com.webscapper.util;

import java.io.File;
import java.util.Date;

import com.webscrapper.constants.CommonConstants;

public class CommonUtil {

	public static String getFileName(String location, String title, String ext)
	{
		String fileName = location + File.separator + title
				+ CommonConstants.DATE_FORMATTER.format(new Date()) + ext;
		return fileName;
	}

}

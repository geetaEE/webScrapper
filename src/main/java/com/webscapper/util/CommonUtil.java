package com.webscapper.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.webscrapper.constants.CommonConstants;

/** The Class CommonUtil.
 * 
 * Common Util. */
public final class CommonUtil {

    /** The logger. */
    private static Logger logger = Logger.getLogger(CommonUtil.class);

    /** Instantiates a new CommonUtil. */
    private CommonUtil() {
    }

    /** Gets the file name.
     * 
     * @param location
     *            the location
     * @param title
     *            the title
     * @param ext
     *            the ext
     * @return the file name */
    public static String getFileName(String location, String title, String ext) {
        logger.info("Entering getFileName method.");
        String fileName = location + File.separator + title + new SimpleDateFormat(CommonConstants.DATE_FORMAT).format(new Date()) + ext;
        logger.info("Exiting from getFileName method.");
        return fileName;
    }

}

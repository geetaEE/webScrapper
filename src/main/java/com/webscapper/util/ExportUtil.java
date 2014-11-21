package com.webscapper.util;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.webscapper.exception.WebScrapperException;
import com.webscrapper.constants.CommonConstants;

/** The Class ExportUtil. */
public final class ExportUtil {
    /** The logger. */
    private static final Logger LOG = Logger.getLogger(ExportUtil.class);

    /** Instantiates a new ExportUtil. */
    private ExportUtil() {
    }

    /** Get file writer.
     * 
     * @param fileName
     *            file name
     * @return file writer
     * @throws WebScrapperException */
    public static FileWriter getFileWriter(String fileName) throws WebScrapperException {
        try {
            FileWriter writer = new FileWriter(fileName);
            return writer;
        } catch (IOException e) {
            LOG.error(CommonConstants.EXP_FILE_EXIST_ERROR + fileName, e);
            throw new WebScrapperException(CommonConstants.EXP_FILE_EXIST_ERROR + fileName, e);
        }
    }

    /** Close file writer.
     * 
     * @param writer
     *            file writer
     * @param fileName
     *            file name
     * @throws WebScrapperException */
    public static void closeFileWriter(FileWriter writer, String fileName) throws WebScrapperException {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                LOG.error(CommonConstants.EXP_FILE_OPER_ERROR + fileName, e);
                throw new WebScrapperException(CommonConstants.EXP_FILE_OPER_ERROR + fileName, e);
            }
        }
    }
}
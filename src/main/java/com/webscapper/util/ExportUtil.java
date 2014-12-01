package com.webscapper.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

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
    public static Writer getFileWriter(String fileName) throws WebScrapperException {
        if (fileName == null) {
            LOG.error(CommonConstants.EXP_FILE_EXIST_ERROR + fileName);
            throw new WebScrapperException(CommonConstants.EXP_FILE_EXIST_ERROR + fileName);
        }
        try {
            File file = new File(fileName);
            return new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
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
    public static void closeFileWriter(Writer writer, String fileName) throws WebScrapperException {
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
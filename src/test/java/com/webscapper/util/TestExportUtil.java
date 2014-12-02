package com.webscapper.util;

import java.io.FileWriter;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.webscapper.exception.WebScrapperException;
import com.webscrapper.constants.CommonConstants;

/** TestExportUtil. */
@RunWith(JUnit4.class)
public class TestExportUtil {
    /** @throws WebScrapperException */
    @Test
    public void testGetFileWriterForBlankFileName() {
        try {
            ExportUtil.getFileWriter(null);
            Assert.fail(" Unit test should be fail.");
        } catch (WebScrapperException ex) {
            Assert.assertEquals(CommonConstants.EXP_FILE_EXIST_ERROR, ex.getMessage());
        }
    }

    /** @throws WebScrapperException */
    @Test
    public void testGetFileWriterForIncorrectFile() {
        try {
            ExportUtil.getFileWriter("..");
            Assert.fail(" Unit test should be fail.");
        } catch (WebScrapperException ex) {
            Assert.assertEquals(CommonConstants.EXP_FILE_EXIST_ERROR + "..", ex.getMessage());
        }
    }

    /** @throws IOException
     * @throws WebScrapperException */
    @Test
    public void testCloseFileWriter() throws IOException {
        try {
            ExportUtil.closeFileWriter(new FileWriter("test.xml"), "..");
        } catch (WebScrapperException ex) {
            Assert.fail(" Unit test should not be fail.");
        }
    }
}
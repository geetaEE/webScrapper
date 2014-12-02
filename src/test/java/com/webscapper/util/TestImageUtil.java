package com.webscapper.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.webscapper.exception.WebScrapperException;
import com.webscrapper.constants.CommonConstants;

/** TestImageUtil. */
@RunWith(JUnit4.class)
public class TestImageUtil {
    // ~ Methods ------------------------------------------------------------------------------------------------------

    /** @throws MalformedURLException
     * @throws WebScrapperException */
    @Test
    public void testSaveImage() throws MalformedURLException {
        try {
            ImageUtil.saveImage("Test.java", new URL("http://www.google.com"), "..", "Test.java");
            Assert.fail(" Unit test should be fail.");
        } catch (WebScrapperException ex) {
            Assert.assertEquals(CommonConstants.EXP_IMG_FILE_EXIST_RW_ERROR, ex.getMessage());
        }
    }

}
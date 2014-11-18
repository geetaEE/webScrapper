package com.webscrapper.ui;

import com.webscapper.ui.URLUtil;

import org.junit.*;

import org.junit.runner.RunWith;

import org.junit.runners.JUnit4;

/** The Class TestURLUtil.
 * 
 * @author vivek.yadav */
@RunWith(JUnit4.class)
public class TestURLUtil {
    // ~ Methods ------------------------------------------------------------------------------------------------------

    /** Unit test for isValidURL(). */
    @Test
    public void testIsValidURL() {
        // Scenario 1 : Positive Scenario.
        String url = "http://www.google.com";
        Boolean result = URLUtil.isValidURL(url);
        Assert.assertTrue("URL validaton result should be true.", result);

        // Scenario 2 : Negative Scenario.
        url = "testtesttest123";
        result = URLUtil.isValidURL(url);
        Assert.assertFalse("URL validaton result should be false.", result);

        // Scenario 3 : Negative Scenario.
        url = "testtesttest123.com";
        result = URLUtil.isValidURL(url);
        Assert.assertFalse("URL validaton result should be false.", result);
    }

    /** Unit test for isValidURLForConnection(). */
    @Test
    public void testIsValidURLForConnection() {
        // Scenario 1 : Positive Scenario.
        String url = "http://www.google.com";
        Boolean result = URLUtil.isValidURLForConnection(url);
        Assert.assertTrue("URL connection validaton result should be true.", result);

        // Scenario 2 : Negative Scenario.
        url = "http://www.testtesttest123.com/";
        result = URLUtil.isValidURLForConnection(url);
        Assert.assertFalse("URL connection validaton result should be false.", result);
    }
}

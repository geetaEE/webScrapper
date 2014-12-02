package com.webscapper.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.webscapper.exception.WebScrapperException;
import com.webscrapper.constants.CommonConstants;

/** TestWSResource. */
@RunWith(JUnit4.class)
public class TestWSResource {
    // ~ Methods ------------------------------------------------------------------------------------------------------

    /** Unit Test for getValue().
     * 
     * @throws WebScrapperException */
    @Test
    public void testGetValue() throws WebScrapperException {
        // Positive Scenario
        String value = WSResource.getValue(CommonConstants.TABLE_NAME);
        Assert.assertEquals("Value should be Test.", "Test", value);

        // Negative Scenario
        value = WSResource.getValue("Test");
        Assert.assertNull("Value should be null.", value);
        
     // Negative Scenario
        value = WSResource.getValue(null);
        Assert.assertNull("Value should be null.", value);
    }
}

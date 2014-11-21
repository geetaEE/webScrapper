package com.webscapper.util;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.webscapper.exception.WebScrapperException;

/** The Class TestWebScrapperUtil.
 * 
 * @author vivek.yadav */
@RunWith(JUnit4.class)
public class TestWSResourceLoader {
    // ~ Methods ------------------------------------------------------------------------------------------------------

    /** Unit Test for getPropertiesMap(). 
     * @throws WebScrapperException */
    @Test
    public void testGetPropertiesMap() throws WebScrapperException 
    {
    	Properties config = WSResourceLoader.getPropertiesMap();
    	Assert.assertNotNull("Properties object is null.", config);
    }   
}

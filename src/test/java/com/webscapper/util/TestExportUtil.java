package com.webscapper.util;

import java.io.FileWriter;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.webscapper.exception.WebScrapperException;
import com.webscrapper.constants.CommonConstants;

/** @author vivek.yadav */
@RunWith(JUnit4.class)
public class TestExportUtil {
    // ~ Methods ------------------------------------------------------------------------------------------------------

    /**
     * 
     * @throws WebScrapperException
     */
    @Test
    public void testGetFileWriter()
    {        
        try
        {
        	ExportUtil.getFileWriter("*.*");
        	Assert.fail(" Unit test should be fail.");
        }	
        catch(WebScrapperException ex)
        {
        	Assert.assertEquals(CommonConstants.EXP_FILE_EXIST_ERROR + "*.*", ex.getMessage());
        }        
    }
    
    /**
     * 
     * @throws IOException 
     * @throws WebScrapperException
     */
    @Test
    public void testCloseFileWriter() throws IOException
    {        
        try
        {
        	ExportUtil.closeFileWriter(new FileWriter("Test.java"), "*.*");        	
        }	
        catch(WebScrapperException ex)
        {
        	Assert.fail(" Unit test should not be fail.");
        }        
    }
}

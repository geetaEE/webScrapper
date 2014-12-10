/**
 * 
 */
package com.webscapper.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.webscapper.exception.WebScrapperException;
import com.webscapper.factory.ExportServiceFactory;
import com.webscapper.request.ExportRequest;
import com.webscrapper.constants.ExportType;
import com.webscrapper.service.ExportService;

/** JUnit class to test the Export To Image Functionality. */
@RunWith(JUnit4.class)
public class TestExportToImageService {
    private List<String> imageUrlsList;

    /** Before setup. */
    @Before
    public void setUp() {
        imageUrlsList = new ArrayList<String>();
        imageUrlsList.add("http://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50.jpg");
    }

    /** Method under test: export() Scenario: Verifying the behavior when Image List and Directory Location are Not Null. Expectation: Image should get
     * saved successfully.
     * 
     * @throws WebScrapperException */
    @Test
    public void testExportToImage() throws WebScrapperException {
        ExportRequest exportRequest = new ExportRequest();
        exportRequest.setTitle("Test");
        exportRequest.setUrl("https://www.httpsnow.org/");
        exportRequest.setLocation(System.getProperty("user.dir"));
        exportRequest.setImageURLList(imageUrlsList);
        exportRequest.setExportType(ExportType.IMAGE);

        ExportService exportService = ExportServiceFactory.getInstance(exportRequest.getExportType());
        exportService.export(exportRequest);
        assertTrue(exportService.export(exportRequest).isSuccess());
    }

    /** Method under test: export() Scenario: Verifying the behavior when Directory Location is Null. Expectation: Image will not be saved & method
     * will not throw any Exception.
     * 
     * @throws WebScrapperException */
    @Test
    public void testExportToImageWhenDirIsNull() throws WebScrapperException {
        ExportRequest exportRequest = new ExportRequest();
        exportRequest.setTitle("Test");
        exportRequest.setUrl("https://www.httpsnow.org/");
        exportRequest.setLocation(null);
        exportRequest.setImageURLList(imageUrlsList);
        exportRequest.setExportType(ExportType.IMAGE);

        ExportService exportService = ExportServiceFactory.getInstance(exportRequest.getExportType());
        exportService.export(exportRequest);
        assertFalse(exportService.export(exportRequest).isSuccess());
    }

    /** Method under test: export() Scenario: Verifying the behavior when Image List is Null. Expectation: Image will not be saved & method will not
     * throw any Exception.
     * 
     * @throws WebScrapperException */
    @Test
    public void testExportToImageWhenImageListIsNull() throws WebScrapperException {
        ExportRequest exportRequest = new ExportRequest();
        exportRequest.setTitle("Test");
        exportRequest.setUrl("https://www.httpsnow.org/");
        exportRequest.setLocation(null);
        exportRequest.setImageURLList(imageUrlsList);
        exportRequest.setExportType(ExportType.IMAGE);

        ExportService exportService = ExportServiceFactory.getInstance(exportRequest.getExportType());
        exportService.export(exportRequest);
        assertFalse(exportService.export(exportRequest).isSuccess());
    }

    /** The destroy. */
    @After
    public void destroy() {
        imageUrlsList = null;
    }
}

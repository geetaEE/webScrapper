package com.webscrapper.ui;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.webscapper.exception.WebScrapperException;
import com.webscapper.request.ExportRequest;
import com.webscapper.request.ExtractRequest;
import com.webscapper.response.ExportResponse;
import com.webscapper.response.ExtractResponse;
import com.webscapper.ui.WSServiceProvider;
import com.webscrapper.constants.ContentType;
import com.webscrapper.constants.ExportType;
import com.webscrapper.constants.TagType;

/** The Class TestWSServiceProvider. */
@RunWith(JUnit4.class)
public class TestWSServiceProvider {
    // ~ Methods ------------------------------------------------------------------------------------------------------

    /** Unit test for buildExtractRequest(). */
    @Test
    public void testBuildExtractRequest() {
        String url = "www.google.com";
        ContentType contentType = ContentType.IMAGE;

        WSServiceProvider wsServiceProvider = new WSServiceProvider();
        ExtractRequest extractRequest = wsServiceProvider.buildExtractRequest(url, contentType);

        Assert.assertNotNull("ExtractRequest object should not be null.", extractRequest);
        Assert.assertEquals("URL is wrong.", url, extractRequest.getUrl());
        Assert.assertEquals("Content type is wrong.", contentType, extractRequest.getContentType());
    }

    /** Unit test for buildExportRequest(). */
    @Test
    public void testBuildExportRequest() {
        String url = "google.com";
        String title = "testTitle";
        ExtractResponse extractResponse = new ExtractResponse();
        ExportType exportType = ExportType.CSV;
        List<String> tagsList = Arrays.asList(new String[] { "Tag1" });
        String location = "TestLocation";
        List<String> selectedImageURLList = Arrays.asList(new String[] { "Image1" });

        WSServiceProvider wsServiceProvider = new WSServiceProvider();
        ExportRequest exportRequest = wsServiceProvider.buildExportRequest(url, title, extractResponse, exportType, tagsList, location,
                selectedImageURLList);

        Assert.assertNotNull("ExportRequest object should not be null.", exportRequest);
        Assert.assertEquals("Url is wrong.", url, exportRequest.getUrl());
        Assert.assertEquals("title is wrong.", title, exportRequest.getTitle());
        Assert.assertEquals("ExtractResponse is wrong.", extractResponse, exportRequest.getExtractResponse());
        Assert.assertEquals("ExportType is wrong.", exportType, exportRequest.getExportType());
        Assert.assertEquals("TagsList is wrong.", tagsList, exportRequest.getTagsList());
        Assert.assertEquals("Location is wrong.", location, exportRequest.getLocation());
        Assert.assertEquals("ImageURLList is wrong.", selectedImageURLList, exportRequest.getImageURLList());
    }

    /** Unit test for executeExtractOperation().
     * 
     * @throws WebScrapperException */
    @Test
    public void testExecuteExtractOperation() throws WebScrapperException {
        WSServiceProvider wsServiceProvider = new WSServiceProvider();

        String url = "http://www.google.com";
        ContentType contentType = ContentType.TEXT;

        ExtractRequest extractRequest = wsServiceProvider.buildExtractRequest(url, contentType);

        ExtractResponse extractResponse = wsServiceProvider.executeExtractOperation(extractRequest);

        Assert.assertNotNull("ExportRequest object should not be null.", extractResponse);
    }

    /** Unit test for executeExportOperation().
     * 
     * @throws WebScrapperException */
    @Test
    public void testExecuteExportOperation() throws WebScrapperException {
        WSServiceProvider wsServiceProvider = new WSServiceProvider();

        String url = "http://www.w3schools.com/html/html_tables.asp";
        String title = "testTitle";
        ContentType contentType = ContentType.TEXT;

        ExtractRequest extractRequest = wsServiceProvider.buildExtractRequest(url, contentType);

        ExtractResponse extractResponse = wsServiceProvider.executeExtractOperation(extractRequest);

        Set<TagType> tagValues = extractResponse.getTagDataMap().keySet();

        List<String> tagList = new ArrayList<String>();

        for (TagType tagType : tagValues) {
            tagList.add(tagType.getDisplayName());
        }

        ExtractResponse extractResponse1 = extractResponse;
        ExportType exportType = ExportType.TEXT;
        List<String> tagsList = tagList;
        String location = System.getProperty("user.dir");

        ExportRequest exportRequest = wsServiceProvider.buildExportRequest(url, title, extractResponse1, exportType, tagsList, location, null);

        ExportResponse exportResponse = wsServiceProvider.executeExportOperation(exportRequest);

        Assert.assertTrue("Response should be success.", exportResponse.isSuccess());
    }

    /** Unit test for fetchTabularPreviewData().
     * 
     * @throws WebScrapperException */
    @Test
    public void testFetchTabularPreviewData() throws WebScrapperException {
        WSServiceProvider wsServiceProvider = new WSServiceProvider();

        String url = "http://www.w3schools.com/html/html_tables.asp";
        String title = "testTitle";
        ContentType contentType = ContentType.TEXT;

        ExtractRequest extractRequest = wsServiceProvider.buildExtractRequest(url, contentType);

        ExtractResponse extractResponse = wsServiceProvider.executeExtractOperation(extractRequest);

        String[][] result = wsServiceProvider.fetchTabularPreviewData(extractResponse);

        Assert.assertTrue("Row count should be 4.", result.length == 4);
        Assert.assertTrue("Column count should be 4.", result[0].length == 4);
    }

    /** Unit test for fetchColumnNameForPreview().
     * 
     * @throws WebScrapperException */
    @Test
    public void testFetchColumnNameForPreview() throws WebScrapperException {
        WSServiceProvider wsServiceProvider = new WSServiceProvider();

        String url = "http://www.w3schools.com/html/html_tables.asp";
        String title = "testTitle";
        ContentType contentType = ContentType.TEXT;

        ExtractRequest extractRequest = wsServiceProvider.buildExtractRequest(url, contentType);

        ExtractResponse extractResponse = wsServiceProvider.executeExtractOperation(extractRequest);

        String[] columns = wsServiceProvider.fetchColumnNameForPreview(extractResponse);
        Assert.assertTrue("Column count should be 4.", columns.length == 4);
    }

    /** Unit test for fetchNonTabularPreviewData().
     * 
     * @throws WebScrapperException */
    @Test
    public void testFetchNonTabularPreviewData() throws WebScrapperException {
        WSServiceProvider wsServiceProvider = new WSServiceProvider();

        String url = "http://www.w3schools.com/html/html_tables.asp";
        String title = "testTitle";
        ContentType contentType = ContentType.TEXT;

        ExtractRequest extractRequest = wsServiceProvider.buildExtractRequest(url, contentType);

        ExtractResponse extractResponse = wsServiceProvider.executeExtractOperation(extractRequest);

        Set<TagType> tagValues = extractResponse.getTagDataMap().keySet();

        List<String> tagList = new ArrayList<String>();

        for (TagType tagType : tagValues) {
            tagList.add(tagType.getDisplayName());
        }

        String result = wsServiceProvider.fetchNonTabularPreviewData(extractResponse, tagList);

        Assert.assertNotNull("Result String should not be null.", result);
    }

    /** Unit test for fetchImagePreviewData().
     * 
     * @throws IOException
     *             Signals that an I/O exception has occurred. */
    @Test
    public void testFetchImagePreviewData() throws IOException {
        WSServiceProvider wsServiceProvider = new WSServiceProvider();

        String imageURL = "http://www.w3schools.com/images/w3logotest2.png";

        InputStream inputStream = wsServiceProvider.fetchImagePreviewData(imageURL);

        Assert.assertNotNull("inputStream should not be null.", inputStream);
    }
}

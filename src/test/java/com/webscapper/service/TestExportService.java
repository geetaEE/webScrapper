package com.webscapper.service;

import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.webscapper.exception.WebScrapperException;
import com.webscapper.factory.ExportServiceFactory;
import com.webscapper.factory.ExtractServiceFactory;
import com.webscapper.request.ExportRequest;
import com.webscapper.request.ExtractRequest;
import com.webscapper.response.ExportResponse;
import com.webscapper.response.ExtractResponse;
import com.webscrapper.constants.ContentType;
import com.webscrapper.constants.ExportType;
import com.webscrapper.constants.TagType;
import com.webscrapper.service.ExportService;

/** Test export service. */
@RunWith(JUnit4.class)
public class TestExportService {

    /** The exportRequest. */
    private ExportRequest exportRequest;
    /** The extractResponse. */
    private ExtractResponse extractResponse;

    /** Before setup. */
    @Before
    public void setUp() {
        exportRequest = new ExportRequest();
        extractResponse = new ExtractResponse();
        exportRequest.setTitle("Test");
        exportRequest.setLocation(System.getProperty("user.dir"));
        exportRequest.setExportType(ExportType.DOC);
        exportRequest.setExtractResponse(extractResponse);
    }

    /** Method under test: export() Scenario: Verifying the behavior when table List is not null. Expectation: Data should be saved to DB successfully.
     * 
     * @throws WebScrapperException */
    @Test
    public void testExportToDB() throws WebScrapperException {
        List<String> columnValue1 = new ArrayList<String>();
        columnValue1.add("Test1");
        columnValue1.add("Test2");

        List<String> columnValue2 = new ArrayList<String>();
        columnValue2.add("Test4");
        columnValue2.add("Test5");

        List<List<String>> rowList1 = new ArrayList<List<String>>();
        rowList1.add(columnValue1);
        rowList1.add(columnValue2);

        List<List<String>> rowList2 = new ArrayList<List<String>>();
        rowList2.add(columnValue2);

        List<List<List<String>>> tableList = new ArrayList<List<List<String>>>();
        tableList.add(rowList1);
        tableList.add(rowList2);
        exportRequest.setTitle("Test");
        exportRequest.setUrl("https://www.httpsnow.org/");
        exportRequest.setExportType(ExportType.DB);

        extractResponse.setTables(tableList);
        exportRequest.setExtractResponse(extractResponse);

        ExportService exportService = ExportServiceFactory.getInstance(exportRequest.getExportType());
        ExportResponse exportResponse = exportService.export(exportRequest);
        Assert.assertTrue(exportResponse.isSuccess());
        Assert.assertNotNull(tableList);
        Assert.assertNotNull(exportRequest.getUrl());
    }

    /** Method under test: export() Scenario: Verifying the behavior when Tag Data Map and tags list are not null. Expectation: Data should be exported
     * to doc successfully.
     * 
     * @throws WebScrapperException */
    @Test
    public void testExportToDoc() throws WebScrapperException {
        Map<TagType, String> tagDataMap = new LinkedHashMap<TagType, String>();
        tagDataMap.put(TagType.DIV, "Div Text");
        tagDataMap.put(TagType.SPAN, "Span Text");
        tagDataMap.put(TagType.ANCHOR, "Anchor Text");
        tagDataMap.put(TagType.BOLD, "Bold");
        tagDataMap.put(TagType.PARAGRAPH, "Paragraph Text");
        extractResponse.setTagDataMap(tagDataMap);
        List<String> tagsList = new ArrayList<String>();
        tagsList.add("Div");
        tagsList.add("Span");
        tagsList.add("Hyper Link");
        tagsList.add("Bold Text");
        tagsList.add("Paragraph");
        exportRequest.setTagsList(tagsList);
        ExportService exportService = ExportServiceFactory.getInstance(exportRequest.getExportType());
        ExportResponse exportResponse = exportService.export(exportRequest);
        Assert.assertTrue(exportResponse.isSuccess());
        Assert.assertNotNull(tagDataMap);
        Assert.assertNotNull(tagsList);
    }

    /** Method under test: export() Scenario: Verifying the behavior when tag list is Null. Expectation: Data will not be saved & method will not throw
     * any Exception.
     * 
     * @throws WebScrapperException */
    @Test
    public void testExportToDocWhenTagsListIsNull() throws WebScrapperException {
        exportRequest.setTagsList(null);
        ExportService exportService = ExportServiceFactory.getInstance(exportRequest.getExportType());
        exportService.export(exportRequest);
        assertFalse(exportService.export(exportRequest).isSuccess());
    }

    /** Method under test: export() Scenario: Verifying the behavior when Tag Data Map and tags list are not null. Expectation: Data should be exported
     * to text file successfully.
     * 
     * @throws WebScrapperException */
    @Test
    public void testExportToText() throws WebScrapperException {
        Map<TagType, String> tagDataMap = new LinkedHashMap<TagType, String>();
        tagDataMap.put(TagType.DIV, "Div Text");
        tagDataMap.put(TagType.SPAN, "Span Text");
        tagDataMap.put(TagType.ANCHOR, "Anchor Text");
        tagDataMap.put(TagType.BOLD, "Bold");
        tagDataMap.put(TagType.PARAGRAPH, "Paragraph Text");
        extractResponse.setTagDataMap(tagDataMap);
        List<String> tagsList = new ArrayList<String>();
        tagsList.add("Div");
        tagsList.add("Span");
        tagsList.add("Hyper Link");
        tagsList.add("Bold Text");
        tagsList.add("Paragraph");
        exportRequest.setExportType(ExportType.TEXT);
        exportRequest.setTagsList(tagsList);
        ExportService exportService = ExportServiceFactory.getInstance(exportRequest.getExportType());
        ExportResponse exportResponse = exportService.export(exportRequest);
        Assert.assertTrue(exportResponse.isSuccess());
        Assert.assertNotNull(tagDataMap);
        Assert.assertNotNull(tagsList);
    }

    /** Method under test: export() Scenario: Verifying the behavior when Tag Data Map and tags list are not null. Expectation: Data should be exported
     * to csv successfully.
     * 
     * @throws WebScrapperException */
    @Test
    public void testExportToCSV() throws WebScrapperException {
        Map<TagType, String> tagDataMap = new LinkedHashMap<TagType, String>();
        tagDataMap.put(TagType.DIV, "Div Text");
        tagDataMap.put(TagType.SPAN, "Span Text");
        tagDataMap.put(TagType.ANCHOR, "Anchor Text");
        tagDataMap.put(TagType.BOLD, "Bold");
        tagDataMap.put(TagType.PARAGRAPH, "Paragraph Text");
        extractResponse.setTagDataMap(tagDataMap);
        List<String> tagsList = new ArrayList<String>();
        tagsList.add("Div");
        tagsList.add("Span");
        tagsList.add("Hyper Link");
        tagsList.add("Bold Text");
        tagsList.add("Paragraph");
        exportRequest.setExportType(ExportType.CSV);
        exportRequest.setTagsList(tagsList);
        ExportService exportService = ExportServiceFactory.getInstance(exportRequest.getExportType());
        ExportResponse exportResponse = exportService.export(exportRequest);
        Assert.assertTrue(exportResponse.isSuccess());
        Assert.assertNotNull(tagDataMap);
        Assert.assertNotNull(tagsList);
    }

    /** Test export table to csv.
     * 
     * @throws WebScrapperException */
    @Test
    public void testExportTableToCSV() throws WebScrapperException {
        String url = "http://www.w3schools.com/html/html_tables.asp";
        ExtractRequest request = new ExtractRequest();
        request.setUrl(url);
        request.setContentType(ContentType.TEXT);
        ExtractResponse response = ExtractServiceFactory.getInstance(request.getContentType()).extract(request);
        exportRequest.setExtractResponse(response);
        exportRequest.setExportType(ExportType.CSV);
        ExportService exportService = ExportServiceFactory.getInstance(exportRequest.getExportType());
        ExportResponse exportResponse = exportService.export(exportRequest);
        Assert.assertTrue(exportResponse.isSuccess());
    }

    /** After setup. */
    @After
    public void destroy() {
        exportRequest = null;
        extractResponse = null;
    }
}
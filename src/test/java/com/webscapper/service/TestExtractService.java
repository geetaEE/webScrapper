package com.webscapper.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.webscapper.factory.ExtractServiceFactory;
import com.webscapper.request.ExtractRequest;
import com.webscapper.response.ExtractResponse;
import com.webscrapper.constants.ContentType;
import com.webscrapper.constants.TagType;

/** Test extract service. */
public class TestExtractService {
    /** Test https extraction. */
    @Test
    public void testExtractHttps() {
        String url = "https://www.httpsnow.org/";
        ExtractRequest request = new ExtractRequest();
        request.setUrl(url);
        request.setContentType(ContentType.TEXT);
        ExtractResponse response = ExtractServiceFactory.getInstance(request.getContentType()).extract(request);
        Map<TagType, String> tagDataMap = response.getTagDataMap();
        Assert.assertTrue(tagDataMap.get(TagType.ANCHOR).contains("How does our website work"));
        Assert.assertTrue(tagDataMap.get(TagType.PARAGRAPH).contains("HTTPS Now"));
    }

    /** Test https extraction. */
    @Test
    public void testExtractHttp() {
        String url = "http://stackoverflow.com/";
        ExtractRequest request = new ExtractRequest();
        request.setUrl(url);
        request.setContentType(ContentType.TEXT);
        ExtractResponse response = ExtractServiceFactory.getInstance(request.getContentType()).extract(request);
        Map<TagType, String> tagDataMap = response.getTagDataMap();
        Assert.assertTrue(tagDataMap.get(TagType.DIV).contains(
                "Stack Overflow is a question and answer site for professional and enthusiast programmers."));
    }

    /** Test https extraction. */
    @Test
    public void testExtractText() {
        String url = "https://www.httpsnow.org/";
        ExtractRequest request = new ExtractRequest();
        request.setUrl(url);
        request.setContentType(ContentType.TEXT);
        ExtractResponse response = ExtractServiceFactory.getInstance(request.getContentType()).extract(request);
        Map<TagType, String> tagDataMap = response.getTagDataMap();
        Assert.assertTrue(tagDataMap.get(TagType.DIV).contains(
                "Enter any website URL address HTTPS Now allows users to contribute information about how websites use HTTPS"));
        Assert.assertTrue(tagDataMap.get(TagType.ANCHOR).contains("How does our website work"));
    }

    /** Test table extraction. */
    @Test
    public void testExtractTable() {
        String url = "http://www.w3schools.com/html/html_tables.asp";
        ExtractRequest request = new ExtractRequest();
        request.setUrl(url);
        request.setContentType(ContentType.TEXT);
        ExtractResponse response = ExtractServiceFactory.getInstance(request.getContentType()).extract(request);
        List<List<List<String>>> tables = response.getTables();
        List<String> columns = tables.get(0).get(0);
        Assert.assertEquals(6, tables.size());
        Assert.assertEquals(5, tables.get(0).size());
        Assert.assertEquals(4, columns.size());
        Assert.assertEquals("Number", columns.get(0));
    }

    /** Test image extraction. */
    @Test
    public void testExtractImage() {
        String url = "https://www.httpsnow.org/";
        ExtractRequest request = new ExtractRequest();
        request.setUrl(url);
        request.setContentType(ContentType.IMAGE);
        ExtractResponse response = ExtractServiceFactory.getInstance(request.getContentType()).extract(request);
        Set<String> imageUrls = response.getImageUrls();
        Assert.assertEquals(3, imageUrls.size());
        Assert.assertEquals("https://www.httpsnow.org/images/httpsnow_banner.png", imageUrls.iterator().next());
    }
}
package com.webscapper.service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.webscapper.factory.ExtractServiceFactory;
import com.webscapper.request.ExtractRequest;
import com.webscapper.response.ExtractResponse;
import com.webscrapper.constants.CommonConstants;
import com.webscrapper.constants.ContentType;
import com.webscrapper.constants.TagType;

/** Test extract service. */
@RunWith(JUnit4.class)
public class TestExtractService {
    /** Test https extraction.
     * 
     * @throws IOException */
    @Test
    public void testExtractHttps() throws IOException {
        String url = "https://www.httpsnow.org/";
        ExtractRequest request = new ExtractRequest();
        request.setUrl(url);
        request.setContentType(ContentType.TEXT);
        ExtractResponse response = ExtractServiceFactory.getInstance(request.getContentType()).extract(request);
        Map<TagType, String> tagDataMap = response.getTagDataMap();
        Assert.assertTrue(tagDataMap.get(TagType.ANCHOR).contains("How does our website work"));
        Assert.assertTrue(tagDataMap.get(TagType.PARAGRAPH).contains("HTTPS Now"));
    }

    /** Test https extraction.
     * 
     * @throws IOException */
    @Test
    public void testExtractHttp() throws IOException {
        String url = "http://stackoverflow.com/";
        ExtractRequest request = new ExtractRequest();
        request.setUrl(url);
        request.setContentType(ContentType.TEXT);
        ExtractResponse response = ExtractServiceFactory.getInstance(request.getContentType()).extract(request);
        Map<TagType, String> tagDataMap = response.getTagDataMap();
        Assert.assertTrue(tagDataMap.get(TagType.DIV).contains(
                "Stack Overflow is a question and answer site for professional and enthusiast programmers."));
    }

    /** Test https extraction.
     * 
     * @throws IOException */
    @Test
    public void testExtractText() throws IOException {
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

    /** Test table extraction.
     * 
     * @throws IOException */
    @Test
    public void testExtractTable() throws IOException {
        String url = "http://www.w3schools.com/html/html_tables.asp";
        ExtractRequest request = new ExtractRequest();
        request.setUrl(url);
        request.setContentType(ContentType.TEXT);
        ExtractResponse response = ExtractServiceFactory.getInstance(request.getContentType()).extract(request);
        List<List<List<String>>> tables = response.getTables();
        List<String> columns = tables.get(0).get(0);
        Assert.assertEquals(7, tables.size());
        Assert.assertEquals(5, tables.get(0).size());
        Assert.assertEquals(4, columns.size());
        Assert.assertEquals("Number", columns.get(0));
    }

    /** Test image extraction.
     * 
     * @throws IOException */
    @Test
    public void testExtractImage() throws IOException {
        String url = "https://www.httpsnow.org/";
        ExtractRequest request = new ExtractRequest();
        request.setUrl(url);
        request.setContentType(ContentType.IMAGE);
        ExtractResponse response = ExtractServiceFactory.getInstance(request.getContentType()).extract(request);
        Set<String> imageUrls = response.getImageUrls();
        Assert.assertEquals(3, imageUrls.size());
        Assert.assertEquals("https://www.httpsnow.org/images/httpsnow_banner.png", imageUrls.iterator().next());
    }

    /** Test extract timeout.
     * 
     * @throws Exception */
    @Test
    public void testExtractTimeout() throws Exception {
        String url = "https://www.httpsnow.org/";
        ExtractRequest request = new ExtractRequest();
        request.setUrl(url);
        request.setContentType(ContentType.IMAGE);
        setFinalStatic(CommonConstants.class.getDeclaredField("EXTRACT_TIMEOUT"), 1);

        try {
            ExtractServiceFactory.getInstance(request.getContentType()).extract(request);
        } catch (IOException e) {
            Assert.assertEquals(CommonConstants.EXTRACT_READ_TIME_OUT, e.getMessage());
        }
    }

    /** FinalStatic.
     * 
     * @param field
     *            field
     * @param newValue
     *            newValue
     * @throws Exception */
    private static void setFinalStatic(Field field, Object newValue) throws Exception {
        field.setAccessible(true);

        // remove final modifier from field
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(null, newValue);
    }
}
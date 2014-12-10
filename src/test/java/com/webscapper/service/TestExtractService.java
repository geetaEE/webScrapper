package com.webscapper.service;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.webscapper.exception.WebScrapperException;
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
     * @throws WebScrapperException */
    @Test
    public void testExtractHttps() throws WebScrapperException {
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
     * @throws WebScrapperException */
    @Test
    public void testExtractHttp() throws WebScrapperException {
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
     * @throws WebScrapperException */
    @Test
    public void testExtractText() throws WebScrapperException {
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
     * @throws WebScrapperException */
    @Test
    public void testExtractTable() throws WebScrapperException {
        String url = "http://www.w3schools.com/html/html_tables.asp";
        ExtractRequest request = new ExtractRequest();
        request.setUrl(url);
        request.setContentType(ContentType.TEXT);
        ExtractResponse response = ExtractServiceFactory.getInstance(request.getContentType()).extract(request);
        List<List<List<String>>> tables = response.getTables();
        List<String> columns = tables.get(0).get(0);
        Assert.assertEquals(CommonConstants.SEVEN, tables.size());
        Assert.assertEquals(CommonConstants.FIVE, tables.get(0).size());
        Assert.assertEquals(CommonConstants.FOUR, columns.size());
        Assert.assertEquals("Number", columns.get(0));
    }

    /** Test image extraction.
     * 
     * @throws WebScrapperException */
    @Test
    public void testExtractImage() throws WebScrapperException {
        String url = "https://www.httpsnow.org/";
        ExtractRequest request = new ExtractRequest();
        request.setUrl(url);
        request.setContentType(ContentType.IMAGE);
        ExtractResponse response = ExtractServiceFactory.getInstance(request.getContentType()).extract(request);
        Set<String> imageUrls = response.getImageUrls();
        Assert.assertEquals(CommonConstants.THREE, imageUrls.size());
        Assert.assertEquals("https://www.httpsnow.org/images/httpsnow_banner.png", imageUrls.iterator().next());
    }

    /** Test Invalid url. */
    @Test
    public void testInvalidUrl() {
        String url = "Invalid";
        ExtractRequest request = new ExtractRequest();
        request.setUrl(url);
        request.setContentType(ContentType.IMAGE);
        try {
            ExtractServiceFactory.getInstance(request.getContentType()).extract(request);
            Assert.fail(CommonConstants.EXTRACT_URL_INVALID);
        } catch (WebScrapperException e) {
            Assert.assertEquals(CommonConstants.EXTRACT_URL_INVALID, e.getMessage());
        }
    }

    /** Test Invalid host. */
    @Test
    public void testInvalidHost() {
        String url = "http://invalidhost999666";
        ExtractRequest request = new ExtractRequest();
        request.setUrl(url);
        request.setContentType(ContentType.IMAGE);
        try {
            ExtractServiceFactory.getInstance(request.getContentType()).extract(request);
            Assert.fail(CommonConstants.EXTRACT_URL_INVALID);
        } catch (WebScrapperException e) {
            Assert.assertEquals(CommonConstants.EXTRACT_URL_INVALID, e.getMessage());
        }
    }

    /** Test http error. */
    @Test
    public void testHttpError() {
        String url = "http://google.com/status404";
        ExtractRequest request = new ExtractRequest();
        request.setUrl(url);
        request.setContentType(ContentType.IMAGE);
        try {
            ExtractServiceFactory.getInstance(request.getContentType()).extract(request);
            Assert.fail(CommonConstants.EXTRACT_HTTP_ERROR);
        } catch (WebScrapperException e) {
            Assert.assertEquals(CommonConstants.EXTRACT_HTTP_ERROR + "404", e.getMessage());
        }
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
            Assert.fail(CommonConstants.EXTRACT_READ_TIME_OUT);
        } catch (WebScrapperException e) {
            Assert.assertEquals(CommonConstants.EXTRACT_READ_TIME_OUT, e.getMessage());
        }
        setFinalStatic(CommonConstants.class.getDeclaredField("EXTRACT_TIMEOUT"), CommonConstants.EXTRACT_TIMEOUT_VAL);
    }

    /** Test blank url for text.
     * 
     * @throws WebScrapperException
     *             the exception */
    @Test
    public void testEmptyUrlInText() throws WebScrapperException {
        ExtractRequest request = new ExtractRequest();
        request.setContentType(ContentType.TEXT);
        Assert.assertNull(ExtractServiceFactory.getInstance(request.getContentType()).extract(request));
    }

    /** Test blank url for image.
     * 
     * @throws WebScrapperException
     *             the exception */
    @Test
    public void testEmptyUrlInImage() throws WebScrapperException {
        ExtractRequest request = new ExtractRequest();
        request.setContentType(ContentType.IMAGE);
        Assert.assertNull(ExtractServiceFactory.getInstance(request.getContentType()).extract(request));
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
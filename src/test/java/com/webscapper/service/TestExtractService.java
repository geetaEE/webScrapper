package com.webscapper.service;

import java.util.List;
import java.util.Map;

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
        ExtractResponse response = ExtractServiceFactory.getInstance(ContentType.TEXT).extract(request);
        Map<TagType, String> tagDataMap = response.getTagDataMap();
        Assert.assertTrue(tagDataMap.get(TagType.BOLD).contains("A joint project of EFF"));
        Assert.assertTrue(tagDataMap.get(TagType.PARAGRAPH).contains("HTTPS Now"));
        
        List<List<List<String>>> tables = response.getTables();
        List<String> columns = tables.get(0).get(0);
        Assert.assertEquals(1, tables.size());
        Assert.assertEquals(42, tables.get(0).size());
        Assert.assertEquals(262, columns.size());
        Assert.assertEquals("Name", columns.get(0));
    }
}
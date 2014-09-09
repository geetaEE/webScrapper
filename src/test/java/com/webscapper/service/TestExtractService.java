package com.webscapper.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.webscapper.factory.ExtractServiceFactory;
import com.webscapper.request.ExtractRequest;
import com.webscrapper.constants.ContentType;
import com.webscrapper.constants.TagType;

/** Test extract service. */
public class TestExtractService {
    /** Test https extraction. */
    @Test
    public void testExtractHttps() {
        String url = "https://www.httpsnow.org/";
        List<TagType> tags = new ArrayList<TagType>();
        tags.add(TagType.PARAGRAPH);
        tags.add(TagType.BOLD);
        ExtractRequest request = new ExtractRequest();
        request.setUrl(url);
        request.setTags(tags);
        String text = ExtractServiceFactory.getInstance(ContentType.TEXT).extract(request).toString();
        Assert.assertTrue(text.contains("HTTPS Now"));
    }
}
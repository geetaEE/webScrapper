package com.webscapper.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import com.webscapper.exception.WebScrapperException;
import com.webscapper.request.ExtractRequest;
import com.webscapper.response.ExtractResponse;
import com.webscapper.util.ExtractTableUtil;
import com.webscapper.util.ExtractTextUtil;
import com.webscapper.util.ExtractUtil;
import com.webscrapper.constants.TagType;
import com.webscrapper.service.ExtractService;

/** The extract text content service. */
public enum TextExtractService implements ExtractService {
    INSTANCE;
    /** Logger. */
    private static final Logger LOG = Logger.getLogger(TextExtractService.class);

    @Override
    public ExtractResponse extract(ExtractRequest request) throws WebScrapperException {
        LOG.info("Method extract for Text is executing");
        if (request.getUrl() != null) {
            Document doc = ExtractUtil.extractDocument(request.getUrl());
            if (doc != null) {
                ExtractResponse response = new ExtractResponse();
                // Non tabular data
                Map<TagType, String> tagDataMap = ExtractTextUtil.getTagDataMap(doc);
                if (!tagDataMap.isEmpty()) {
                    response.setTagDataMap(tagDataMap);
                }
                // Tabular data.
                List<List<List<String>>> tables = ExtractTableUtil.getTableList(doc);
                if (!tables.isEmpty()) {
                    response.setTables(tables);
                }
                // return response
                if (!tagDataMap.isEmpty() || !tables.isEmpty()) {
                    return response;
                }
            }
        }
        return null;
    }
}
package com.webscapper.service.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.webscapper.request.ExtractRequest;
import com.webscapper.response.ExtractResponse;

/** The extract image content service. */
public class ImageExtractService extends BaseExtractService {
    @Override
    public ExtractResponse extract(ExtractRequest request) {
        if (request != null && request.getUrl() != null) {
            Document doc = extractDocument(request.getUrl());
            if (doc != null) {
                Set<String> imgSet = new LinkedHashSet<String>();
                for (Element imgElem : doc.select("img")) {
                    String imgUrl = imgElem.absUrl("src");
                    if (!imgUrl.isEmpty()) {
                        imgSet.add(imgUrl);
                    }
                }
                if (!imgSet.isEmpty()) {
                    ExtractResponse response = new ExtractResponse();
                    response.setImageUrls(imgSet);
                    return response;
                }
            }
        }
        return null;
    }
}
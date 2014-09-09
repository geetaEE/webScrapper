package com.webscapper.service.impl;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.webscapper.request.ExtractRequest;
import com.webscrapper.constants.TagType;

/** The extract text content service. */
public class TextExtractService extends BaseExtractService {
    @Override
    public String extract(ExtractRequest request) {
        if (request != null && request.getUrl() != null && request.getTags() != null && !request.getTags().isEmpty()) {
            List<TagType> tags = request.getTags();
            if (tags != null && !tags.isEmpty()) {
                Document doc = extractDocument(request.getUrl());
                if (doc != null) {
                    StringBuilder textB = new StringBuilder();
                    for (TagType tagType : tags) {
                        for (Element element : doc.select(tagType.getName())) {
                            String elemTxt = element.text();
                            if (!textB.toString().contains(elemTxt)) {
                                if (textB.toString().isEmpty()) {
                                    textB.append(elemTxt);
                                } else {
                                    textB.append(" ");
                                    textB.append(elemTxt);
                                }
                            }
                        }
                    }
                    String text = textB.toString();
                    if (!text.isEmpty()) {
                        return text;
                    }
                }
            }
        }
        return null;
    }
}
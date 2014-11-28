package com.webscapper.service.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.webscapper.exception.WebScrapperException;
import com.webscapper.request.ExtractRequest;
import com.webscapper.response.ExtractResponse;
import com.webscapper.util.ExtractUtil;
import com.webscrapper.constants.CommonConstants;
import com.webscrapper.service.ExtractService;

/** The extract image content service. */
public enum ImageExtractService implements ExtractService {
    INSTANCE;
    /** Logger. */
    private static final Logger LOG = Logger.getLogger(ImageExtractService.class);

    @Override
    public ExtractResponse extract(ExtractRequest request) throws WebScrapperException {
        LOG.info("Method extract for Image is executing");
        if (request.getUrl() != null) {
            Document doc = ExtractUtil.extractDocument(request.getUrl());
            if (doc != null) {
                Set<String> imgSet = new LinkedHashSet<String>();
                for (Element imgElem : doc.select(CommonConstants.IMAGE_TAG)) {
                    String imgUrl = imgElem.absUrl(CommonConstants.SRC_ATTR);
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
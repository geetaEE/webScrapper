package com.webscapper.service.impl;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.webscapper.request.ExtractRequest;
import com.webscapper.response.ExtractResponse;
import com.webscrapper.constants.CommonConstants;

/** The extract image content service. */
public class ImageExtractService extends BaseExtractService {
    /** Logger. */
    private static Logger logger = Logger.getLogger(ImageExtractService.class);

    @Override
    public ExtractResponse extract(ExtractRequest request) throws IOException {
        logger.info("Method extract for Image is executing");
        if (request != null && request.getUrl() != null) {
            Document doc = extractDocument(request.getUrl());
            if (doc != null) {
                Set<String> imgSet = new LinkedHashSet<String>();
                for (Element imgElem : doc.select(CommonConstants.IMAGE_TAG)) {
                    String imgUrl = imgElem.absUrl(CommonConstants.SRC_ATTR);
                    if (imgUrl.isEmpty()) {
                        String relUrl = imgElem.attr(CommonConstants.SRC_ATTR);
                        if (!relUrl.isEmpty()) {
                            String url = request.getUrl();
                            if (url.endsWith("/")) {
                                url = url.substring(0, url.length() - 1);
                            }
                            imgUrl = url + relUrl;
                        }
                    }
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
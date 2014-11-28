package com.webscapper.factory;

import com.webscapper.service.impl.ImageExtractService;
import com.webscapper.service.impl.TextExtractService;
import com.webscrapper.constants.ContentType;
import com.webscrapper.service.ExtractService;

/** A factory for creating extract service objects. */
public abstract class ExtractServiceFactory {
    /** Get extract service instance.
     * 
     * @param type
     *            content type
     * @return instance */
    public static ExtractService getInstance(ContentType type) {
        if (ContentType.IMAGE == type) {
            return ImageExtractService.INSTANCE;
        } else {
            return TextExtractService.INSTANCE;
        }
    }
}
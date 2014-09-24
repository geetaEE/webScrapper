package com.webscapper.factory;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.webscapper.service.impl.ImageExtractService;
import com.webscapper.service.impl.TextExtractService;
import com.webscrapper.constants.ContentType;
import com.webscrapper.service.ExtractService;

/** A factory for creating extract service objects. */
public abstract class ExtractServiceFactory {
    /** Logger. */
    private static Logger logger = Logger.getLogger(ExtractServiceFactory.class);
    /** The instance map. */
    private static Map<ContentType, ExtractService> instanceMap;

    /** Get extract service instance.
     * 
     * @param type
     *            content type
     * @return instance */
    public static ExtractService getInstance(ContentType type) {
        if (null == instanceMap) {
            synchronized (ExtractServiceFactory.class) {
                if (null == instanceMap) {
                    instanceMap = new HashMap<ContentType, ExtractService>();
                }
            }
        }
        if (!instanceMap.containsKey(type)) {
            synchronized (ExtractServiceFactory.class) {
                if (!instanceMap.containsKey(type)) {
                    if (ContentType.IMAGE.equals(type)) {
                        instanceMap.put(type, new ImageExtractService());
                        logger.info("Singleton ImageExtractService created");
                    } else {
                        instanceMap.put(type, new TextExtractService());
                        logger.info("Singleton TextExtractService created");
                    }
                }
            }
        }
        return instanceMap.get(type);
    }
}
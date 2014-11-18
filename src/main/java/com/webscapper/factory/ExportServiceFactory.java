package com.webscapper.factory;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.webscapper.service.impl.ExportToCSVService;
import com.webscapper.service.impl.ExportToDBService;
import com.webscapper.service.impl.ExportToDocService;
import com.webscapper.service.impl.ExportToImageService;
import com.webscapper.service.impl.ExportToTextService;
import com.webscrapper.constants.ExportType;
import com.webscrapper.service.ExportService;

/** @author ruby.jha A factory for creating export service objects. */
public abstract class ExportServiceFactory {

    /** The logger. */
    private static Logger logger = Logger.getLogger(ExportServiceFactory.class);

    /** The instance map. */
    private static Map<ExportType, ExportService> instanceMap;

    /** Get export service instance.
     * 
     * @param type
     *            content type
     * @return instance */
    public static ExportService getInstance(ExportType type) {
        logger.info("Entering getInstance method.");
        if (null == instanceMap) {
            synchronized (ExportServiceFactory.class) {
                if (null == instanceMap) {
                    instanceMap = new HashMap<ExportType, ExportService>();
                }
            }
        }
        if (!instanceMap.containsKey(type)) {
            synchronized (ExportServiceFactory.class) {
                if (!instanceMap.containsKey(type)) {
                    if (ExportType.DB.equals(type)) {
                        instanceMap.put(type, new ExportToDBService());
                    } else if (ExportType.CSV.equals(type)) {
                        instanceMap.put(type, new ExportToCSVService());
                    } else if (ExportType.DOC.equals(type)) {
                        instanceMap.put(type, new ExportToDocService());
                    } else if (ExportType.TEXT.equals(type)) {
                        instanceMap.put(type, new ExportToTextService());
                    } else if (ExportType.IMAGE.equals(type)) {
                        instanceMap.put(type, new ExportToImageService());
                    }
                }
            }
        }
        logger.info("Exiting from getInstance method.");
        return instanceMap.get(type);
    }
}
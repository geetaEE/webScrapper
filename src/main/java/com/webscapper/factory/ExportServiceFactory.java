package com.webscapper.factory;

import java.util.HashMap;
import java.util.Map;

import com.webscapper.service.impl.ExportToCSVService;
import com.webscapper.service.impl.ExportToDBService;
import com.webscapper.service.impl.ExportToDocService;
import com.webscapper.service.impl.ExportToTextService;
import com.webscrapper.constants.ExportType;
import com.webscrapper.service.ExportService;

/** A factory for creating export service objects. */
public abstract class ExportServiceFactory {
    /** The instance map. */
    private static Map<ExportType, ExportService> instanceMap;

    /** Get export service instance.
     * 
     * @param type
     *            content type
     * @return instance */
    public static ExportService getInstance(ExportType type) {
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
                    }
                    else if (ExportType.CSV.equals(type)) {
                        instanceMap.put(type, new ExportToCSVService());
                    }
                    else if (ExportType.DOC.equals(type)) {
                        instanceMap.put(type, new ExportToDocService());
                    } else {
                        instanceMap.put(type, new ExportToTextService());
                    }
                }
            }
        }
        return instanceMap.get(type);
    }
}
package com.webscapper.factory;

import com.webscapper.service.impl.ExportToCSVService;
import com.webscapper.service.impl.ExportToDBService;
import com.webscapper.service.impl.ExportToDocService;
import com.webscapper.service.impl.ExportToImageService;
import com.webscapper.service.impl.ExportToTextService;
import com.webscrapper.constants.ExportType;
import com.webscrapper.service.ExportService;

/** A factory for creating export service objects. */
public abstract class ExportServiceFactory {
    /** Get export service instance.
     * 
     * @param type
     *            content type
     * @return instance */
    public static ExportService getInstance(ExportType type) {
        if (ExportType.DB.equals(type)) {
            return ExportToDBService.INSTANCE;
        } else if (ExportType.CSV.equals(type)) {
            return ExportToCSVService.INSTANCE;
        } else if (ExportType.DOC.equals(type)) {
            return ExportToDocService.INSTANCE;
        } else if (ExportType.TEXT.equals(type)) {
            return ExportToTextService.INSTANCE;
        } else if (ExportType.IMAGE.equals(type)) {
            return ExportToImageService.INSTANCE;
        }
        return null;
    }
}
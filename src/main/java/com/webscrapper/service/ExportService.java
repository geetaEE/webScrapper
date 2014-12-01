package com.webscrapper.service;

import com.webscapper.exception.WebScrapperException;
import com.webscapper.request.ExportRequest;
import com.webscapper.response.ExportResponse;

/** Export Service */
public interface ExportService {
    /** This method is used to insert the tabular data into DB based on the ExportType.
     * 
     * @param request
     *            request
     * @return extract response
     * @throws WebScrapperException
     *             excetion */
    ExportResponse export(ExportRequest request) throws WebScrapperException;
}

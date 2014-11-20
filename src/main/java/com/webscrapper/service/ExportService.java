package com.webscrapper.service;

import com.webscapper.exception.WebScrapperException;
import com.webscapper.request.ExportRequest;
import com.webscapper.response.ExportResponse;

/** @author ruby.jha Export Service */
public interface ExportService {
    /** This method is used to insert the tabular data into DB based on the ExportType */
    ExportResponse export(ExportRequest request) throws WebScrapperException;
}

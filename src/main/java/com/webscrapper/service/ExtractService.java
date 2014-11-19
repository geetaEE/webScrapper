package com.webscrapper.service;

import com.webscapper.exception.WebScrapperException;
import com.webscapper.request.ExtractRequest;
import com.webscapper.response.ExtractResponse;

/** The extract web content service. */
public interface ExtractService {
    /** Extracts the web content of url.
     * 
     * @param request
     *            the request
     * @return extracted content
     * @throws WebScrapperException */
    ExtractResponse extract(ExtractRequest request) throws WebScrapperException;
}
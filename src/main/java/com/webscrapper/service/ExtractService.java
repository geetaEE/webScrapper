package com.webscrapper.service;

import com.webscapper.request.ExtractRequest;

/** The extract web content service. */
public interface ExtractService {
    /** Extracts the web content of url.
     * 
     * @param request
     *            the request
     * @return extracted content */
    Object extract(ExtractRequest request);
}
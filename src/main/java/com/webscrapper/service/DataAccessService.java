package com.webscrapper.service;

import java.util.Map;

import com.webscapper.exception.WebScrapperException;

/** DataAccessService. */

public interface DataAccessService {
    /** Insert tabular data to DB.
     * 
     * @param map
     *            map
     * @throws WebScrapperException
     *             exception */
    void insertData(Map<String, Object> map) throws WebScrapperException;
}
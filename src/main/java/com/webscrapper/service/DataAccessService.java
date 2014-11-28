package com.webscrapper.service;

import java.util.Map;

import com.mongodb.DBCollection;
import com.webscapper.exception.WebScrapperException;

/** @author ruby.jha DataAccessService */

public interface DataAccessService {
    /** Insert tabular data to DB.
     * 
     * @param map
     *            map
     * @return db collection
     * @throws WebScrapperException
     *             exception */
    DBCollection insertData(Map<String, Object> map) throws WebScrapperException;

}
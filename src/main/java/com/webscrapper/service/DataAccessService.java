package com.webscrapper.service;

import java.util.Map;

import com.mongodb.DBCollection;

/** @author ruby.jha DataAccessService */

public interface DataAccessService {
    /** insert tabular data to DB */
    DBCollection insertData(Map<String, Object> map);

}

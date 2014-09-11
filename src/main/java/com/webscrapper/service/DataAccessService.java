package com.webscrapper.service;

import java.util.Map;

import com.mongodb.DBCollection;

public interface DataAccessService {

	DBCollection insertData(Map<String, Object> map);

}

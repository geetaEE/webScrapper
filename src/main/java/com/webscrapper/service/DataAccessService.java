package com.webscrapper.service;

import com.mongodb.DBCollection;

public interface DataAccessService {
	
	public DBCollection insertData(Object jsonData);

}

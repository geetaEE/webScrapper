package com.webscrapper.service;

import com.mongodb.DBCollection;

public interface WebScrapperService {
	
	public DBCollection insertData(Object jsonData);

}

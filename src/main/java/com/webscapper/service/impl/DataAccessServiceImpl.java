package com.webscapper.service.impl;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.webscrapper.connection.MongoConnectionManager;
import com.webscrapper.constants.CommonConstants;
import com.webscrapper.service.DataAccessService;

public class DataAccessServiceImpl implements DataAccessService{	

	@Override
	public DBCollection insertData(Object jsonData)
 {
		DB db = null;
		try {
			db = MongoConnectionManager.getInstance().getConnection()
					.getDbConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBCollection table = db.getCollection(CommonConstants.TABLE_NAME);		
		DBObject dbObject = (DBObject)JSON.parse((String) jsonData);		
		table.insert(dbObject);
		return table;

	}

}

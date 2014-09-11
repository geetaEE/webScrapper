package com.webscapper.service.impl;

import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.webscrapper.connection.MongoConnectionManager;
import com.webscrapper.constants.DBConstants;
import com.webscrapper.service.DataAccessService;

public class DataAccessServiceImpl implements DataAccessService {

	/*This method is used to insert the tabular data into DB*/
	@Override
	public DBCollection insertData(Map<String, String> map) {
		DB db = null;
		try {
			db = MongoConnectionManager.getInstance().getConnection()
					.getDbConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBCollection table = db.getCollection(DBConstants.TABLE_NAME);
		table.insert(new BasicDBObject(map));
		return table;

	}

}

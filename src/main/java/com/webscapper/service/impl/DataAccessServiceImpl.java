package com.webscapper.service.impl;

import java.util.Map;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.webscrapper.connection.MongoConnectionManager;
import com.webscrapper.constants.DBConstants;
import com.webscrapper.service.DataAccessService;

/**
 * @author ruby.jha
 * DataAccessServiceImpl
 */
public class DataAccessServiceImpl implements DataAccessService {
	private static Logger logger = Logger.getLogger(DataAccessServiceImpl.class);
	/**
	 * This method is used to insert the tabular data into DB
	 */	
	@Override
	public DBCollection insertData(Map<String, Object> map) {
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

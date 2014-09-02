package com.webscapper.ServiceImpl;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.webscrapper.common.CommonConstants;
import com.webscrapper.connection.MongoConnectionManager;
import com.webscrapper.service.WebScrapperService;

public class WebScrapperServiceImpl implements WebScrapperService{	

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

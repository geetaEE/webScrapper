package com.webscrapper.main;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.webscapper.service.impl.DataAccessServiceImpl;

public class MongoDBMain {
	
	public static void main(String[] args) {
		String json = "{'database' : 'test','table' : 'hosting'," +
				  "'detail' : {'records' : 99, 'index' : 'index1', 'active' : 'true'}}}";
		
		DataAccessServiceImpl serviceImpl = new DataAccessServiceImpl();
		DBCollection collection = serviceImpl.insertData(json);
		
		DBCursor cursorDocJSON = collection.find();
		while (cursorDocJSON.hasNext()) {
			System.out.println(cursorDocJSON.next());
		}

	}

}

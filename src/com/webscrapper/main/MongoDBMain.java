package com.webscrapper.main;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.webscapper.ServiceImpl.WebScrapperServiceImpl;

public class MongoDBMain {
	
	public static void main(String[] args) {
		String json = "{'database' : 'test','table' : 'hosting'," +
				  "'detail' : {'records' : 99, 'index' : 'index1', 'active' : 'true'}}}";
		
		WebScrapperServiceImpl serviceImpl = new WebScrapperServiceImpl();
		DBCollection collection = serviceImpl.insertData(json);
		
		DBCursor cursorDocJSON = collection.find();
		while (cursorDocJSON.hasNext()) {
			System.out.println(cursorDocJSON.next());
		}

	}

}

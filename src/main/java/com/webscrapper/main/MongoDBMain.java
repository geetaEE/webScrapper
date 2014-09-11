package com.webscrapper.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.webscapper.request.ExtractRequest;
import com.webscapper.response.ExtractResponse;
import com.webscapper.service.impl.DataAccessServiceImpl;
import com.webscapper.service.impl.TextExtractService;

public class MongoDBMain {
	
	public static void main(String[] args) {
		String json = "{'database':'test','table':'hosting','detail':{'records':99,'index':'index1','active':'true'}}";
		
		DataAccessServiceImpl serviceImpl = new DataAccessServiceImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<String> colsList = new ArrayList<String>();
		colsList.add("COL1");
		colsList.add("COL2");
		colsList.add("COL3");
		
		List<List<String>> rowsList = new ArrayList<List<String>>();
		rowsList.add(colsList);
		rowsList.add(colsList);
		
		List<List<List<String>>> tablesList = new ArrayList<List<List<String>>>();
		tablesList.add(rowsList);
		tablesList.add(rowsList);
		
		map.put("Title", "Test");
		map.put("URL", "www.google.com");
		map.put("Export Type", "DB");
		map.put("Tables", tablesList.toString());
		
		
		DBCollection collection = serviceImpl.insertData(map);
		
		DBCursor cursorDocJSON = collection.find();
		while (cursorDocJSON.hasNext()) {
			System.out.println(cursorDocJSON.next());
		}

	}

}

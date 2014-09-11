package com.webscapper.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mongodb.DBCollection;
import com.webscapper.request.ExportRequest;
import com.webscapper.response.ExportResponse;
import com.webscapper.response.ExtractResponse;
import com.webscrapper.constants.CommonConstants;
import com.webscrapper.constants.ExportType;
import com.webscrapper.service.DataAccessService;
import com.webscrapper.service.ExportService;

public class ExportToDBService implements ExportService {
	
	/*This method is used to insert the tabular data into DB
	 * based on the ExportType*/
	@Override
	public ExportResponse export(ExportRequest request) {
		DataAccessService serviceImpl = null;
		Map<String, String> map = null;
		ExportResponse exportResponse = null;
		try {
			exportResponse = new ExportResponse();
			map = new HashMap<String, String>();
			if (request.getExportType().equals(ExportType.DB)) {
				ExtractResponse response = request.getExtractResponse();
				List<List<List<String>>> tablesList = response.getTables();
				Iterator<List<List<String>>> tableIterator = tablesList
						.iterator();
				while (tableIterator.hasNext()) {
					List<List<String>> rowsList = tableIterator.next();
					Iterator<List<String>> rowIterator = rowsList.iterator();
					while (rowIterator.hasNext()) {
						List<String> colsList = rowIterator.next();
						Iterator<String> colIterator = colsList.iterator();
						while (colIterator.hasNext()) {
							map.put(CommonConstants.TITLE, request.getTitle());
							map.put(CommonConstants.URL, request.getUrl());
							map.put(CommonConstants.CONTENT_TYPE, request.getExportType()
									.getType());
							map.put(CommonConstants.TABLES, colIterator.next());
						}
					}
				}

				serviceImpl = new DataAccessServiceImpl();
				DBCollection collection = serviceImpl.insertData(map);
				
				if(collection !=null){
					exportResponse.setSuccess(true);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}

		return exportResponse;
	}

}

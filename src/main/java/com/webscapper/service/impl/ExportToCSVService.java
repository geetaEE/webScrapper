package com.webscapper.service.impl;

import java.io.FileWriter;
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
import com.webscrapper.service.ExportService;

public class ExportToCSVService implements ExportService{

	@Override
	public ExportResponse export(ExportRequest request) {
		String s = "C:\\Users\\"+request.getTitle()+".csv";
		// TODO Auto-generated method stub
		try {
			FileWriter writer = new FileWriter(s);
			if (request.getExportType().equals(ExportType.CSV)) {
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
							writer.append(request.getTitle());
							writer.append(',');
							writer.append(request.getUrl());
							writer.append(',');
							writer.append(request.getExportType()
							.getType());
							writer.append(',');
							writer.append(colIterator.next());
							writer.append('\n');
						}
					}
				}
			}
			
			writer.flush();
			writer.close();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		return null;
	}

}

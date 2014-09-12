package com.webscapper.service;

import java.util.ArrayList;
import java.util.List;

import com.webscapper.request.ExportRequest;
import com.webscapper.response.ExtractResponse;
import com.webscapper.service.impl.ExportToDBService;

public class UnitTestDB 
{
	 public static void main(String[] args)
	 {
		 List<String> columnValue1 = new ArrayList<String>();
		 columnValue1.add("Test1");
		 columnValue1.add("Test2");
		 
		 List<String> columnValue2 = new ArrayList<String>();
		 columnValue2.add("Test4");
		 columnValue2.add("Test5");
		 
		 List<List<String>> rowList1 = new ArrayList<List<String>>();
		 rowList1.add(columnValue1);
		 rowList1.add(columnValue2);
		 
		 List<List<String>> rowList2 = new ArrayList<List<String>>();
		 rowList2.add(columnValue2);
		 
		 List<List<List<String>>> tableList = new ArrayList<List<List<String>>>();		 
		 tableList.add(rowList1);
		 tableList.add(rowList2);
		 
		 ExportRequest exportRequest = new ExportRequest();
		 
		 ExtractResponse extractResponse = new ExtractResponse();
		 extractResponse.setTables(tableList);
		 
		 exportRequest.setExtractResponse(extractResponse);
		 
		 ExportToDBService exportToDBService = new ExportToDBService();
		 
		 exportToDBService.export(exportRequest);
	 }
}

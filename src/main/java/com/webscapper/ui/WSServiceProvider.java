package com.webscapper.ui;

import java.util.List;

import com.webscapper.factory.ExportServiceFactory;
import com.webscapper.factory.ExtractServiceFactory;
import com.webscapper.request.ExportRequest;
import com.webscapper.request.ExtractRequest;
import com.webscapper.response.ExportResponse;
import com.webscapper.response.ExtractResponse;
import com.webscrapper.constants.ContentType;
import com.webscrapper.constants.ExportType;

public class WSServiceProvider 
{
	public ExtractRequest buildExtractRequest(String url, ContentType contentType)
	{
		ExtractRequest extractRequest = new ExtractRequest();
		extractRequest.setUrl(url);
		extractRequest.setContentType(contentType);
		return extractRequest;
	}
	
	public ExportRequest buildExportRequest(String url, String title, ExtractResponse extractResponse, ExportType exportType, List<String> tagsList, String location)
	{
		ExportRequest exportRequest = new ExportRequest();
		exportRequest.setUrl(url);
		exportRequest.setTitle(title);
		exportRequest.setExtractResponse(extractResponse);
		exportRequest.setExportType(exportType);
		exportRequest.setTagsList(tagsList);
		exportRequest.setLocation(location);
		
		return exportRequest;
	}
	
	public ExtractResponse executeExtractOperation(ExtractRequest extractRequest)
	{
		ExtractResponse extractResponse = ExtractServiceFactory.getInstance(extractRequest.getContentType()).extract(extractRequest);
		return extractResponse;
	}
	
	public ExportResponse executeExportOperation(ExportRequest exportRequest)
	{
		ExportResponse exportResponse = ExportServiceFactory.getInstance(exportRequest.getExportType()).export(exportRequest);
		return exportResponse;
	}
	
	public String[] fetchColumnNameForPreview(ExtractResponse extractResponse)
	{
		List<List<List<String>>> tables = extractResponse.getTables();
        List<String> columns = tables.get(0).get(0);
        String[] columnNames = columns.toArray(new String[columns.size()]);
        return columnNames;
        
	}
	
	public String[][] fetchColumnValuesForPreview(ExtractResponse extractResponse)
	{
		return null;
	}
}

package com.webscapper.ui;

import com.webscapper.factory.ExtractServiceFactory;
import com.webscapper.request.ExtractRequest;
import com.webscapper.response.ExtractResponse;
import com.webscrapper.constants.ContentType;

public class WSServiceProvider 
{
	public ExtractRequest buildExtractRequest(String url, ContentType contentType)
	{
		ExtractRequest extractRequest = new ExtractRequest();
		extractRequest.setUrl(url);
		extractRequest.setContentType(contentType);
		return extractRequest;
	}
	
	public ExtractResponse startExtraction(ExtractRequest extractRequest)
	{
		ExtractResponse extractResponse = ExtractServiceFactory.getInstance(extractRequest.getContentType()).extract(extractRequest);
		return extractResponse;
	}
	
	public String[] fetchColumnNameForPreview(ExtractResponse extractResponse)
	{
		return null;
	}
	
	public String[][] fetchColumnValuesForPreview(ExtractResponse extractResponse)
	{
		return null;
	}
}

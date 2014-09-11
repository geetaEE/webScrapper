package com.webscrapper.service;

import com.webscapper.request.ExportRequest;
import com.webscapper.response.ExportResponse;

public interface ExportService {	
	/*This method is used to insert the tabular data into DB
	 * based on the ExportType*/
	ExportResponse export(ExportRequest request);
}

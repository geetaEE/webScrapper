package com.webscapper.request;

import java.util.List;

import com.webscapper.response.ExtractResponse;
import com.webscrapper.constants.ExportType;

public class ExportRequest {
	 /** The url. */
    private String url;   
    /** The title.*/
    private String title;
    /** The extractResponse.*/
    private ExtractResponse extractResponse;
    /** The exportType.*/
    private ExportType exportType;
    /** The tagsList.*/
    private List<String> tagsList;
    
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ExtractResponse getExtractResponse() {
		return extractResponse;
	}
	public void setExtractResponse(ExtractResponse extractResponse) {
		this.extractResponse = extractResponse;
	}
	public ExportType getExportType() {
		return exportType;
	}
	public void setExportType(ExportType exportType) {
		this.exportType = exportType;
	}
	public List<String> getTagsList() {
		return tagsList;
	}
	public void setTagsList(List<String> tagsList) {
		this.tagsList = tagsList;
	}
    
    

}

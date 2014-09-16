package com.webscapper.request;

import java.util.List;

import com.webscapper.response.ExtractResponse;
import com.webscrapper.constants.ExportType;

/** Export request. */
public class ExportRequest {
    /** The url. */
    private String url;
    /** The title. */
    private String title;
    /** The extractResponse. */
    private ExtractResponse extractResponse;
    /** The exportType. */
    private ExportType exportType;
    /** The tagsList. */
    private List<String> tagsList;
    /** The location. */
    private String location;
    /** image URL List **/
    private List<String> imageURLList;
    
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

	public List<String> getImageURLList() {
		return imageURLList;
	}

	public void setImageURLList(List<String> imageURLList) {
		this.imageURLList = imageURLList;
	}    
}
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

    /** @return the url */
    public String getUrl() {
        return url;
    }

    /** @param url
     *            the url to set */
    public void setUrl(String url) {
        this.url = url;
    }

    /** @return the title */
    public String getTitle() {
        return title;
    }

    /** @param title
     *            the title to set */
    public void setTitle(String title) {
        this.title = title;
    }

    /** @return the extractResponse */
    public ExtractResponse getExtractResponse() {
        return extractResponse;
    }

    /** @param extractResponse
     *            the extractResponse to set */
    public void setExtractResponse(ExtractResponse extractResponse) {
        this.extractResponse = extractResponse;
    }

    /** @return the exportType */
    public ExportType getExportType() {
        return exportType;
    }

    /** @param exportType
     *            the exportType to set */
    public void setExportType(ExportType exportType) {
        this.exportType = exportType;
    }

    /** @return the tagsList */
    public List<String> getTagsList() {
        return tagsList;
    }

    /** @param tagsList
     *            the tagsList to set */
    public void setTagsList(List<String> tagsList) {
        this.tagsList = tagsList;
    }

    /** @return the location */
    public String getLocation() {
        return location;
    }

    /** @param location
     *            the location to set */
    public void setLocation(String location) {
        this.location = location;
    }

    /** @return the imageURLList */
    public List<String> getImageURLList() {
        return imageURLList;
    }

    /** @param imageURLList
     *            the imageURLList to set */
    public void setImageURLList(List<String> imageURLList) {
        this.imageURLList = imageURLList;
    }
}
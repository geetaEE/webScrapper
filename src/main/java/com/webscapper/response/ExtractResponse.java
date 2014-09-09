package com.webscapper.response;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.webscrapper.constants.TagType;

/** The extract response. */
public class ExtractResponse {
    /** The tagDataMap. */
    private Map<TagType, String> tagDataMap;
    /** The tables. */
    private List<List<List<String>>> tables;
    /** The image urls. */
    private Set<String> imageUrls;

    /** Get tagDataMap.
     * 
     * @return the tagDataMap */
    public Map<TagType, String> getTagDataMap() {
        return tagDataMap;
    }

    /** Set tagDataMap.
     * 
     * @param tagDataMap
     *            the tagDataMap to set */
    public void setTagDataMap(Map<TagType, String> tagDataMap) {
        this.tagDataMap = tagDataMap;
    }

    /** Get table data.
     * 
     * @return the tables */
    public List<List<List<String>>> getTables() {
        return tables;
    }

    /** Set table data.
     * 
     * @param tables
     *            the tables to set */
    public void setTables(List<List<List<String>>> tables) {
        this.tables = tables;
    }

    /** Get image urls.
     * 
     * @return the imageUrls */
    public Set<String> getImageUrls() {
        return imageUrls;
    }

    /** Set image urls.
     * 
     * @param imageUrls
     *            the imageUrls to set */
    public void setImageUrls(Set<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
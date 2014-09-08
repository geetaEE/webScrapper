package com.webscapper.request;

import java.util.List;

import com.webscrapper.constants.ContentType;
import com.webscrapper.constants.TagType;

/** The extract request. */
public class ExtractRequest {
    /** The url. */
    private String url;
    /** The contentType. */
    private ContentType contentType;
    /** The tag types. */
    private List<TagType> tags;

    /** Get url.
     * 
     * @return url the url */
    public String getUrl() {
        return url;
    }

    /** Set url.
     * 
     * @param url
     *            the url */
    public void setUrl(String url) {
        this.url = url;
    }

    /** Get content type.
     * 
     * @return contentType the content type */
    public ContentType getContentType() {
        return contentType;
    }

    /** Set content type.
     * 
     * @param contentType
     *            the content type */
    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    /** Get tags.
     * 
     * @return tags the tags */
    public List<TagType> getTags() {
        return tags;
    }

    /** Set tags.
     * 
     * @param tags
     *            the tags */
    public void setTags(List<TagType> tags) {
        this.tags = tags;
    }
}
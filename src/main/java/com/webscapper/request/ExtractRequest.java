package com.webscapper.request;

import com.webscrapper.constants.ContentType;

/** The extract request. */
public class ExtractRequest {
    /** The url. */
    private String url;
    /** The contentType. */
    private ContentType contentType;

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
}
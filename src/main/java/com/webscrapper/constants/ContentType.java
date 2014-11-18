package com.webscrapper.constants;

import java.util.ArrayList;
import java.util.List;

/** Defines Content types. */
public enum ContentType {
    /** The Text. */
    TEXT("Text"),
    /** The Image. */
    IMAGE("Image");

    /** The type. */
    private String type;

    /** Instantiates a new ContentType.
     * 
     * @param type
     *            the type */
    private ContentType(String type) {
        this.type = type;
    }

    /** Gets the type.
     * 
     * @return the type */
    public String getType() {
        return type;
    }

    /** Gets the ContentType.
     * 
     * @param type
     *            the type
     * @return the ContentType */
    public static ContentType getContentType(String type) {
        for (ContentType contentType : ContentType.values()) {
            if (contentType.getType().equals(type)) {
                return contentType;
            }
        }
        return null;
    }

    /** Gets the ContentType.
     * 
     * @param type
     *            the type
     * @return the ContentType */
    public static String[] getContentArray() {
        List<String> values = new ArrayList<String>();
        for (ContentType contentType : ContentType.values()) {
            values.add(contentType.getType());
        }
        return values.toArray(new String[values.size()]);
    }
}
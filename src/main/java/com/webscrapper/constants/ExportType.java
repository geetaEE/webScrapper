package com.webscrapper.constants;


/** The Enum ExportType.
 * 
 * Defines Content types. */
public enum ExportType {
    /** The Text. */
    DB("DB"),
    /** The CSV. */
    CSV("CSV"),
    /** The Text. */
    TEXT("Text"),
    /** The DOC. */
    DOC("Doc"),

    /** The image. */
    IMAGE("Image");

    /** The type. */
    private String type;

    /** Instantiates a new ContentType.
     * 
     * @param type
     *            the type */
    private ExportType(String type) {
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
    public static ExportType getExportType(String type) {
        for (ExportType exportType : ExportType.values()) {
            if (exportType.getType().equals(type)) {
                return exportType;
            }
        }
        return null;
    }
}
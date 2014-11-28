package com.webscrapper.constants;

/** UnStructuredExtractDocType enum. */
public enum UnStructuredExtractDocType {
    DOC("Doc"), TEXT("Text");
    private String description;

    /** Constructor.
     * 
     * @param description
     *            description */
    UnStructuredExtractDocType(String description) {
        this.description = description;
    }

    /** Get description.
     * 
     * @return description */
    public String getDescription() {
        return description;
    }

    /** Set description.
     * 
     * @param description
     *            description */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
package com.webscrapper.constants;

/** StructuredExtractDocType class. */
public enum StructuredExtractDocType {
    DB("DB"), CSV("CSV");
    private String description;

    /** Constructor.
     * 
     * @param description
     *            description */
    StructuredExtractDocType(String description) {
        this.description = description;
    }

    /** Get description.
     * 
     * @return description description */
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
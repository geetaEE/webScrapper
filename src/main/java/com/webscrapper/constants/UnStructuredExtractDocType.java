package com.webscrapper.constants;

public enum UnStructuredExtractDocType {
    DOC("Doc"), TEXT("Text");
    private String description;

    UnStructuredExtractDocType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}

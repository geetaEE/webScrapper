package com.webscrapper.constants;

public enum StructuredExtractDocType {
    DB("DB"), CSV("CSV");
    private String description;

    StructuredExtractDocType(String description) {
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

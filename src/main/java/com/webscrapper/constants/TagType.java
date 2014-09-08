package com.webscrapper.constants;

/** Defines Html Tag types. */
public enum TagType {
    /** The div. */
    DIV("div", "Div"),
    /** The bold. */
    BOLD("b", "Bold Text"),
    /** The Paragraph. */
    PARAGRAPH("p", "Paragraph"),
    /** The Span. */
    SPAN("span", "Span"),
    /** The Hyper Link. */
    ANCHOR("a", "Hyper Link");

    /** The name. */
    private String name;
    /** The display name. */
    private String displayName;

    /** Instantiates a new TagType.
     * 
     * @param name
     *            the name
     * @param displayName
     *            the displayName */
    private TagType(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    /** Gets the name.
     * 
     * @return the name */
    public String getName() {
        return name;
    }

    /** Gets the displayName.
     * 
     * @return the displayName */
    public String getDisplayName() {
        return displayName;
    }

    /** Gets the tagType.
     * 
     * @param name
     *            the name
     * @return the tagType */
    public static TagType getTagTypeByName(String name) {
        for (TagType tagType : TagType.values()) {
            if (tagType.getName().equals(name)) {
                return tagType;
            }
        }
        return null;
    }

    /** Gets the tagType.
     * 
     * @param displayName
     *            the displayName
     * @return the tagType */
    public static TagType getTagTypeByDisplayName(String displayName) {
        for (TagType tagType : TagType.values()) {
            if (tagType.getDisplayName().equals(displayName)) {
                return tagType;
            }
        }
        return null;
    }
}
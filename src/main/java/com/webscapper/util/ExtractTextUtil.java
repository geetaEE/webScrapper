package com.webscapper.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.webscrapper.constants.TagType;

/** The Class ExtractUtil. */
public final class ExtractTextUtil {
    /** Instantiates a new ExtractUtil. */
    private ExtractTextUtil() {

    }

    /** Get tag data map.
     * 
     * @param doc
     *            html document
     * @return tag data map */
    public static Map<TagType, String> getTagDataMap(Document doc) {
        Map<TagType, String> tagDataMap = new LinkedHashMap<TagType, String>();
        TagType[] tags = TagType.values();
        for (TagType tagType : tags) {
            StringBuilder textB = new StringBuilder();
            for (Element element : doc.select(tagType.getName())) {
                String elemTxt = element.text().trim();
                if (!textB.toString().contains(elemTxt)) {
                    if (!textB.toString().isEmpty()) {
                        textB.append(" ");
                    }
                    textB.append(elemTxt);
                }
            }
            String text = textB.toString();
            if (!text.isEmpty()) {
                tagDataMap.put(tagType, text);
            }
        }
        return tagDataMap;
    }
}
package com.webscapper.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.webscapper.request.ExtractRequest;
import com.webscapper.response.ExtractResponse;
import com.webscrapper.constants.TagType;

/** The extract text content service. */
public class TextExtractService extends BaseExtractService {
    @Override
    public ExtractResponse extract(ExtractRequest request) {
        if (request != null && request.getUrl() != null) {
            Document doc = extractDocument(request.getUrl());
            if (doc != null) {
                doc = Jsoup.parse(doc.html(), "UTF-8");
                ExtractResponse response = new ExtractResponse();
                // Non tabular data
                Map<TagType, String> tagDataMap = new LinkedHashMap<TagType, String>();
                StringBuilder textB = new StringBuilder();
                TagType[] tags = TagType.values();
                for (TagType tagType : tags) {
                    for (Element element : doc.select(tagType.getName())) {
                        String elemTxt = element.text();
                        if (!textB.toString().contains(elemTxt)) {
                            if (textB.toString().isEmpty()) {
                                textB.append(elemTxt);
                            } else {
                                textB.append(" ");
                                textB.append(elemTxt);
                            }
                        }
                    }
                    String text = textB.toString();
                    if (!text.isEmpty()) {
                        tagDataMap.put(tagType, text);
                    }
                }
                if (!tagDataMap.isEmpty()) {
                    response.setTagDataMap(tagDataMap);
                }
                // Tabular data.
                List<List<List<String>>> tables = new ArrayList<List<List<String>>>();
                for (Element table : doc.select("table")) {
                    List<List<String>> rows = new ArrayList<List<String>>();
                    for (Element row : table.select("tr")) {
                        List<String> columns = new ArrayList<String>();
                        Elements tds = null;
                        tds = row.select("th");
                        for (Element td : tds) {
                            columns.add(td.text());
                        }
                        tds = row.select("td");
                        for (Element td : tds) {
                            columns.add(td.text());
                        }
                        rows.add(columns);
                    }
                    tables.add(rows);
                }
                if (!tables.isEmpty()) {
                    response.setTables(tables);
                }
                // return response
                if (!response.getTagDataMap().isEmpty() || !response.getTables().isEmpty()) {
                    return response;
                }
            }
        }
        return null;
    }
}
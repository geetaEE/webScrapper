package com.webscapper.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.webscapper.request.ExtractRequest;

/** The extract table content service. */
public class TableExtractService extends BaseExtractService {
    @Override
    public List<List<List<String>>> extract(ExtractRequest request) {
        if (request != null && request.getUrl() != null) {
            Document doc = extractDocument(request.getUrl());
            if (doc != null) {
                List<String> columns = new ArrayList<String>();
                List<List<String>> rows = new ArrayList<List<String>>();
                List<List<List<String>>> tables = new ArrayList<List<List<String>>>();
                for (Element table : doc.select("table")) {
                    for (Element row : table.select("tr")) {
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
                if (!columns.isEmpty() && !rows.isEmpty() && !tables.isEmpty()) {
                    return tables;
                }
            }
        }
        return null;
    }
}
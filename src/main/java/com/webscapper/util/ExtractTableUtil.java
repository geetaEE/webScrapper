package com.webscapper.util;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.webscrapper.constants.CommonConstants;

/** The Class ExtractUtil. */
public final class ExtractTableUtil {
    /** Instantiates a new ExtractUtil. */
    private ExtractTableUtil() {}

    /** Get table list.
     * 
     * @param doc
     *            html document
     * @return table list */
    public static List<List<List<String>>> getTableList(Document doc) {
        List<List<List<String>>> tables = new ArrayList<List<List<String>>>();
        for (Element table : doc.select(CommonConstants.TABLE_TAG)) {
            List<List<String>> rows = new ArrayList<List<String>>();
            for (Element row : table.select(CommonConstants.TR_TAG)) {
                List<String> columns = new ArrayList<String>();
                Elements tds = null;
                tds = row.select(CommonConstants.TH_TAG);
                for (Element td : tds) {
                    columns.add(td.text());
                }
                tds = row.select(CommonConstants.TD_TAG);
                for (Element td : tds) {
                    columns.add(td.text());
                }
                rows.add(columns);
            }
            tables.add(rows);
        }
        return tables;
    }
}
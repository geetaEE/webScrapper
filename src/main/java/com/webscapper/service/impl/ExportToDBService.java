package com.webscapper.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.DBCollection;
import com.webscapper.request.ExportRequest;
import com.webscapper.response.ExportResponse;
import com.webscapper.response.ExtractResponse;
import com.webscrapper.constants.CommonConstants;
import com.webscrapper.service.DataAccessService;
import com.webscrapper.service.ExportService;

/** Export to DB service. */
public class ExportToDBService implements ExportService {

    private static final String DATE_FORMAT = "yyyy-MM-dd-HH:mm:ss";
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);

    /*
     * This method is used to insert the tabular data into DB based on the ExportType
     */
    @Override
    public ExportResponse export(ExportRequest request) {
        DataAccessService serviceImpl = null;
        Map<String, Object> map = null;
        ExportResponse exportResponse = new ExportResponse();
        try {
            ExtractResponse response = request.getExtractResponse();
            List<List<List<String>>> tablesList = response != null ? response.getTables() : null;
            if (tablesList != null) {
                map = new HashMap<String, Object>();
                List<Map<String, List<Map<String, List<String>>>>> tableList = new ArrayList<Map<String, List<Map<String, List<String>>>>>();

                for (List<List<String>> table : tablesList) {
                    List<Map<String, List<String>>> rowList = new ArrayList<Map<String, List<String>>>();
                    for (List<String> row : table) {
                        Map<String, List<String>> colMap = new HashMap<String, List<String>>();
                        colMap.put("Columns", row);
                        rowList.add(colMap);
                    }

                    Map<String, List<Map<String, List<String>>>> rowMap = new HashMap<String, List<Map<String, List<String>>>>();
                    rowMap.put("Rows", rowList);
                    tableList.add(rowMap);
                }
                map.put(CommonConstants.TITLE, request.getTitle() + DATE_FORMATTER.format(new Date()));
                map.put(CommonConstants.URL, request.getUrl());
                map.put(CommonConstants.TABLES, tableList);

                serviceImpl = new DataAccessServiceImpl();
                DBCollection collection = serviceImpl.insertData(map);

                if (collection != null) {
                    exportResponse.setSuccess(true);
                }
            }
        } catch (Exception e) {
            exportResponse.setSuccess(false);
        }

        return exportResponse;
    }
}

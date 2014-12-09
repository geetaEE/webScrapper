package com.webscapper.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.webscapper.exception.WebScrapperException;
import com.webscapper.request.ExportRequest;
import com.webscapper.response.ExportResponse;
import com.webscapper.response.ExtractResponse;
import com.webscrapper.constants.CommonConstants;
import com.webscrapper.service.ExportService;

/** Export to DB service. */
public enum ExportToDBService implements ExportService {
    INSTANCE;
    @Override
    public ExportResponse export(ExportRequest request) throws WebScrapperException {
        ExportResponse exportResponse = new ExportResponse();

        ExtractResponse response = request.getExtractResponse();
        List<List<List<String>>> tablesList = response != null ? response.getTables() : null;
        if (tablesList != null) {
            List<Map<String, List<Map<String, List<String>>>>> tableList = new ArrayList<Map<String, List<Map<String, List<String>>>>>();
            for (List<List<String>> table : tablesList) {
                List<Map<String, List<String>>> rowList = new ArrayList<Map<String, List<String>>>();
                for (List<String> row : table) {
                    Map<String, List<String>> colMap = new HashMap<String, List<String>>();
                    colMap.put(CommonConstants.COLUMNS, row);
                    rowList.add(colMap);
                }

                Map<String, List<Map<String, List<String>>>> rowMap = new HashMap<String, List<Map<String, List<String>>>>();
                rowMap.put(CommonConstants.ROWS, rowList);
                tableList.add(rowMap);
            }

            Map<String, Object> map = new HashMap<String, Object>();
            map.put(CommonConstants.TITLE, request.getTitle() + new SimpleDateFormat(CommonConstants.DATE_FORMAT).format(new Date()));
            map.put(CommonConstants.URL, request.getUrl());
            map.put(CommonConstants.TABLES, tableList);

            DataAccessServiceImpl.INSTANCE.insertData(map);
            exportResponse.setSuccess(true);
        }

        return exportResponse;
    }
}
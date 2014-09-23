package com.webscapper.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.mongodb.DBCollection;
import com.webscapper.request.ExportRequest;
import com.webscapper.response.ExportResponse;
import com.webscapper.response.ExtractResponse;
import com.webscrapper.constants.CommonConstants;
import com.webscrapper.service.DataAccessService;
import com.webscrapper.service.ExportService;

/**
 * @author ruby.jha
 * Export to DB service.
 */
public class ExportToDBService implements ExportService {
	private static Logger logger = Logger.getLogger(ExportToDBService.class);
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
                        colMap.put(CommonConstants.COLUMNS, row);
                        rowList.add(colMap);
                    }

                    Map<String, List<Map<String, List<String>>>> rowMap = new HashMap<String, List<Map<String, List<String>>>>();
                    rowMap.put(CommonConstants.ROWS, rowList);
                    tableList.add(rowMap);
                }
                map.put(CommonConstants.TITLE, request.getTitle() + CommonConstants.DATE_FORMATTER.format(new Date()));
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

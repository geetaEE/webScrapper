package com.webscapper.service.impl;

import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.webscapper.request.ExportRequest;
import com.webscapper.response.ExportResponse;
import com.webscapper.response.ExtractResponse;
import com.webscapper.util.CommonUtil;
import com.webscrapper.constants.CommonConstants;
import com.webscrapper.service.ExportService;

public class ExportToCSVService implements ExportService {
    private static Logger logger = Logger.getLogger(ExportToCSVService.class);
    /* 
	 * This method will export  data into CSV file. 
	 */
    @Override
    public ExportResponse export(ExportRequest request) {
        logger.info("CSV export executing");
        ExportResponse exportResponse = new ExportResponse();
        String fileName = null;
        try {
            fileName = CommonUtil.getFileName(request.getLocation(), request.getTitle(), CommonConstants.EXT_CSV);
            FileWriter writer = new FileWriter(fileName);
            ExtractResponse response = request.getExtractResponse();
            List<List<List<String>>> tablesList = response != null ? response.getTables() : null;
            if (tablesList != null) {
                Iterator<List<List<String>>> tableIterator = tablesList.iterator();
                while (tableIterator.hasNext()) {
                    List<List<String>> rowsList = tableIterator.next();
                    Iterator<List<String>> rowIterator = rowsList.iterator();
                    while (rowIterator.hasNext()) {
                        int commaCounter = 0;
                        List<String> colsList = rowIterator.next();
                        Iterator<String> colIterator = colsList.iterator();
                        while (colIterator.hasNext()) {
                            if (commaCounter > 0) {
                                writer.append(",");
                            }
                            writer.append(colIterator.next());
                            commaCounter++;
                        }

                        writer.append("\r\n");
                    }
                    writer.append("\r\n");
                }
            }

            writer.close();

            exportResponse.setSuccess(true);
        } catch (Exception e) {
            exportResponse.setSuccess(false);
            logger.warn(e);
        }
        return exportResponse;
    }
}
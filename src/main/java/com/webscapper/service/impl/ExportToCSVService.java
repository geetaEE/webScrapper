package com.webscapper.service.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.webscapper.exception.WebScrapperException;
import com.webscapper.request.ExportRequest;
import com.webscapper.response.ExportResponse;
import com.webscapper.response.ExtractResponse;
import com.webscapper.util.CommonUtil;
import com.webscrapper.constants.CommonConstants;
import com.webscrapper.service.ExportService;

/** Export to CSV service. */
public class ExportToCSVService implements ExportService {
    private static final Logger logger = Logger.getLogger(ExportToCSVService.class);

    @Override
    public ExportResponse export(ExportRequest request) throws WebScrapperException {
        logger.info("CSV export executing");
        ExportResponse exportResponse = new ExportResponse();
        String fileName = CommonUtil.getFileName(request.getLocation(), request.getTitle(), CommonConstants.EXT_CSV);
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName);
        } catch (IOException e) {
            logger.error(CommonConstants.EXP_FILE_EXIST_ERROR + fileName, e);
            throw new WebScrapperException(CommonConstants.EXP_FILE_EXIST_ERROR + fileName);
        }
        ExtractResponse response = request.getExtractResponse();
        List<List<List<String>>> tablesList = response != null ? response.getTables() : null;
        try {
            if (tablesList != null) {
                for (List<List<String>> rowsList : tablesList) {
                    for (List<String> colsList : rowsList) {
                        int commaCounter = 0;
                        for (String col : colsList) {
                            if (commaCounter > 0) {
                                writer.append(",");
                            }
                            writer.append(col);
                            commaCounter++;
                        }
                        writer.append("\r\n");
                    }
                    writer.append("\r\n");
                }
            }
            exportResponse.setSuccess(true);
        } catch (IOException e) {
            logger.error(CommonConstants.EXP_FILE_OPER_ERROR + fileName, e);
            throw new WebScrapperException(CommonConstants.EXP_FILE_OPER_ERROR + fileName);

        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    logger.error(CommonConstants.EXP_FILE_OPER_ERROR + fileName, e);
                    throw new WebScrapperException(CommonConstants.EXP_FILE_OPER_ERROR + fileName);
                }
            }
        }
        return exportResponse;
    }
}
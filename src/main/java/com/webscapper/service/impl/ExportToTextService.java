package com.webscapper.service.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.webscapper.exception.WebScrapperException;
import com.webscapper.request.ExportRequest;
import com.webscapper.response.ExportResponse;
import com.webscapper.response.ExtractResponse;
import com.webscapper.util.CommonUtil;
import com.webscrapper.constants.CommonConstants;
import com.webscrapper.constants.TagType;
import com.webscrapper.service.ExportService;

/** @author geeta.chaudhary */
public class ExportToTextService implements ExportService {
    private static Logger logger = Logger.getLogger(ExportToTextService.class);

    @Override
    public ExportResponse export(ExportRequest request) throws WebScrapperException {
        logger.info("Text export executing");
        ExportResponse exportResponse = new ExportResponse();
        String fileName = CommonUtil.getFileName(request.getLocation(), request.getTitle(), CommonConstants.EXT_TEXT);
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName);
        } catch (IOException e) {
            logger.error(CommonConstants.EXP_FILE_EXIST_ERROR + fileName, e);
            throw new WebScrapperException(CommonConstants.EXP_FILE_EXIST_ERROR + fileName);            
        }
        ExtractResponse response = request.getExtractResponse();
        List<String> tagsList = request.getTagsList();
        Map<TagType, String> tagData = response != null ? response.getTagDataMap() : null;

        try {
            if (tagsList != null) {
                for (String tags : tagsList) {
                    for (Map.Entry<TagType, String> entry : tagData.entrySet()) {
                        if (entry.getKey().getDisplayName().equals(tags)) {
                            writer.append(entry.getKey() + "\n");
                            writer.append(entry.getValue() + "\n");
                            writer.append("\n");
                        }
                    }
                }
                exportResponse.setSuccess(true);
            }
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
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

/** Export To Doc Service */
public class ExportToDocService implements ExportService {
    private static final Logger logger = Logger.getLogger(ExportToDocService.class);

    @Override
    public ExportResponse export(ExportRequest request) throws WebScrapperException {
        String fileName = null;
        ExportResponse exportResponse = new ExportResponse();
        ExtractResponse response = null;
        FileWriter writer = null;
        List<String> tagsList = null;
        if (request != null) {
            fileName = CommonUtil.getFileName(request.getLocation(), request.getTitle(), CommonConstants.EXT_DOC);
            response = request.getExtractResponse();
            tagsList = request.getTagsList();
        }
        try {
            writer = new FileWriter(fileName);
        } catch (IOException e) {
            logger.error(CommonConstants.EXP_FILE_EXIST_ERROR + fileName, e);
            throw new WebScrapperException(CommonConstants.EXP_FILE_EXIST_ERROR + fileName);           
        }

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
        } 
        catch (IOException e) 
        {
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
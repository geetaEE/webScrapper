package com.webscapper.service.impl;

import java.io.FileWriter;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.webscapper.request.ExportRequest;
import com.webscapper.response.ExportResponse;
import com.webscapper.response.ExtractResponse;
import com.webscapper.util.CommonUtil;
import com.webscrapper.constants.CommonConstants;
import com.webscrapper.constants.TagType;
import com.webscrapper.service.ExportService;

/**
 * @author geeta.chaudhary
 *
 */
public class ExportToTextService  implements ExportService{
	private static Logger logger = Logger.getLogger(ExportToTextService.class);
	/* 
	 * This method will export non tabular data into Text file. 
	 */
	@Override
	public ExportResponse export(ExportRequest request) {
	    logger.info("Text export executing");
		String fileName = null;
		ExportResponse exportResponse = new ExportResponse();
		try
			{
			fileName = CommonUtil.getFileName(request.getLocation(),
					request.getTitle(), CommonConstants.EXT_TEXT);
			FileWriter writer = new FileWriter(fileName);
			ExtractResponse response = request.getExtractResponse();
			List<String> tagsList = request.getTagsList();
			Map<TagType, String> tagData = response != null ? response
					.getTagDataMap() : null;
	
			if (tagsList != null) 
			{
				for (String tags : tagsList)
				{
					for (Map.Entry<TagType, String> entry : tagData.entrySet()) 
					{
						if (entry.getKey().getDisplayName().equals(tags)) 
						{
							writer.append(entry.getKey() + "\n");
							writer.append(entry.getValue() + "\n");
							writer.append("\n");
						}
					}
				}
				exportResponse.setSuccess(true);
			}
			writer.close();

			
		} catch (Exception e) {
			exportResponse.setSuccess(false);
			logger.warn(e);
		}
		return exportResponse;
	}

}

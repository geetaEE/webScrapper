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
 * @author ruby.jha
 * Export To Doc Service
 */
public class ExportToDocService implements ExportService {
	private static Logger logger = Logger.getLogger(ExportToDocService.class);	
	/* 
	 * This method will export non tabular data into doc. 
	   This will take file name, tagsList from the UI and return ExportResponse
	 */
	@Override
	public ExportResponse export(ExportRequest request)
	{
		String fileName = null;
		ExportResponse exportResponse = null;
		ExtractResponse response = null;
		FileWriter writer = null;
		List<String> tagsList = null;
		try
			{
			exportResponse = new ExportResponse();
			if (request != null) 
			{
				fileName = CommonUtil.getFileName(request.getLocation(),
						request.getTitle(), CommonConstants.EXT_DOC);
				response = request.getExtractResponse();
				tagsList = request.getTagsList();
			}
			writer = new FileWriter(fileName);
			
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
		}
		return exportResponse;
	}

}

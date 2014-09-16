package com.webscapper.service.impl;

import com.webscapper.request.ExportRequest;
import com.webscapper.response.ExportResponse;
import com.webscrapper.service.ExportService;

public class ExportToTextService  implements ExportService{

	@Override
	public ExportResponse export(ExportRequest request) {
		String fileName = request.getLocation() + File.separator + request.getTitle()
				+ ".txt";
		ExportResponse exportResponse = new ExportResponse();
		try
			{
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
		}
		return exportResponse;
	}

}

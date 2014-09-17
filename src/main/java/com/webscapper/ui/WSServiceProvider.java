package com.webscapper.ui;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import com.webscapper.factory.ExportServiceFactory;
import com.webscapper.factory.ExtractServiceFactory;
import com.webscapper.request.ExportRequest;
import com.webscapper.request.ExtractRequest;
import com.webscapper.response.ExportResponse;
import com.webscapper.response.ExtractResponse;
import com.webscrapper.constants.ContentType;
import com.webscrapper.constants.ExportType;
import com.webscrapper.constants.TagType;

public class WSServiceProvider 
{
	/**
	 * 
	 * @param url
	 * @param contentType
	 * @return
	 */
	public ExtractRequest buildExtractRequest(String url, ContentType contentType)
	{
		ExtractRequest extractRequest = new ExtractRequest();
		extractRequest.setUrl(url);
		extractRequest.setContentType(contentType);
		return extractRequest;
	}
	
	/**
	 * 
	 * @param url
	 * @param title
	 * @param extractResponse
	 * @param exportType
	 * @param tagsList
	 * @param location
	 * @return
	 */
	public ExportRequest buildExportRequest(String url, String title, ExtractResponse extractResponse, ExportType exportType, List<String> tagsList, String location, List<String> selectedImageURLList)
	{
		ExportRequest exportRequest = new ExportRequest();
		exportRequest.setUrl(url);
		exportRequest.setTitle(title);
		exportRequest.setExtractResponse(extractResponse);
		exportRequest.setExportType(exportType);
		exportRequest.setTagsList(tagsList);
		exportRequest.setLocation(location);
		exportRequest.setImageURLList(selectedImageURLList);
		
		return exportRequest;
	}
	
	/**
	 * 
	 * @param extractRequest
	 * @return
	 */
	public ExtractResponse executeExtractOperation(ExtractRequest extractRequest)
	{
		ExtractResponse extractResponse = ExtractServiceFactory.getInstance(extractRequest.getContentType()).extract(extractRequest);
		return extractResponse;
	}
	
	/**
	 * 
	 * @param exportRequest
	 * @return
	 */
	public ExportResponse executeExportOperation(ExportRequest exportRequest)
	{
		ExportResponse exportResponse = ExportServiceFactory.getInstance(exportRequest.getExportType()).export(exportRequest);
		return exportResponse;
	}	
	
	/**
	 * 
	 * @param extractResponse
	 * @return
	 */
	public String[][] fetchTabularPreviewData(ExtractResponse extractResponse)
	{
		String[][] columnArray = new String[4][4];
		List<List<List<String>>> tablesList = extractResponse != null ? extractResponse.getTables() : null;
        if (tablesList != null && tablesList.size() > 0) 
        {
        	List<List<String>> table = tablesList.get(0);                            
        	int rowCounter = 0;           
            for (List<String> row : table) 
            {              
            	if(rowCounter == 4)
            	{
            		break;
            	}
            	columnArray[rowCounter] = new String[4];
            	int columnCounter = 0;
            	for(String columnValue : row)
                {
                	if(columnCounter == 4)
                	{
                		break;
                	}
                	columnArray[rowCounter][columnCounter]=columnValue;
            		columnCounter++;
                }
            	
            	if(columnCounter < 4)
            	{
            		for(int i = columnCounter ; i<= 3 ; i++)
            				columnArray[rowCounter][i]="";
            	}
            	
            	rowCounter++;
            }
            if(rowCounter < 4 )
            {            	
            	for(int i = rowCounter ; i<= 3 ; i++)
            	{
            		columnArray[i] = new String[4];
            		for(int j = 0 ; j<= 3 ; j++)
            		{
            			columnArray[i][j]="";
            		}
            	}
            }
        }
        
        return columnArray;
	}
	
	/**
	 * 
	 * @param extractResponse
	 * @return
	 */
	public String[] fetchColumnNameForPreview(ExtractResponse extractResponse)
	{		
        String[] columnArray = new String[4];
        columnArray[0] = "Column_1";
        columnArray[1] = "Column_2";
        columnArray[2] = "Column_3";
        columnArray[3] = "Column_4";
        return columnArray;
	}
	
	
	/**
	 * 
	 * @param extractResponse
	 * @return
	 */
	public String fetchNonTabularPreviewData(ExtractResponse response, List<String> tagsList)
	{		
		String previewData = "";
		Map<TagType, String> tagData = response != null ? response.getTagDataMap() : null;

		if (tagsList != null) 
		{
			for (String tags : tagsList)
			{
				for (Map.Entry<TagType, String> entry : tagData.entrySet()) 
				{
					if (entry.getKey().getDisplayName().equals(tags)) 
					{
						previewData = previewData+entry.getKey() + "\n";
						String value = entry.getValue();
						if(value.length() > 50)
						{
							value = value.substring(0, 50);
						}
						previewData = previewData+value + "\n";
						previewData = previewData+"\n";
					}
				}
			}		
		}    
		
		return previewData;
	}
	
	/**
	 * 
	 * @param extractResponse
	 * @return
	 * @throws IOException 
	 */
	public InputStream fetchImagePreviewData(String ImageURL) throws IOException
	{
		InputStream is = null;
		
		URL url = new URL(ImageURL);      
          
		is = url.openStream();      
		
		return is;
              
	}
}

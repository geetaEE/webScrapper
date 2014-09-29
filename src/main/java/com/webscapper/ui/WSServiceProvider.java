package com.webscapper.ui;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.webscapper.factory.ExportServiceFactory;
import com.webscapper.factory.ExtractServiceFactory;
import com.webscapper.request.ExportRequest;
import com.webscapper.request.ExtractRequest;
import com.webscapper.response.ExportResponse;
import com.webscapper.response.ExtractResponse;
import com.webscrapper.constants.ContentType;
import com.webscrapper.constants.ExportType;
import com.webscrapper.constants.TagType;


// TODO: Auto-generated Javadoc
/**
 * The Class WSServiceProvider.
 */
public class WSServiceProvider 
{
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(WSServiceProvider.class);
	
	/**
	 * Builds the extract request.
	 *
	 * @param url the url
	 * @param contentType the content type
	 * @return the extract request
	 */
	public ExtractRequest buildExtractRequest(String url, ContentType contentType)
	{
		logger.info("Entering buildExtractRequest method.");
		
		ExtractRequest extractRequest = new ExtractRequest();
		extractRequest.setUrl(url);
		extractRequest.setContentType(contentType);
		
		logger.info("Exiting from buildExtractRequest method.");
		
		return extractRequest;
	}
	
	/**
	 * Builds the export request.
	 *
	 * @param url the url
	 * @param title the title
	 * @param extractResponse the extract response
	 * @param exportType the export type
	 * @param tagsList the tags list
	 * @param location the location
	 * @param selectedImageURLList the selected image url list
	 * @return the export request
	 */
	public ExportRequest buildExportRequest(String url, String title, ExtractResponse extractResponse, ExportType exportType, List<String> tagsList, String location, List<String> selectedImageURLList)
	{
		logger.info("Entering buildExportRequest method.");
		
		ExportRequest exportRequest = new ExportRequest();
		exportRequest.setUrl(url);
		exportRequest.setTitle(title);
		exportRequest.setExtractResponse(extractResponse);
		exportRequest.setExportType(exportType);
		exportRequest.setTagsList(tagsList);
		exportRequest.setLocation(location);
		exportRequest.setImageURLList(selectedImageURLList);
		
		logger.info("Exiting from buildExportRequest method.");
		
		return exportRequest;
	}
	
	/**
	 * Execute extract operation.
	 *
	 * @param extractRequest the extract request
	 * @return the extract response
	 */
	public ExtractResponse executeExtractOperation(ExtractRequest extractRequest)
	{
		logger.info("Entering executeExtractOperation method.");
		
		ExtractResponse extractResponse = null;
        try {
            extractResponse = ExtractServiceFactory.getInstance(extractRequest.getContentType()).extract(extractRequest);
        } catch (IOException e) {
            logger.error(e);
        }
		
		logger.info("Exiting from executeExtractOperation method.");
		
		return extractResponse;
	}
	
	/**
	 * Execute export operation.
	 *
	 * @param exportRequest the export request
	 * @return the export response
	 */
	public ExportResponse executeExportOperation(ExportRequest exportRequest)
	{
		logger.info("Entering executeExportOperation method.");
		
		ExportResponse exportResponse = ExportServiceFactory.getInstance(exportRequest.getExportType()).export(exportRequest);
		
		logger.info("Exiting from executeExportOperation method.");
		
		return exportResponse;
	}	
	
	/**
	 * Fetch tabular preview data.
	 *
	 * @param extractResponse the extract response
	 * @return the string[][]
	 */
	public String[][] fetchTabularPreviewData(ExtractResponse extractResponse)
	{
		logger.info("Entering fetchTabularPreviewData method.");
		
		String[][] columnArray = new String[4][4];
		List<List<List<String>>> tablesList = extractResponse != null ? extractResponse.getTables() : null;
        if (tablesList != null && tablesList.size() > 0) 
        {
        	List<List<String>> table = tablesList.get(0);                            
        	int rowCounter = populateTableData(table, columnArray);   
        	
            if(rowCounter < 4 )
            {            	
            	for(int i = rowCounter ; i<= 3 ; i++)
            	{
            		populateRowBlankValue(columnArray, i);
            		
            	}
            }
        }
        
        logger.info("Exiting from fetchTabularPreviewData method.");
        
        return columnArray;
	}
	
	/**
	 * Populate row blank value.
	 *
	 * @param columnArray the column array
	 * @param i the i
	 */
	public void populateRowBlankValue(String[][] columnArray, int i)
	{
		columnArray[i] = new String[4];
		for(int j = 0 ; j<= 3 ; j++)
		{
			columnArray[i][j]="";
		}
	}
	
	/**
	 * Populate column blank value.
	 *
	 * @param columnArray the column array
	 * @param columnCounter the column counter
	 * @param rowCounter the row counter
	 */
	public void populateColumnBlankValue(String[][] columnArray, int columnCounter, int rowCounter)
	{
		for(int i = columnCounter ; i<= 3 ; i++)
		{	
				columnArray[rowCounter][i]="";
		}
	}
	
	/**
	 * Populate table data.
	 *
	 * @param table the table
	 * @param columnArray the column array
	 * @return the int
	 */
	public int populateTableData(List<List<String>> table, String[][] columnArray)
	{
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
        		populateColumnBlankValue(columnArray, columnCounter, rowCounter);		
        	}
        	
        	rowCounter++;
        }
        
        return rowCounter;
	}
	
	/**
	 * Fetch column name for preview.
	 *
	 * @param extractResponse the extract response
	 * @return the string[]
	 */
	public String[] fetchColumnNameForPreview(ExtractResponse extractResponse)
	{		
		logger.info("Entering fetchColumnNameForPreview method.");
		
		String[] columnArray = new String[4];
        columnArray[0] = "Column_1";
        columnArray[1] = "Column_2";
        columnArray[2] = "Column_3";
        columnArray[3] = "Column_4";
        
        logger.info("Exiting from fetchColumnNameForPreview method.");
        
        return columnArray;
	}
	
	
	/**
	 * Fetch non tabular preview data.
	 *
	 * @param response the response
	 * @param tagsList the tags list
	 * @return the string
	 */
	public String fetchNonTabularPreviewData(ExtractResponse response, List<String> tagsList)
	{		
		logger.info("Entering fetchNonTabularPreviewData method.");
		
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
		
		logger.info("Exiting from fetchNonTabularPreviewData method.");
		
		return previewData;
	}
	
	/**
	 * Fetch image preview data.
	 *
	 * @param imageURL the image url
	 * @return the input stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public InputStream fetchImagePreviewData(String imageURL) throws IOException
	{
		logger.info("Entering fetchImagePreviewData method.");
		
		InputStream is = null;
		
		URL url = new URL(imageURL);      
          
		is = url.openStream();      
		
		logger.info("Exiting from fetchImagePreviewData method.");
		
		return is;
              
	}
}

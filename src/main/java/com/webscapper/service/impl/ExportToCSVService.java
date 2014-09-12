package com.webscapper.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import com.webscapper.request.ExportRequest;
import com.webscapper.response.ExportResponse;
import com.webscapper.response.ExtractResponse;
import com.webscrapper.service.ExportService;

public class ExportToCSVService implements ExportService {

    @Override
    public ExportResponse export(ExportRequest request) 
    {
        String s = request.getLocation()+File.separator+ request.getTitle() + ".csv";
        ExportResponse exportResponse = new ExportResponse();
        
        try 
        {
            FileWriter writer = new FileWriter(s);
            ExtractResponse response = request.getExtractResponse();
            List<List<List<String>>> tablesList = response != null ? response.getTables() : null;
            if (tablesList != null) {
                Iterator<List<List<String>>> tableIterator = tablesList.iterator();
                while (tableIterator.hasNext()) {
                    List<List<String>> rowsList = tableIterator.next();
                    Iterator<List<String>> rowIterator = rowsList.iterator();
                    while (rowIterator.hasNext()) 
                    {
                        int commaCounter = 0;
                    	List<String> colsList = rowIterator.next();
                        Iterator<String> colIterator = colsList.iterator();
                        while (colIterator.hasNext()) 
                        {                            
                            if(commaCounter >0)
                            {
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
        } 
        catch (Exception e) 
        {            
            exportResponse.setSuccess(false);
        }
        return exportResponse;
    }

}

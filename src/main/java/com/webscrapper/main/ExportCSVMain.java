package com.webscrapper.main;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.webscapper.factory.ExtractServiceFactory;
import com.webscapper.request.ExtractRequest;
import com.webscapper.response.ExtractResponse;
import com.webscrapper.constants.ContentType;
import com.webscrapper.constants.TagType;
 
 
public class ExportCSVMain {
                
                
  public static void main(String ...args)
 {
		try {
			// XLS Location
			String s = "C:\\Users\\geeta.chaudhary\\Desktop\\Geeta.csv";
			// OutputStream stream = new FileOutputStream(s);

			FileWriter writer = new FileWriter(s);

//			writer.append("DisplayName");
//			writer.append(',');
//			writer.append("Age");
//			writer.append('\n');

			// generate whatever data you want

			//
			// WritableWorkbook workbook = Workbook.createWorkbook(stream);
			// WritableSheet sheet = workbook.createSheet("Sheet1", 0);
			// sheet.addCell(new Label(0,0, "Name"));
			// sheet.addCell(new Label(1,0, "Age"));
			// sheet.addCell(new Label(2,0, "Messages"));

			// Creating JSON Object
//			JSONObject obj = new JSONObject();
//			obj.put("name", "geeta");
//			obj.put("age", new Long(100));
//
//			JSONArray list = new JSONArray();
//			list.add("msg 1");
//			list.add("msg 2");
//			list.add("msg 3");
//
//			obj.put("messages", list);
			
			
			 String url = "https://www.httpsnow.org/";
		        ExtractRequest request = new ExtractRequest();
		        request.setUrl(url);
		        request.setContentType(ContentType.TEXT);
		        ExtractResponse response = ExtractServiceFactory.getInstance(request.getContentType()).extract(request);
		        Map<TagType, String> tagDataMap = response.getTagDataMap();
		        List<List<List<String>>> tables = response.getTables();
			for (List<List<String>> table : tables) {
				for (List<String> tableData : table) {
					for(String string: tableData){
						writer.append(string);
						writer.append(',');
					}
				}
			}
			
			for(Map.Entry<TagType, String> mapEntry : tagDataMap.entrySet()){
				TagType tagType = mapEntry.getKey();
				String string = mapEntry.getValue();
				writer.append(tagType.getDisplayName());
				writer.append(',');
				writer.append(string);
				writer.append('\n');
			}
		        

//			// Parse JSON
//			String name = (String) obj.get("name");
//			// sheet.addCell(new Label(0, 1, name));
//			writer.append(name);
//			writer.append(',');
//			long age = (Long) obj.get("age");
//			writer.append(String.valueOf(age));
//			writer.append(',');
//			// sheet.addCell(new Label(1, 1, String.valueOf(age)));
//			// loop array
//			JSONArray msg = (JSONArray) obj.get("messages");
//			Iterator<String> iterator = msg.iterator();
//			// int index = 1;
//			while (iterator.hasNext()) {
//				// sheet.addCell(new Label(2, index, iterator.next()));
//				// index++;
//				writer.append(iterator.next());
//				writer.append(',');
//			}

			// workbook.write();
			// workbook.close();
			writer.flush();
			writer.close();
		} catch (IOException e) {
		}
	}
    
}

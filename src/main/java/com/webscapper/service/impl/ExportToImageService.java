package com.webscapper.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;

import com.webscapper.request.ExportRequest;
import com.webscapper.response.ExportResponse;
import com.webscrapper.constants.CommonConstants;
import com.webscrapper.service.ExportService;

public class ExportToImageService implements ExportService {
    /*
     * (non-Javadoc)
     * @see com.webscrapper.service.ExportService#export(com.webscapper.request.ExportRequest) Export Image Functionality.
     */
    @Override
    public ExportResponse export(ExportRequest request) {
        ExportResponse exportResponse = new ExportResponse();
        if (null != request) {
            String dirLocation = request.getLocation();
            List<String> imageList = request.getImageURLList();
            String title = request.getTitle();
            if (null != imageList && !imageList.isEmpty() && null != dirLocation && !dirLocation.isEmpty() && title != null && !title.isEmpty()) {
                dirLocation = dirLocation + File.separator + title + CommonConstants.DATE_FORMATTER.format(new Date());
                exportResponse.setSuccess(saveImages(imageList, dirLocation));
            }
        }
        return exportResponse;
    }

    /** Save Images.
     * 
     * @param imageUrlList
     * @param imageStorePath
     * @return Save the Images to Directory selected via UI. */
    private boolean saveImages(List<String> imageUrlList, String imageStorePath) {
        InputStream is = null;
        OutputStream os = null;
        try {
            for (String imageUrl : imageUrlList) {
                URL url = new URL(imageUrl);
                String fileName = url.getFile();
                if (null != fileName && fileName.contains("/")) {
                    fileName = fileName.substring(fileName.lastIndexOf("/")).split("/")[1];
                    String destName = imageStorePath + File.separator + fileName;
                    File destDir = new File(imageStorePath);
                    destDir.mkdirs();
                    is = url.openStream();
                    os = new FileOutputStream(destName);
                    byte[] img = new byte[2048];
                    int length;
                    while ((length = is.read(img)) != -1) {
                        os.write(img, 0, length);
                    }
                }
            }
            is.close();
            os.close();
            return true;
        } catch (Exception e) {
            try {
                if (null != is)
                    is.close();
                if (null != os)
                    os.close();
            } catch (IOException e1) {
                return false;
            }
            return false;
        }
    }
}
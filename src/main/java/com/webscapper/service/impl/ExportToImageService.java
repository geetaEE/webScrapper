package com.webscapper.service.impl;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.webscapper.exception.WebScrapperException;
import com.webscapper.request.ExportRequest;
import com.webscapper.response.ExportResponse;
import com.webscapper.util.ImageUtil;
import com.webscrapper.constants.CommonConstants;
import com.webscrapper.service.ExportService;

/** Export Image Service class. */
public class ExportToImageService implements ExportService {
    private static Logger logger = Logger.getLogger(ExportToImageService.class);

    @Override
    public ExportResponse export(ExportRequest request) throws WebScrapperException {
        logger.info("Image export executing");
        ExportResponse exportResponse = new ExportResponse();
        if (null != request) {
            String dirLocation = request.getLocation();
            List<String> imageList = request.getImageURLList();
            String title = request.getTitle();
            boolean imageExists = null != imageList && !imageList.isEmpty();
            boolean dirLocExists = null != dirLocation && !dirLocation.isEmpty();
            boolean titleExists = title != null && !title.isEmpty();
            if (imageExists && dirLocExists && titleExists) {
                dirLocation = dirLocation + File.separator + title + CommonConstants.DATE_FORMATTER.format(new Date());
                saveImages(imageList, dirLocation);
                exportResponse.setSuccess(true);
            }
        }
        return exportResponse;
    }

    /** Save Images.
     * 
     * @param imageUrlList
     * @param imageStorePath
     * @throws WebScrapperException */
    private void saveImages(List<String> imageUrlList, String imageStorePath) throws WebScrapperException {
        for (String imageUrl : imageUrlList) {
            URL url;
            try {
                url = new URL(imageUrl);
            } catch (MalformedURLException e) {
                logger.error(imageUrl + CommonConstants.EXP_IMG_URL_INVALID, e);
                throw new WebScrapperException(imageUrl + CommonConstants.EXP_IMG_URL_INVALID, e);
            }
            String fileName = url.getFile();
            if (null != fileName && fileName.contains("/")) {
                ImageUtil.saveImage(imageUrl, url, fileName, imageStorePath);
            }
        }
    }
}
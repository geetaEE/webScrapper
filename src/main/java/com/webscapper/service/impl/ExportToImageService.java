package com.webscapper.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
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
public enum ExportToImageService implements ExportService {
    INSTANCE;
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
                dirLocation = dirLocation + File.separator + title + new SimpleDateFormat(CommonConstants.DATE_FORMAT).format(new Date());
                saveImages(imageList, dirLocation);
                exportResponse.setSuccess(true);
            }
        }
        return exportResponse;
    }

    /** Save Images.
     * 
     * @param imageUrlList
     *            imageUrlList
     * @param imageStorePath
     *            imageStorePath
     * @throws WebScrapperException
     *             exception */
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
                try {
                    ImageUtil.saveImage(imageUrl, url, fileName, imageStorePath);
                } catch (IOException e) {
                    logger.error(imageUrl + CommonConstants.EXP_IMG_OPER_ERROR, e);
                    throw new WebScrapperException(CommonConstants.EXP_IMG_OPER_ERROR, e);
                }
            }
        }
    }
}
package com.webscapper.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.webscapper.exception.WebScrapperException;
import com.webscapper.request.ExportRequest;
import com.webscapper.response.ExportResponse;
import com.webscrapper.constants.CommonConstants;
import com.webscrapper.service.ExportService;

public class ExportToImageService implements ExportService {
    private static Logger logger = Logger.getLogger(ExportToImageService.class);

    @Override
    public ExportResponse export(ExportRequest request) {
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
                try {
                    saveImages(imageList, dirLocation);
                    exportResponse.setSuccess(true);
                } catch (WebScrapperException e) {
                    exportResponse.setErrMsg(e.getMessage());
                    exportResponse.setSuccess(false);
                }
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
        InputStream is = null;
        OutputStream os = null;
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
                String[] fileSeparatorArr = fileName.split("/");
                fileName = fileSeparatorArr[fileSeparatorArr.length - 1];
                String destName = imageStorePath + File.separator + fileName;
                File destDir = new File(imageStorePath);
                destDir.mkdirs();
                try {
                    is = url.openStream();
                } catch (IOException e) {
                    logger.error(imageUrl + CommonConstants.EXP_IMG_OPER_ERROR, e);
                    throw new WebScrapperException(CommonConstants.EXP_IMG_OPER_ERROR, e);
                }
                try {
                    os = new FileOutputStream(destName);
                } catch (FileNotFoundException e) {
                    logger.error(destName + CommonConstants.EXP_IMG_FILE_EXIST_RW_ERROR, e);
                    throw new WebScrapperException(CommonConstants.EXP_IMG_FILE_EXIST_RW_ERROR, e);
                } catch (SecurityException e) {
                    logger.error(destName + CommonConstants.EXP_IMG_FILE_WRITE_ERROR, e);
                    throw new WebScrapperException(CommonConstants.EXP_IMG_FILE_WRITE_ERROR, e);
                }
                byte[] img = new byte[CommonConstants.DEFAULT_BYTE_ARR_SIZE];
                int length;
                try {
                    while ((length = is.read(img)) != -1) {
                        os.write(img, 0, length);
                    }
                } catch (IOException e) {
                    logger.error(imageUrl + CommonConstants.EXP_IMG_OPER_ERROR, e);
                    throw new WebScrapperException(CommonConstants.EXP_IMG_OPER_ERROR, e);
                } finally {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            logger.error(imageUrl + CommonConstants.EXP_IMG_OPER_ERROR, e);
                            throw new WebScrapperException(CommonConstants.EXP_IMG_OPER_ERROR, e);
                        }
                    }
                    if (os != null) {
                        try {
                            os.close();
                        } catch (IOException e) {
                            logger.error(imageUrl + CommonConstants.EXP_IMG_OPER_ERROR, e);
                            throw new WebScrapperException(CommonConstants.EXP_IMG_OPER_ERROR, e);
                        }
                    }
                }
            }
        }
    }
}
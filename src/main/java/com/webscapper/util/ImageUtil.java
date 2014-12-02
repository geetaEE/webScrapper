package com.webscapper.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.apache.log4j.Logger;

import com.webscapper.exception.WebScrapperException;
import com.webscrapper.constants.CommonConstants;

/** The Class ImageUtil. */
public final class ImageUtil {
    /** The logger. */
    private static final Logger LOG = Logger.getLogger(ImageUtil.class);

    /** Instantiates a new ImageUtil. */
    private ImageUtil() {
    }

    /** Save image.
     * 
     * @param imageUrl
     *            image url
     * @param url
     *            url
     * @param fileName
     *            file name
     * @param imageStorePath
     *            image directory
     * @throws WebScrapperException 
     * @throws IOException */
    public static void saveImage(String imageUrl, URL url, String fileName, String imageStorePath) throws WebScrapperException, IOException {
        InputStream is = null;
        OutputStream os = null;
        String[] fileSeparatorArr = fileName.split("/");
        String actaulFileName = fileSeparatorArr[fileSeparatorArr.length - 1];
        String destName = imageStorePath + File.separator + actaulFileName;
        File destDir = new File(imageStorePath);
        boolean isCreated = destDir.mkdirs();
        if (isCreated) {
            LOG.info("Directory created with path: " + destDir.getAbsolutePath());
        }
        try {
            is = url.openStream();
            os = new FileOutputStream(destName);
            byte[] img = new byte[CommonConstants.DEFAULT_BYTE_ARR_SIZE];
            int length;
            while ((length = is.read(img)) != -1) {
                os.write(img, 0, length);
            }
        } catch(final IOException e){
        	LOG.error(imageUrl + CommonConstants.EXP_IMG_OPER_ERROR, e);
            throw new WebScrapperException(CommonConstants.EXP_IMG_OPER_ERROR, e);
        }finally{
            if(os != null){os.close();}
            if(is != null){is.close();}
        }
    }
}
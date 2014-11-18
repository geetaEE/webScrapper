package com.webscapper.service.impl;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.log4j.Logger;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.webscrapper.constants.CommonConstants;
import com.webscrapper.service.ExtractService;

/** The base extract service. */
public abstract class BaseExtractService implements ExtractService {
    private static Logger logger = Logger.getLogger(BaseExtractService.class);

    static {
        // Initialize for ssl communication.
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        } };

        try {
            SSLContext sc = SSLContext.getInstance(CommonConstants.SSL_PROTOCOL);
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (KeyManagementException e) {
            logger.error("Security key error occurred", e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Security algorithm error occurred", e);
        }
        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }

    /** Extract html document.
     * 
     * @param url
     *            the url
     * @return html document
     * @throws IOException */
    public Document extractDocument(String url) throws IOException {
        logger.info("Method extractDocument is executing");
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent(CommonConstants.USER_AGENT).timeout(CommonConstants.EXTRACT_TIMEOUT).get();
        } catch (IllegalArgumentException e) {
            logger.error(CommonConstants.EXTRACT_URL_INVALID, e);
            throw new IOException(CommonConstants.EXTRACT_URL_INVALID);
        } catch (UnknownHostException e) {
            logger.error(CommonConstants.EXTRACT_URL_INVALID, e);
            throw new IOException(CommonConstants.EXTRACT_URL_INVALID);
        } catch (SocketTimeoutException e) {
            logger.error(CommonConstants.EXTRACT_READ_TIME_OUT, e);
            throw new IOException(CommonConstants.EXTRACT_READ_TIME_OUT);
        } catch (HttpStatusException e) {
            logger.error(CommonConstants.EXTRACT_HTTP_ERROR + e.getStatusCode(), e);
            throw new IOException(CommonConstants.EXTRACT_HTTP_ERROR + e.getStatusCode());
        } catch (SSLHandshakeException e) {
            logger.error(CommonConstants.EXTRACT_SSL_ERROR, e);
            throw new IOException(CommonConstants.EXTRACT_SSL_ERROR);
        }
        if (doc != null) {
            doc.select(CommonConstants.HIDDEN_CONTENT_EXPRESSION).remove();
        }
        return doc;
    }
}
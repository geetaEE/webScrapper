package com.webscapper.util;

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

import com.webscapper.exception.WebScrapperException;
import com.webscrapper.constants.CommonConstants;

/** The Class ExtractUtil. */
public final class ExtractUtil {
    private static final Logger LOG = Logger.getLogger(ExtractUtil.class);

    static {
        // Initialize for ssl communication.
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager(){
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
            LOG.error("Security key error occurred", e);
        } catch (NoSuchAlgorithmException e) {
            LOG.error("Security algorithm error occurred", e);
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

    /** Instantiates a new ExtractUtil. */
    private ExtractUtil() {
    }

    /** Extract html document.
     * 
     * @param url
     *            the url
     * @return html document
     * @throws WebScrapperException */
    public static Document extractDocument(String url) throws WebScrapperException {
        LOG.info("Method extractDocument is executing");
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent(CommonConstants.USER_AGENT).timeout(CommonConstants.EXTRACT_TIMEOUT).get();
        } catch (IllegalArgumentException e) {
            LOG.error(CommonConstants.EXTRACT_URL_INVALID, e);
            throw new WebScrapperException(CommonConstants.EXTRACT_URL_INVALID, e);
        } catch (UnknownHostException e) {
            LOG.error(CommonConstants.EXTRACT_URL_INVALID, e);
            throw new WebScrapperException(CommonConstants.EXTRACT_URL_INVALID, e);
        } catch (SocketTimeoutException e) {
            LOG.error(CommonConstants.EXTRACT_READ_TIME_OUT, e);
            throw new WebScrapperException(CommonConstants.EXTRACT_READ_TIME_OUT, e);
        } catch (HttpStatusException e) {
            LOG.error(CommonConstants.EXTRACT_HTTP_ERROR + e.getStatusCode(), e);
            throw new WebScrapperException(CommonConstants.EXTRACT_HTTP_ERROR + e.getStatusCode(), e);
        } catch (SSLHandshakeException e) {
            LOG.error(CommonConstants.EXTRACT_SSL_ERROR, e);
            throw new WebScrapperException(CommonConstants.EXTRACT_SSL_ERROR, e);
        } catch (IOException e) {
            LOG.error(CommonConstants.EXTRACT_DEFAULT_ERROR, e);
            throw new WebScrapperException(CommonConstants.EXTRACT_DEFAULT_ERROR, e);
        }
        if (doc != null) {
            doc.select(CommonConstants.HIDDEN_CONTENT_EXPRESSION).remove();
        }
        return doc;
    }
}
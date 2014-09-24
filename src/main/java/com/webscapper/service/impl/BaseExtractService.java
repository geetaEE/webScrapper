package com.webscapper.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.webscrapper.exception.ExtractException;
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
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (KeyManagementException e) {
            logger.error("Security key error occurred" + e);
            throw new ExtractException("Security key error", e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Security algorithm error occurred" + e);
            throw new ExtractException("Security algorithm error", e);
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
     * @return html document */
    protected Document extractDocument(String url) {
        logger.info("Method extractDocument is executing");
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(30000).get();
        } catch (IOException e) {
            BufferedReader br = null;
            StringBuilder htmlB = new StringBuilder();
            try {
                URLConnection con = new URL(url).openConnection();
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String input = null;
                while ((input = br.readLine()) != null) {
                    htmlB.append(input);
                }
            } catch (IOException ie) {
                logger.error("Document process error occurred" + ie);
                throw new ExtractException("Document process error", ie);
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e1) {
                        logger.error("Connection close error occurred" + e1);
                        throw new ExtractException("Connection close error", e1);
                    }
                }
            }
            String html = htmlB.toString().trim();
            if (!html.isEmpty()) {
                doc = Jsoup.parse(htmlB.toString());
            }
        }
        if (doc != null) {
            doc.select("*[style*=display:none]").remove();
        }
        return doc;
    }
}
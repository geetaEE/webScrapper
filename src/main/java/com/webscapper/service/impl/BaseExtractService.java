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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.webscrapper.service.ExtractService;

/** The base extract service. */
public abstract class BaseExtractService implements ExtractService {
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
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
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
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
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
                ie.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            String html = htmlB.toString().trim();
            if (!html.isEmpty()) {
                doc = Jsoup.parse(htmlB.toString());
            }
        }
        return doc;
    }
}
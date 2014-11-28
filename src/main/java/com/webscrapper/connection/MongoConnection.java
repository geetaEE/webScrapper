package com.webscrapper.connection;

import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.webscapper.exception.WebScrapperException;
import com.webscapper.util.WSResource;
import com.webscrapper.constants.CommonConstants;

/** @author ruby.jha MongoConnection */
public class MongoConnection {
    private static Logger logger = Logger.getLogger(MongoConnection.class);

    /** The db. */
    private DB db;
    /** The client. */
    private MongoClient client;

    /** Close connection. */
    public void closeConnection() {
        if (client != null) {
            closeClient();
        }
    }

    /** @return the db */
    public DB getDb() {
        return db;
    }

    /** Get db connection.
     * 
     * @return db connection
     * @throws WebScrapperException
     *             exception */
    public DB getDbConnection() throws WebScrapperException {
        logger.info("Entering from getDbConnection method.");
        if (db == null) {
            boolean auth = false;
            String server = WSResource.getValue(CommonConstants.MONGO_SERVER);
            String port = WSResource.getValue(CommonConstants.PORT_NO);
            int portNo = 0;

            try {
                if (null != port && !port.isEmpty()) {
                    portNo = Integer.parseInt(port);
                }

                client = new MongoClient(server, portNo);

                db = client.getDB(WSResource.getValue(CommonConstants.DB_NAME));
                db.addUser(WSResource.getValue(CommonConstants.USER_NAME), WSResource.getValue(CommonConstants.PASSWORD).toCharArray());

                auth = db.authenticate(WSResource.getValue(CommonConstants.USER_NAME), WSResource.getValue(CommonConstants.PASSWORD).toCharArray());
            } catch (UnknownHostException e) {
                logger.error("UnknownHost Exception", e);
                throw new WebScrapperException(CommonConstants.EXP_CONNECTION_FAILED, e);
            } catch (MongoException e) {
                logger.error("Authentication Fail.", e);
                throw new WebScrapperException(CommonConstants.EXP_CONNECTION_FAILED, e);
            }

            if (!auth) {
                throw new WebScrapperException("Authentication Fail");
            }
        }
        logger.info("Exiting from getDbConnection method.");
        return db;
    }

    /** Close client. */
    public void closeClient() {
        logger.info("Entering from closeClient method.");
        if (client != null) {
            db = null;
            client.close();
            logger.info("Exiting from closeClient method.");
        }
    }

}

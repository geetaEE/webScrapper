package com.webscrapper.connection;

import java.net.UnknownHostException;

import javax.naming.AuthenticationException;

import org.apache.log4j.Logger;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.webscrapper.constants.DBConstants;

/** @author ruby.jha MongoConnection */
public class MongoConnection {
    private static Logger logger = Logger.getLogger(MongoConnection.class);

    /** The db. */
    private DB db;
    /** The client. */
    private MongoClient client;

    /** Close connection */
    public void closeConnection() {
        if (client != null) {
            closeClient();
        }
    }

    /** @return the db */
    public DB getDb() {
        return db;
    }

    /** @return the db connection */
    public DB getDbConnection() throws AuthenticationException {
        logger.info("Entering from getDbConnection method.");
        if (db == null) {
            try {
                client = new MongoClient(DBConstants.MONGO_SERVER, DBConstants.PORT_NO);
            } catch (UnknownHostException e1) {
                logger.warn("UnknownHost Exception", e1);
            }
            db = client.getDB(DBConstants.DB_NAME);
            db.addUser(DBConstants.USER_NAME, DBConstants.PASSWORD.toCharArray());
            boolean auth = false;
            try {
                auth = db.authenticate(DBConstants.USER_NAME, DBConstants.PASSWORD.toCharArray());
            } catch (MongoException e) {
                logger.warn("Authentication Fail.", e);
            }
            if (!auth) {
                throw new AuthenticationException("Authentication Fail");
            }
        }
        logger.info("Exiting from getDbConnection method.");
        return db;
    }

    /** Close client */
    public void closeClient() {
        logger.info("Entering from closeClient method.");
        if (client != null) {
            db = null;
            client.close();
            logger.info("Exiting from closeClient method.");
        }
    }

}

package com.webscrapper.connection;

import org.apache.log4j.Logger;

/** Mongo Connection Manager. */
public enum MongoConnectionManager {
    INSTANCE;
    private static Logger logger = Logger.getLogger(MongoConnectionManager.class);
    /** The connection. */
    private MongoConnection connection;

    /** Prohibits direct instantiation of a new mongo connection from another class. */
    private MongoConnectionManager() {

    }

    /** @return the connection */
    public MongoConnection getConnection() {
        logger.info("Entering getConnection method.");
        if (connection == null) {
            connection = new MongoConnection();
        }
        logger.info("Exiting getConnection method.");
        return connection;
    }

    /** Release connection. */
    public void releaseConnection() {
        logger.info("Entering from releaseConnection method.");
        if (connection != null) {
            connection.closeConnection();
        }
        logger.info("Exiting from releaseConnection method.");
    }

}

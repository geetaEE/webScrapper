package com.webscrapper.connection;

import org.apache.log4j.Logger;

/** @author ruby.jha Mongo Connection Manager */
public final class MongoConnectionManager {
    private static Logger logger = Logger.getLogger(MongoConnectionManager.class);
    /** The connection. */
    private MongoConnection connection;
    /** The connectionManager. */
    private static MongoConnectionManager connectionManager;

    /** Prohibits direct instantiation of a new mongo connection from another class. */
    private MongoConnectionManager() {

    }

    /** Gets the single instance of MongoConnectionManager.
     * 
     * @return single instance of MongoConnectionManager */
    public static MongoConnectionManager getInstance() {
        logger.info("Entering getInstance method.");
        if (connectionManager == null) {
            synchronized (MongoConnectionManager.class) {
                if (connectionManager == null) {
                    connectionManager = new MongoConnectionManager();
                }
            }
        }
        logger.info("Exiting from getInstance method.");
        return connectionManager;
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

    /** Release connection */
    public void releaseConnection() {
        logger.info("Entering from releaseConnection method.");
        if (connection != null) {
            connection.closeConnection();
        }
        logger.info("Exiting from releaseConnection method.");
    }

}

package com.webscrapper.connection;

/**
 * @author ruby.jha
 * Mongo Connection Manager
 */
public class MongoConnectionManager{
	/** The connection. */
    private MongoConnection connection;
    /** The connectionManager. */
    private static MongoConnectionManager connectionManager;
    
    /** Prohibits direct instantiation of a new mongo connection from another class. */
    private MongoConnectionManager()
    {

    }
    /** Gets the single instance of MongoConnectionManager.
     *
     * @return single instance of MongoConnectionManager */
    public static MongoConnectionManager getInstance() {
        if (connectionManager == null) {
            synchronized (MongoConnectionManager.class) {
                if (connectionManager == null) {
                    connectionManager = new MongoConnectionManager();
                }
            }
        }
        return connectionManager;
    }
    /**
	 * @return the connection
	 */
    public MongoConnection getConnection() {
        if (connection == null) {
            connection = new MongoConnection();
        }
        return connection;
    }

    /**
	 * Release connection
	 */
    public void releaseConnection() {
        if (connection != null) {
            connection.closeConnection();
        }
    }    
 
}

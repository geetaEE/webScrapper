package com.webscrapper.connection;

/** Connection Manager. */
public enum ConnectionManager {

    INSTANCE;
    /** The connection. */
    private MongoConnection connection;

    /** Prohibits direct instantiation of a new mongo connection from another class. */
    private ConnectionManager() {
        connection = MongoConnection.INSTANCE;
    }

    /** @return the connection */
    public MongoConnection getConnection() {
        return connection;
    }

    /** Release connection. */
    public void releaseConnection() {
        connection.closeConnection();
    }
}
package com.webscrapper.connection;

public class MongoConnectionManager{
   
    private MongoConnection connection;
    private static MongoConnectionManager connectionManager;
    
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
 
    public MongoConnection getConnection() {
        if (connection == null) {
            connection = new MongoConnection();
        }
        return connection;
    }

    public void releaseConnection() {
        if (connection != null) {
            connection.closeConnection();
        }
    }
    
    /** Override clone method */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}

package com.webscrapper.connection;

import java.net.UnknownHostException;

import javax.naming.AuthenticationException;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.webscrapper.constants.DBConstants;


/**
 * @author ruby.jha
 * MongoConnection
 */
public class MongoConnection {
	/** The db. */
	private DB db;
	/** The client. */
	private MongoClient client;

	/** Instantiates a new mongo connection. */
	public MongoConnection() {
		try {
			this.db = getDbConnection();
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Close connection
	 */
	public void closeConnection() {
		if (client != null) {
			closeClient();
		}
	}
	/**
	 * @return the db
	 */
	public DB getDb() {
		return db;
	}
	/**
	 * @return the db connection
	 */
	public DB getDbConnection() throws AuthenticationException {
		if (db == null) {
			try {
				client = new MongoClient(DBConstants.MONGO_SERVER,
						DBConstants.PORT_NO);
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
			db = client.getDB(DBConstants.DB_NAME);
			db.addUser(DBConstants.USER_NAME,
						DBConstants.PASSWORD.toCharArray());
			boolean auth = false;
			try {
				auth = db.authenticate(DBConstants.USER_NAME,
						DBConstants.PASSWORD.toCharArray());
			} catch (MongoException e) {
				e.printStackTrace();
			}
			if (auth) {
				System.out
						.println("Successfully Authenticated to Mongo Server");
			} else {
				throw new AuthenticationException("Authentication Fail");
			}
		}
		return db;
	}
	/**
	 * @Close client
	 */
	public void closeClient() {
		if (client != null) {
			db = null;
			client.close();

		}
	}
	

}

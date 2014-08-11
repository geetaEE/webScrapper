package com.webscrapper.connection;

import java.net.UnknownHostException;

import javax.naming.AuthenticationException;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.webscrapper.common.CommonConstants;

public class MongoConnection {

	private DB db;
	private MongoClient client;

	public MongoConnection() {
		try {
			this.db = getDbConnection();
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		if (client != null) {
			closeClient();
		}
	}

	public DB getDb() {
		return db;
	}

	public DB getDbConnection() throws AuthenticationException {
		if (db == null) {
			try {
				client = new MongoClient(CommonConstants.MONGO_SERVER,
						CommonConstants.PORT_NO);
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
			db = client.getDB(CommonConstants.DB_NAME);
			db.addUser(CommonConstants.USER_NAME,
						CommonConstants.PASSWORD.toCharArray());
			boolean auth = false;
			try {
				auth = db.authenticate(CommonConstants.USER_NAME,
						CommonConstants.PASSWORD.toCharArray());
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

	public void closeClient() {
		if (client != null) {
			db = null;
			client.close();

		}
	}
	

}

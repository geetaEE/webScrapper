package com.webscrapper.connection;

import java.net.UnknownHostException;

import javax.naming.AuthenticationException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.mongodb.DB;
import com.webscapper.exception.WebScrapperException;

/** The Test Mongo Connection. */
@RunWith(JUnit4.class)
public class TestMongoConnection {

    /** The mongoConnection. */
    private MongoConnection mongoConnection;

    @Before
    public void setUp() throws UnknownHostException {
        mongoConnection = new MongoConnection();
    }

    /** Method under test: getDbConnection() Scenario: Verifying the behavior when DB object is null. Expectation: Should return DB Object.
     * 
     * @throws AuthenticationException */
    @Test
    public void testGetDbConnection() throws WebScrapperException {
        DB db = mongoConnection.getDbConnection();
        Assert.assertNotNull(db);
    }

    @After
    public void destroy() {
        mongoConnection = null;
    }
}
package com.webscrapper.connection;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/** @author ruby.jha Mongo Connection Manager JUnit Test */
@RunWith(JUnit4.class)
public class TestMongoConnectionManager {

    /** The connectionManager. */
    private MongoConnectionManager connectionManager;
    /** The connection. */
    private MongoConnection connection;

    @Before
    public void setUp() {
        connectionManager = MongoConnectionManager.getInstance();
    }

    /** Method under test: getInstance() Scenario: Verifying the behavior when connectionManager is null. Expectation: Should return connectionManager. */
    @Test
    public void testGetInstance() {
        Assert.assertNotNull(connectionManager);
    }

    /** Method under test: getConnection() Scenario: Verifying the behavior when connection is not null. Expectation: Should return Mongo connection. */
    @Test
    public void testGetConnection() {
        connection = connectionManager.getConnection();
        Assert.assertNotNull(connection);
    }

    /** Method under test: releaseConnection() Scenario: Verifying the behavior when connection is not null. Expectation: Should return release the
     * connection. */
    @Test
    public void testReleaseConnection() {
        connection = connectionManager.getConnection();
        connectionManager.releaseConnection();
        Assert.assertNotNull(connection);
    }

    @After
    public void destroy() {
        connectionManager = null;
        connection = null;
    }
}

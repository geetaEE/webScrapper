package com.webscrapper.connection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/** Mongo Connection Manager JUnit Test. */
@RunWith(JUnit4.class)
public class TestMongoConnectionManager {

    /** The connectionManager. */
    private ConnectionManager connectionManager;
    /** The connection. */
    private MongoConnection connection;

    /** Before setup. */
    @Before
    public void setUp() {
        connectionManager = ConnectionManager.INSTANCE;
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

    /** The destroy. */
    @After
    public void destroy() {
        connectionManager = null;
        connection = null;
    }
}
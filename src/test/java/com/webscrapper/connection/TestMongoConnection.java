package com.webscrapper.connection;

import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.mongodb.DB;
import com.webscapper.exception.WebScrapperException;
import com.webscapper.util.WSResourceLoader;
import com.webscrapper.constants.CommonConstants;

/** The Test Mongo Connection. */
@RunWith(JUnit4.class)
public class TestMongoConnection {

    /** The mongoConnection. */
    private MongoConnection mongoConnection;

    /** Before setup.
     * 
     * @throws UnknownHostException the exception */
    @Before
    public void setUp() throws UnknownHostException {
        mongoConnection = MongoConnection.INSTANCE;
    }

    /** Method under test: getDbConnection() Scenario: Verifying the behavior when DB object is null. Expectation: Should return DB Object.
     * 
     * @throws WebScrapperException */
    @Test
    public void testGetDbConnection() throws WebScrapperException {
        DB db = mongoConnection.getDbConnection();
        Assert.assertNotNull(db);
    }

    /** Method under test: getDbConnection() Scenario: Verifying the behavior when DB object is null. Expectation: Should return DB Object.
     * 
     * @throws WebScrapperException */
    @Test
    public void testGetDbConnectionInvalidServer() throws WebScrapperException {
        String server = WSResourceLoader.getPropertiesMap().getProperty("mongoServer");
        try {
            WSResourceLoader.getPropertiesMap().put("mongoServer", "abc");
            mongoConnection.closeConnection();
            mongoConnection.getDbConnection();
            Assert.fail("Failed as expected exception not occurred");
        } catch (WebScrapperException e) {
            Assert.assertEquals(CommonConstants.EXP_CONNECTION_FAILED, e.getMessage());
        }
        WSResourceLoader.getPropertiesMap().put("mongoServer", server);
    }

    /** The destroy. */
    @After
    public void destroy() {
        mongoConnection = null;
    }
}
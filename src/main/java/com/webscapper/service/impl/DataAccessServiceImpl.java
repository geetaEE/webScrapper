package com.webscapper.service.impl;

import java.util.Map;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoException;
import com.webscapper.exception.WebScrapperException;
import com.webscapper.util.WSResource;
import com.webscrapper.connection.MongoConnectionManager;
import com.webscrapper.constants.CommonConstants;
import com.webscrapper.service.DataAccessService;

/** DataAccessServiceImpl */
public class DataAccessServiceImpl implements DataAccessService {
    private static Logger logger = Logger.getLogger(DataAccessServiceImpl.class);

    @Override
    public DBCollection insertData(Map<String, Object> map) throws WebScrapperException {
        DB db = MongoConnectionManager.INSTANCE.getConnection().getDbConnection();

        DBCollection table = db.getCollection(WSResource.getValue(CommonConstants.TABLE_NAME));
        try {
            table.insert(new BasicDBObject(map));
        } catch (MongoException e) {
            logger.error(e);
            throw new WebScrapperException(CommonConstants.DB_INSERT_ERROR, e);
        }
        return table;
    }
}
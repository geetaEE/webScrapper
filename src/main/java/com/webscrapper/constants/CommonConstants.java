package com.webscrapper.constants;

/** The Class CommonConstants.
 * 
 * Common Constants. */
public final class CommonConstants {

    /** Instantiates a new common constants. */
    private CommonConstants() {
    };

    /** The Constant TITLE. */
    public static final String TITLE = "TITLE";

    /** The Constant URL. */
    public static final String URL = "URL";

    /** The Constant CONTENT_TYPE. */
    public static final String CONTENT_TYPE = "CONTENT_TYPE";

    /** The Constant TABLES. */
    public static final String TABLES = "Tables";

    /** The Constant COLUMNS. */
    public static final String COLUMNS = "Columns";

    /** The Constant ROWS. */
    public static final String ROWS = "Rows";

    /** The Constant DATE_FORMAT. */
    public static final String DATE_FORMAT = "yyyy-MM-dd-HH_mm_ss";

    /** The Constant EXTRACT_TIMEOUT. */
    public static final int EXTRACT_TIMEOUT_VAL = 30000;

    /** The Constant EXT_DOC. */
    public static final String EXT_DOC = ".doc";

    /** The Constant EXT_CSV. */
    public static final String EXT_CSV = ".csv";

    /** The Constant EXT_TEXT. */
    public static final String EXT_TEXT = ".txt";

    /** The Constant SSL_PROTOCOL. */
    public static final String SSL_PROTOCOL = "SSL";

    /** The Constant USER_AGENT. */
    public static final String USER_AGENT = "Mozilla/5.0";

    /** The Constant EXTRACT_TIMEOUT. */
    public static final int EXTRACT_TIMEOUT = Integer.valueOf(EXTRACT_TIMEOUT_VAL).intValue();

    /** The Constant HIDDEN_CONTENT_EXPRESSION. */
    public static final String HIDDEN_CONTENT_EXPRESSION = "*[style*=display:none]";

    /** The Constant TABLE_TAG. */
    public static final String TABLE_TAG = "table";

    /** The Constant TH_TAG. */
    public static final String TH_TAG = "th";

    /** The Constant TR_TAG. */
    public static final String TR_TAG = "tr";

    /** The Constant TD_TAG. */
    public static final String TD_TAG = "td";

    /** The Constant IMAGE_TAG. */
    public static final String IMAGE_TAG = "img";

    /** The Constant SRC_ATTR. */
    public static final String SRC_ATTR = "src";

    /** The Constant DEFAULT_BYTE_ARR_SIZE. */
    public static final int DEFAULT_BYTE_ARR_SIZE = 2048;

    // //////////////////////////////////
    /** The Constant EXTRACT_URL_INVALID. */
    public static final String EXTRACT_URL_INVALID = "Invalid URL";

    /** The Constant EXTRACT_READ_TIME_OUT. */
    public static final String EXTRACT_READ_TIME_OUT = "Connection read timeout occurred";

    /** The Constant EXTRACT_SSL_ERROR. */
    public static final String EXTRACT_SSL_ERROR = "Secured ssl key is not valid for https";

    /** The Constant EXTRACT_HTTP_ERROR. */
    public static final String EXTRACT_HTTP_ERROR = "HTTP Error with statut code ";

    /** The Constant EXTRACT_DEFAULT_ERROR. */
    public static final String EXTRACT_DEFAULT_ERROR = "Some problem in extraction";

    /** The Constant DB_AUTHENTICATE_ERROR. */
    public static final String DB_AUTHENTICATE_ERROR = "Database is not authenticated due to invalid credentials";

    /** The Constant DB_INSERT_ERROR. */
    public static final String DB_INSERT_ERROR = "Problem in db insert";

    /** The Constant EXP_FILE_EXIST_ERROR. */
    public static final String EXP_FILE_EXIST_ERROR = "Either file is directory or does not exists or open permission is denied as for file ";

    /** The Constant EXP_FILE_OPER_ERROR. */
    public static final String EXP_FILE_OPER_ERROR = "Problem occured in file operation for ";

    /** The Constant EXP_IMG_URL_INVALID. */
    public static final String EXP_IMG_URL_INVALID = "Image URL is invalid";

    /** The Constant EXP_IMG_OPER_ERROR. */
    public static final String EXP_IMG_OPER_ERROR = "Problem occured in image export";

    /** The Constant EXP_IMG_FILE_EXIST_RW_ERROR. */
    public static final String EXP_IMG_FILE_EXIST_RW_ERROR = "File is directory or has permission problem";

    /** The Constant EXP_IMG_FILE_WRITE_ERROR. */
    public static final String EXP_IMG_FILE_WRITE_ERROR = "File has write permission problem";

    public static final String EXP_LOAD_RESOURCES_ERROR = "Unable to load property file entry.";

    public static final String DB_NAME = "dbName";

    public static final String MONGO_SERVER = "mongoServer";

    public static final String PORT_NO = "port";

    public static final String USER_NAME = "userName";

    public static final String PASSWORD = "password";

    public static final String TABLE_NAME = "tableName";

    public static final String EXP_CONNECTION_FAILED = "Database connection is not available, kindly check MongoDB connection on your machine and then choose export to DB option.";

    /** The Constant CONFIG_PROP_FILE. */
    public static final String CONFIG_PROP_FILE = "/configuration.properties";

    /** The Constant THREE. */
    public static final int THREE = 3;

    /** The Constant FOUR. */
    public static final int FOUR = 4;

    /** The Constant FIVE. */
    public static final int FIVE = 5;

    /** The Constant SEVEN. */
    public static final int SEVEN = 7;
}
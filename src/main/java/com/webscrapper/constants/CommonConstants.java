package com.webscrapper.constants;

import java.text.SimpleDateFormat;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonConstants.
 *
 * @author ruby.jha
 * Common Constants
 */
public final class CommonConstants {
	
	/**
	 * Instantiates a new common constants.
	 */
	private CommonConstants(){};
	
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
	
	/** The Constant DATE_FORMATTER. */
	public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);
	
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
}

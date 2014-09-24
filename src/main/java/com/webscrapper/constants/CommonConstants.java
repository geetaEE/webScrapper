package com.webscrapper.constants;

import java.text.SimpleDateFormat;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonConstants.
 *
 * @author ruby.jha
 * Common Constants
 */
public class CommonConstants {
	
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
	
	/** The Constant DATE_FORMATTER. */
	public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);
	
	/** The Constant EXT_DOC. */
	public static final String EXT_DOC = ".doc";
	
	/** The Constant EXT_CSV. */
	public static final String EXT_CSV = ".csv";
	
	/** The Constant EXT_TEXT. */
	public static final String EXT_TEXT = ".txt";
	
}

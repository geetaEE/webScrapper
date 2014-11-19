package com.webscapper.exception;

/** The web scraper exception. */
public class WebScrapperException extends Exception {
	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = -970550785835203758L;

	/**
	 * Message.
	 */
	private String message;

	/**
	 * Default constructor.
	 */
	public WebScrapperException() {
		super();
	}

	/**
	 * Constructor with message.
	 * 
	 * @param message
	 *            the message
	 */
	public WebScrapperException(String message) {
		super(message);
		this.message = message;
	}

	/**
	 * Constructor with cause.
	 * 
	 * @param cause
	 *            the cause
	 */
	public WebScrapperException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor with message and cause.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public WebScrapperException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}